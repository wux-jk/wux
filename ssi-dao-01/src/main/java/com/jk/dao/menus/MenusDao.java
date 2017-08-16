package com.jk.dao.menus;

import java.util.List;

import com.jk.pojo.menus.MenusRequest;
import com.jk.pojo.menus.MenusResponse;

public interface MenusDao {

	List<MenusResponse> selectMenuListJson(MenusRequest menusRequest);

	
	void deleteMenusInfo(MenusRequest menusRequest);


	List<MenusResponse> selectFistMenuList(MenusRequest menusRequest);


	void insertMenus(MenusRequest menusRequest);

	

}
