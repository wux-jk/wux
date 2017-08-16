package com.jk.service.menus;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jk.dao.menus.MenusDao;
import com.jk.pojo.UserRequest;
import com.jk.pojo.menus.MenusRequest;
import com.jk.pojo.menus.MenusResponse;

@Service
public class MenusServiceImpl implements MenusService {
	
	@Resource
	private MenusDao menusDao;

	/**
	 * 查询菜单表
	 */
	@Override
	public List<MenusResponse> selectMenuListJson(MenusRequest menusRequest) {
		return menusDao.selectMenuListJson(menusRequest);
	}

	/**
	 * 删除权限
	 */
	@Override
	public void deleteMenusInfo(MenusRequest menusRequest) {
		
		menusDao.deleteMenusInfo(menusRequest);
	}

	/**
	 * 新增之前 查权限
	 */
	@Override
	public List<MenusResponse> selectFistMenuList(MenusRequest menusRequest) {
		return menusDao.selectFistMenuList(menusRequest);
	}

	/**
	 * 新增权限模块
	 */
	@Override
	public void insertMenus(MenusRequest menusRequest) {
		//判断是否是模块
		if(0==menusRequest.getPid()){
			menusRequest.setParent(true);
		}
		menusDao.insertMenus(menusRequest);
	}

}
