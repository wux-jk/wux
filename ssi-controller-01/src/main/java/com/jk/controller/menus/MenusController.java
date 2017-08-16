package com.jk.controller.menus;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.pojo.UserRequest;
import com.jk.pojo.menus.MenusRequest;
import com.jk.pojo.menus.MenusResponse;
import com.jk.service.menus.MenusService;


@Controller
public class MenusController {
	
	@Resource
	private MenusService menusService;
	
	
	//跳转到菜单表
	@RequestMapping("toMenuListPage")
	String toMenuListPage() {
		return "menus/menu_list";
	}
	
	/**
	 * <pre>selectMenuListJson(查询菜单表)   
	 * Founder：吴茜     
	 * Found_time：2017年7月27日 下午8:17:35    
	 * Updater：吴茜       
	 * Update_Time：2017年7月27日 下午8:17:35    
	 * Update_Remark： 
	 * @param menuRequest
	 * @return</pre>
	 */
	@RequestMapping("selectMenuListJson")
	@ResponseBody
	List<MenusResponse> selectMenuListJson(MenusRequest menusRequest) {
		List<MenusResponse> menuList = menusService.selectMenuListJson(menusRequest);
		return menuList;
	}
	
	/**
	 * <pre>deleteMenuInfo(删除权限（菜单）)   
	 * Founder：吴茜     
	 * Found_time：2017年7月27日 下午9:40:00    
	 * Updater：吴茜       
	 * Update_Time：2017年7月27日 下午9:40:00    
	 * Update_Remark： 
	 * @param userRequest
	 * @return</pre>
	 */
	@RequestMapping("deleteMenuInfo")
 	@ResponseBody
 	String deleteMenuInfo(MenusRequest menusRequest){
		
		menusService.deleteMenusInfo(menusRequest);
		return "{}";
 		
 		
 	}
	
	//跳转到新增权限页面
	@RequestMapping("toAddMenuPage")
	String toAddMenuPage(Model m){
		//在跳转到新增权限页面时  已有的权限查出来 一级列表
		List<MenusResponse>	menuList=menusService.selectFistMenuList(new MenusRequest());
		m.addAttribute("menuList", menuList);
		return "menus/add_menus";
		
	}
	
	@RequestMapping("insertMenus")
	@ResponseBody
	String insertMenus(MenusRequest menusRequest) {
		
		menusService.insertMenus(menusRequest);
		//重定向
		return "{}";
	}

}
