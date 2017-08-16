package com.jk.service.menus;

import java.util.List;

import com.jk.pojo.UserRequest;
import com.jk.pojo.menus.MenusRequest;
import com.jk.pojo.menus.MenusResponse;

public interface MenusService {

	List<MenusResponse> selectMenuListJson(MenusRequest menusRequest);

	void deleteMenusInfo(MenusRequest menusRequest);

	List<MenusResponse> selectFistMenuList(MenusRequest menusRequest);

	void insertMenus(MenusRequest menusRequest);

}
