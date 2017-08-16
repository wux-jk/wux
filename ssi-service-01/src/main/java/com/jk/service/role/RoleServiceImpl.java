package com.jk.service.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jk.dao.role.RoleDao;
import com.jk.pojo.UserReponse;
import com.jk.pojo.UserRequest;
import com.jk.pojo.menus.MenusRequest;
import com.jk.pojo.menus.MenusResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Resource
	private RoleDao roleDao;

	/**
	 * 查询总条数
	 */
	@Override
	public int selectRoleCount(RoleRequest roleRequest) {
		return roleDao.selectRoleCount(roleRequest);
	}

	/**
	 * 查询角色信息
	 */
	@Override
	public List<RoleResponse> selectRoleList(RoleRequest roleRequest) {
		return roleDao.selectRoleList(roleRequest);
	}

	/**
	 * 查询树角色菜单被勾选的信息
	 */
	@Override
	public List<MenusResponse> selectRoleMenuListJson(MenusRequest menusRequest) {
		return roleDao.selectRoleMenuListJson(menusRequest);
	}

	/**
	 * 修改角色菜单
	 */
	@Override
	public void insertRoleMenuList(List<MenusRequest> menusRequestList) {
		//先删除已有的菜单
		roleDao.deleteRoleMenusById(menusRequestList.get(0));
		//在新增角色菜单
		roleDao.insertRoleMenuList(menusRequestList);
	}

	/**
	 * 新增角色信息
	 */
	@Override
	public void insertRole(RoleRequest roleRequest) {
		roleDao.insertRole(roleRequest);
	}

	@Override
	public void deleteroleInfo(RoleRequest roleRequest) {
		roleDao.deleteroleInfo(roleRequest);
	}


	

	

	
}
