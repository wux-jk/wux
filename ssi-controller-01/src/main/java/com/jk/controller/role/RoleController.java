package com.jk.controller.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.pojo.UserReponse;
import com.jk.pojo.UserRequest;
import com.jk.pojo.menus.MenusRequest;
import com.jk.pojo.menus.MenusResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;
import com.jk.service.role.RoleService;

@Controller
public class RoleController {
	
	@Resource
	private RoleService roleService;
	
	
	//跳转到角色展示页面
	@RequestMapping("toRoleListPage")
	String toRoleListPage() {
		return "role/roleList";
	}
	
	//跳转到角色页面
	@RequestMapping("toAddRolePage")
	String toAddRolePage(){
	return "role/addMenus";
		
	}
	
	/**
	 * <pre>insertRole(新增角色信息)   
	 * Founder：吴茜     
	 * Found_time：2017年7月27日 下午9:00:57    
	 * Updater：吴茜       
	 * Update_Time：2017年7月27日 下午9:00:57    
	 * Update_Remark： 
	 * @param roleRequest
	 * @return</pre>
	 */
	@RequestMapping("insertRole")
	@ResponseBody
	String insertRole(RoleRequest roleRequest) {
		
		roleService.insertRole(roleRequest);
		//重定向
		return "{}";
	}

	/**
	 * <pre>deleteUserInfo(删除角色操作)   
	 * Founder：吴茜     
	 * Found_time：2017年7月27日 下午9:01:36    
	 * Updater：吴茜       
	 * Update_Time：2017年7月27日 下午9:01:36    
	 * Update_Remark： 
	 * @param userRequest
	 * @return</pre>
	 */
	@RequestMapping("deleteroleInfo")
 	@ResponseBody
 	String deleteroleInfo(RoleRequest roleRequest){
		
		roleService.deleteroleInfo(roleRequest);
		return "{}";
 		
 		
 	}

	//点击菜单操作 跳到树展示角色菜单的页面
	@RequestMapping("toRoleMenusPage")
	String toRoleMenusPage(Model m, RoleRequest roleRequest) {
		m.addAttribute("roleID", roleRequest.getRoleID());
		return "role/role_menu";
	}
	
	/**
	 * <pre>insertRoleMenuList(把之前的角色菜单信息删掉，重新添加角色信息，修改)   
	 * Founder：吴茜     
	 * Found_time：2017年7月27日 下午5:09:48    
	 * Updater：吴茜       
	 * Update_Time：2017年7月27日 下午5:09:48    
	 * Update_Remark： 
	 * @param menuRequestList
	 * @return</pre>
	 */
	@RequestMapping("insertRoleMenusList")
	@ResponseBody
	String insertRoleMenusList(@RequestBody List<MenusRequest> menusRequestList) {
		roleService.insertRoleMenuList(menusRequestList);
		return "{}";
	}
	
	/**
	 * <pre>selectRoleMenuListJson(查询树角色菜单被勾选的信息)   
	 * Founder：吴茜     
	 * Found_time：2017年7月27日 下午12:09:29    
	 * Updater：吴茜       
	 * Update_Time：2017年7月27日 下午12:09:29    
	 * Update_Remark： 
	 * @param menuRequest
	 * @return</pre>
	 */
	@RequestMapping("selectRoleMenuListJson")
	@ResponseBody
	List<MenusResponse> selectRoleMenuListJson(MenusRequest menusRequest) {
		List<MenusResponse> menuList = roleService.selectRoleMenuListJson(menusRequest);
		return menuList;
	}
	
	/**
	 * <pre>selectRoleListJson(查询角色信息)   
	 * Founder：吴茜     
	 * Found_time：2017年7月27日 上午10:23:10    
	 * Updater：吴茜       
	 * Update_Time：2017年7月27日 上午10:23:10    
	 * Update_Remark： 
	 * @param pageNumber
	 * @param userRequest
	 * @return</pre>
	 */
	 @RequestMapping("selectRoleListJson")
	 @ResponseBody
	Map<String,Object> selectRoleListJson(String pageNumber,RoleRequest roleRequest){
		
		//查询总条数
			int totalCount = roleService.selectRoleCount(roleRequest);
			roleRequest.setTotalCount(totalCount);
			if(null == pageNumber || "".equals(pageNumber.trim())){
				pageNumber = "1";
			}
			roleRequest.setPageIndex(Integer.valueOf(pageNumber));
			//计算分页信息 调用util封装的计算方法
			roleRequest.calculate();
			//查询列表
			 List<RoleResponse> roleList=roleService.selectRoleList(roleRequest);
			//封装结果
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", totalCount);
			map.put("rows", roleList);
			 return map;
		 
	 }

}
