package com.jk.controller;



import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.json.JsonObject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.assembler.MethodExclusionMBeanInfoAssembler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.jk.pojo.book.Book;
import com.jk.pojo.User;
import com.jk.pojo.UserReponse;
import com.jk.pojo.UserRequest;
import com.jk.pojo.menus.MenusRequest;
import com.jk.pojo.menus.MenusResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;
import com.jk.pojo.upload.Upload;
import com.jk.service.UserService;

import common.base.MySessionContext;
import common.util.DateUtil;
import common.util.FTPUtil;
import common.util.FileUtil;
import common.util.JedisUtil;
import common.util.JsonUtil;
import common.util.Page;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	     
	/**
	 * <pre>login(登录)   
	 * Founder：吴茜     
	 * Found_time：2017年7月24日 下午5:14:26    
	 * Updater：吴茜       
	 * Update_Time：2017年7月24日 下午5:14:26    
	 * Update_Remark： 
	 * @param userRequest
	 * @param request
	 * @return</pre>
	 */
	@RequestMapping("login")
	@ResponseBody
	Map<String,Object> login(UserRequest userRequest,HttpServletRequest request){
		HttpSession session = request.getSession();
		//获取验证码
		Object codeObj = session.getAttribute("imageCode");
		//如果验证码为空 空字符串  codeObj.toString就不会报空指针异常
		if(null== codeObj){
			codeObj= "";
		}
		String code = codeObj.toString();
		//把验证码给系统验证码
		userRequest.setSysImgCode(code);
		Map<String,Object> map=userService.login(userRequest);
		Object userInfo = map.get("userInfo");
		if(null!=userInfo){
			UserReponse userRepose=(UserReponse) userInfo;
			//把用户信息放到session中
			session.setAttribute("userInfo", userInfo);
			String userID = userRepose.getUserID()+"";
			//两个设备不能使用同一个账号登录 把之前在浏览器存的session删掉，在重新添加
			MySessionContext.removeSession(userID,session);
			MySessionContext.addSession(userID, session);
			
			//查询出用户的权限树
			MenusRequest menusRequest=new MenusRequest();
			
			menusRequest.setUserID(userRepose.getUserID());
			List<MenusResponse> treeList = userService.selectMenusListJson(menusRequest);
			//存到redis中
			JedisUtil.setString(userID + "#tree_list", JsonUtil.toJsonString(treeList), -1);
		}
		return map;
		
	}
	
	//退出登录
	@RequestMapping("goLogin")
	String goLogin(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:login2.jsp";
		
		}
	
	/**
	 * <pre>selectBookListJson(查询用户信息并分页)   
	 * Founder：吴茜     
	 * Found_time：2017年7月24日 下午5:17:06    
	 * Updater：吴茜       
	 * Update_Time：2017年7月24日 下午5:17:06    
	 * Update_Remark： 
	 * @return</pre>
	 * @throws IOException 
	 */
	 @RequestMapping("selectUserListJson")
	 @ResponseBody
	Map<String,Object> selectUserListJson(String pageNumber,UserRequest userRequest) throws IOException{
		
		//查询总条数
			int totalCount = userService.selectUserCount(userRequest);
			//总条数
			userRequest.setTotalCount(totalCount);
			if(null == pageNumber || "".equals(pageNumber.trim())){
				pageNumber = "1";
			}
			userRequest.setPageIndex(Integer.valueOf(pageNumber));
			//计算分页信息 调用util封装的计算方法
			userRequest.calculate();
			//查询列表
			 List<UserReponse> userList=userService.selectUserList(userRequest);
			// PoiUtil.exportExcle(userList);
			 //封装结果
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", totalCount);
			map.put("rows", userList);
			 return map;
		 
	 }
	 
	 /**
	  * <pre>insertUser(新增用户信息)   
	  * Founder：吴茜     
	  * Found_time：2017年7月26日 下午7:43:25    
	  * Updater：吴茜       
	  * Update_Time：2017年7月26日 下午7:43:25    
	  * Update_Remark： 
	  * @param userRequest
	  * @return</pre>
	  */
 	@RequestMapping("insertUser")
	@ResponseBody
	String insertUser(UserRequest userRequest) {
		
		userService.insertUser(userRequest);
		//重定向
		return "{}";
	}
 	
 	
 	
 	/**
 	 * <pre>deleteUserInfo(删除用户信息)   
 	 * Founder：吴茜     
 	 * Found_time：2017年7月27日 上午10:06:32    
 	 * Updater：吴茜       
 	 * Update_Time：2017年7月27日 上午10:06:32    
 	 * Update_Remark： 
 	 * @param userRequest
 	 * @return</pre>
 	 */
 	@RequestMapping("deleteUserInfo")
 	@ResponseBody
 	String deleteUserInfo(UserRequest userRequest){
		
 		userService.deleteUserInfo(userRequest);
		return "{}";
 		
 		
 	}

	//跳转到用户展示页面的方法
	@RequestMapping("toUserListPage")
	String toBookListPage(){
		return "/userList";
		
	}
	
		
		
	//跳转到新增或修改用户页面
	@RequestMapping("toAddUserPage")
	ModelAndView toAddUserPage(UserRequest userRequest){
		ModelAndView mv = new ModelAndView();
		if(null !=userRequest.getUserID()){
			UserReponse userReponse=userService.selectUserById(userRequest);
			mv.addObject("userReponse", userReponse);
		}
		
		mv.setViewName("/saveOrUpdate");
		return mv;
	}

	/**
	 * <pre>updateUserInfo(修改用户信息)   
	 * Founder：吴茜     
	 * Found_time：2017年8月5日 上午11:07:40    
	 * Updater：吴茜       
	 * Update_Time：2017年8月5日 上午11:07:40    
	 * Update_Remark： 
	 * @return</pre>
	 */
	@RequestMapping("updateUserInfo")
	@ResponseBody
	String updateUserInfo(UserRequest userRequest){
		userService.updateUserInfo(userRequest);
		return "{}";
	}
	
	//点击角色操作跳转的页面
	//用Model方法也行  返回是字符串，自己就带过去了
	@RequestMapping("toUserRolePage")
	String toUserRolePage(ModelMap ma, UserRequest userRequest){
		ma.addAttribute("userID",userRequest.getUserID());//根据用户ID去修改用户的角色
		
		return "/userRole";
		
	}
	
	
	
	/**
	 * <pre>selectUserRoleListJson(查询zree的方法)   
	 * Founder：吴茜     
	 * Found_time：2017年7月25日 下午8:48:55    
	 * Updater：吴茜       
	 * Update_Time：2017年7月25日 下午8:48:55    
	 * Update_Remark： 
	 * @return</pre>
	 */
	 @RequestMapping("selectUserRoleListJson")
	 @ResponseBody
	 List<RoleResponse> selectUserRoleListJson(RoleRequest roleRequest){
		 List<RoleResponse> roleList=userService.selectUserRoleListJson(roleRequest);
		return roleList;
		 
	 }
	
	 	/**
	 	 * <pre>insertUserRoleList(把之前用户角色删了新增进去的用户角色)   
	 	 * Founder：吴茜     
	 	 * Found_time：2017年7月26日 上午9:49:21    
	 	 * Updater：吴茜       
	 	 * Update_Time：2017年7月26日 上午9:49:21    
	 	 * Update_Remark： 
	 	 * @param roleRequestList
	 	 * @return</pre>
	 	 */
	 	@RequestMapping("insertUserRoleList")
		@ResponseBody
		String insertUserRoleList(@RequestBody List<RoleRequest> roleRequestList) {
			userService.insertUserRoleList(roleRequestList);
			return "{}";
		}
	 	

	 	/**
	 	 * <pre>selectTreeListJson(大树)   
	 	 * Founder：吴茜     
	 	 * Found_time：2017年7月26日 下午9:56:02    
	 	 * Updater：吴茜       
	 	 * Update_Time：2017年7月26日 下午9:56:02    
	 	 * Update_Remark： 
	 	 * @param menuRequest
	 	 * @return</pre>
	 	 */
		@RequestMapping("selectTreeListJson")
		@ResponseBody
		List<Map<String, Object>> selectTreeListJson(MenusRequest menusRequest) {
			List<Map<String, Object>> treeList = userService.selectTreeListJson(menusRequest);
			return treeList;
		}
	
		
		/**
		 * <pre>uploadUserPhoto(上传图片)   
		 * Founder：吴茜     
		 * Found_time：2017年8月4日 下午7:59:51    
		 * Updater：吴茜       
		 * Update_Time：2017年8月4日 下午7:59:51    
		 * Update_Remark： 
		 * MultipartFile 是spring 提供的 用于处理表单的file
		 * @param userPhoto</pre>
		 */
		@RequestMapping("uploadUserPhoto")
		void uploadUserPhoto(MultipartFile userPhoto,HttpServletResponse response){
			//定义临时的字符串 接下边的值  json返回 相当于全局变量
			String imgName = "";
			Upload upload= new Upload();
			PrintWriter writer = null;
			try {
				//输入流
				InputStream inputStream = userPhoto.getInputStream();
				//通过工具类  获取md5 指纹
				String md5 = FileUtil.getMD5(inputStream,"md5");
				//把工具类的指纹赋给数据库的指纹
				upload.setMadFile(md5);
				System.out.println("文件指纹"+md5);
				//查询数据库有没有指纹
				Upload returnUpload=userService.selectUploadPhoto(upload);
				//如果有 直接把地址返回
				if(null != returnUpload){
					imgName = returnUpload.getImgName();
				}else{
					//如果不存在  把文件保存到ftp服务上   并且把文件的的路径 和指纹保存到数据库
					 String originalFilename = userPhoto.getOriginalFilename();
					 //截取 . 最后的后缀名
					 String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
					 //给文件名随机一个ID  避免重名覆盖原来的文件
					 String fileName=UUID.randomUUID().toString()+suffix;
					//存放的路径
					 String path="1702A/"+DateUtil.formatDateToString(new Date(), "yyyy/MM/dd");
					 			//工具类 上传路径  文件名
					 boolean bool = FTPUtil.uploadFile(userPhoto.getInputStream(), fileName, path);
					 //如果判断为真
					 if(bool){
						
						 			//文件路径和文件
						imgName= path + "/"  + fileName;
						//把地址保存到数据库
						upload.setImgName(imgName);
						userService.insertUpload(upload);
						 System.out.println("文件保存在" + path + "/"  + fileName);
					 }
				}
				
				JSONObject  json= new JSONObject();
				json.put("imgName", imgName);
				response.setCharacterEncoding("UTF-8");
				//通过json响应到前台去
				writer = response.getWriter();
				writer.println(json);
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(null!=writer){
					writer.close();
				}
			}
		}
	

}
