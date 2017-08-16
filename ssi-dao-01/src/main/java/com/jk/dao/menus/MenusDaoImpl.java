package com.jk.dao.menus;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.jk.pojo.menus.MenusRequest;
import com.jk.pojo.menus.MenusResponse;

@Repository
public class MenusDaoImpl extends SqlMapClientDaoSupport implements MenusDao {

	/**
	 * 展示菜单信息
	 */
	@Override
	public List<MenusResponse> selectMenuListJson(MenusRequest menusRequest) {
		return this.getSqlMapClientTemplate().queryForList("menus.selectMenuListJson",menusRequest);
	}

	@Override
	public void deleteMenusInfo(MenusRequest menusRequest) {
		this.getSqlMapClientTemplate().delete("menus.deleteMenusInfo",menusRequest);
		
	}

	
	/**
	 * 查询权限
	 */
	@Override
	public List<MenusResponse> selectFistMenuList(MenusRequest menusRequest) {
		return this.getSqlMapClientTemplate().queryForList("menus.selectFistMenuList",menusRequest);
	}

	/**
	 * 新增权限模块
	 */
	@Override
	public void insertMenus(MenusRequest menusRequest) {
		this.getSqlMapClientTemplate().insert("menus.insertMenus",menusRequest);
	}

	
}
