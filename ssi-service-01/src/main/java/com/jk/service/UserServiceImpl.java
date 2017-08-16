package com.jk.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dao.UserDao;
import com.jk.pojo.User;
import com.jk.pojo.UserReponse;
import com.jk.pojo.UserRequest;
import com.jk.pojo.menus.MenusRequest;
import com.jk.pojo.menus.MenusResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;
import com.jk.pojo.upload.Upload;

import common.constant.Constant;
import common.util.JedisUtil;
import common.util.JsonUtil;
import common.util.Page;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public Map<String, Object> login(UserRequest userRequest) {
		Map<String,Object> map=new HashMap<String,Object>();
		//默认值设置密码错误
		map.put("flag", Constant.LOGIN_PWD_ERROR);
		map.put("userInfo", null);
		//判断验证码
		if(null== userRequest.getUserImgCode() || "".equals(userRequest.getUserImgCode())){
			//验证码为空
			map.put("flag", Constant.LOGIN_CODE_NULL);
			
			
		}else if(userRequest.getUserImgCode().equals(userRequest.getSysImgCode())){
			 //连接数据库，查询用户信息
			UserReponse userReponse=userDao.login(userRequest);
			
			//判断用户是否存在
			if(null==userReponse){
				map.put("flag", Constant.LOGIN_USER_ERROR);//用户不存在
			
			}else if(0==userReponse.getLoginFailNum() //判断当前登录次数有没有大于三次
					||0<(userReponse.getLoginFailNum()%3) 
					|| userReponse.getLoginDate()>300000){
				
					if(userRequest.getUserPassword().equals(userReponse.getUserPassword())){
						//登录成功
						map.put("flag", Constant.LOGIN_SUCCESS);
						map.put("userInfo",userReponse );
						//把状态修改为0
						userRequest.setLoginFailNum(0);
					}else{
						//密码错误
						/*map.put("flag", Constant.LOGIN_PWD_ERROR);*/
						//用户登录的次数
						int loginFailNum=1;//初始值
						if(userReponse.getLoginDate()>300000){//如果超过5分钟，在输错密码 就从1开始
							userRequest.setLoginFailNum(1);
						}else{
							// 在上次登录错误状态上+1
							userRequest.setLoginFailNum(userReponse.getLoginFailNum()+1);
							//返回给前台
							loginFailNum=userReponse.getLoginFailNum()+1;
						}
						map.put("loginFailNum", loginFailNum);//返回前台
					}	
					//修改登陆的次数
					userDao.updateLoginFailnum(userRequest);
			
			
			
			}else{
				//账号被锁定
				map.put("flag", Constant.LOGIN_LOCKED);
			}
			
		
		}else{
			
			//验证码错误
			map.put("flag", Constant.LOGIN_CODE_ERROR);
		}
	
		return map;
	}

	
	
	/**
	 * zree树
	 */
	@Override
	public List<RoleResponse> selectUserRoleListJson(RoleRequest roleRequest) {
		
		return userDao.selectUserRoleListJson(roleRequest);
	}

	/**
	 * 为用户赋角色
	 */
	@Override
	public void insertUserRoleList(List<RoleRequest> roleRequestList) {
		// 1、删除用户之前的所有角色（mid）
		userDao.deleteAllRolesByUserID(roleRequestList.get(0));
		// 2、添加用户勾选的所有角色（mid）
		userDao.insertUserRoleList(roleRequestList);
	}

	/**
	 * 新增用户信息
	 */
	@Override
	public void insertUser(UserRequest userRequest) {
		userDao.insertUser(userRequest);
		userRequest.setRoleID(1);
		userDao.insertUserRole(userRequest);
	}

	/**
	 * 删除用户信息
	 */
	@Override
	public void deleteUserInfo(UserRequest userRequest) {
		
		userDao.deleteUserInfo(userRequest);
	}

	/**
	 * 查询总条数
	 */
	@Override
	public int selectUserCount(UserRequest userRequest) {
		
		return userDao.selectUserCount(userRequest);
	}



	/**
	 * 查询用户信息
	 */
	@Override
	public List<UserReponse> selectUserList(UserRequest userRequest) {
		return userDao.selectUserList(userRequest);
	}



	/**
	 * 查询左侧树
	 */
	@Override
	public List<Map<String, Object>> selectTreeListJson(MenusRequest menusRequest) {
		List<Map<String, Object>> treeList =new ArrayList<Map<String, Object>>();
		//从缓存中获取权限列表
		String string = JedisUtil.getString(menusRequest.getUserID()+"#menu_list");
		//如果没有获取到，则查询数据库，
		if(null==string){
			//查询数据库，
			treeList=userDao.selectTreeListJson(menusRequest);
			//开始调用递归
			if (null != treeList && 0 < treeList.size()) {
				treeList = queryTreeListNodes(treeList, menusRequest);
			} 
			//把查到的结果放到redis上一份																设置过期的时间
			JedisUtil.setString(menusRequest.getUserID()+"#menu_list",JsonUtil.toJsonString(treeList),600);
			
		}else {
			//如果查到结果 直接返回
			//调用封装的json工具包
			treeList = JsonUtil.fromJson(string, new ArrayList<Map<String, Object>>(){}.getClass());
		}
		
		return treeList;
	}
	
	//递归查询树菜单
		private List<Map<String, Object>> queryTreeListNodes(List<Map<String, Object>> treeList, MenusRequest menusRequest) {
			for (Map<String, Object> map : treeList) {
				if ("0".equals(map.get("pid").toString())) {
					//取出ID作为下次查询的pid
					int pid = Integer.valueOf(map.get("id").toString());
					menusRequest.setPid(pid);
					//调用查询树的方法
					List<Map<String, Object>> queryTreeListNodes = 
							queryTreeListNodes(userDao.selectTreeListJson(menusRequest), menusRequest);
					map.put("nodes", queryTreeListNodes);
				}
			}
			return treeList;
		}


		/**
		 * 根据登录用户 查询用户查询用户权限树
		 */
		@Override
		public List<MenusResponse> selectMenusListJson(MenusRequest menusRequest) {
			return userDao.selectMenusListJson(menusRequest);
		}
		
		/**
		 * 根据指纹查询图片
		 */
		@Override
		public Upload selectUploadPhoto(Upload upload) {
			return userDao.selectUploadPhoto(upload);
		}
		
		/**
		 * <pre>insertUpload(上传文件信息 到数据库)   
		 * Founder：吴茜     
		 * Found_time：2017年8月5日 上午11:18:19    
		 * Updater：吴茜       
		 * Update_Time：2017年8月5日 上午11:18:19    
		 * Update_Remark： 
		 * @param upload</pre>
		 */
		@Override
		public void insertUpload(Upload upload) {
			userDao.insertUploadFile(upload);
		}



		/**
		 * <pre>selectUserById(根据ID  修改最回显)   
		 * Founder：吴茜     
		 * Found_time：2017年8月5日 上午11:19:08    
		 * Updater：吴茜       
		 * Update_Time：2017年8月5日 上午11:19:08    
		 * Update_Remark： 
		 * @param userRequest
		 * @return</pre>
		 */
		@Override
		public UserReponse selectUserById(UserRequest userRequest) {
			return userDao.selectUserById(userRequest);
		}



		/**
		 * <pre>updateUserInfo(修改用户信息)   
		 * Founder：吴茜     
		 * Found_time：2017年8月5日 上午11:18:04    
		 * Updater：吴茜       
		 * Update_Time：2017年8月5日 上午11:18:04    
		 * Update_Remark： 
		 * @param userRequest</pre>
		 */
		@Override
		public void updateUserInfo(UserRequest userRequest) {
			userDao.updateUserInfo(userRequest);
		}
	
}
