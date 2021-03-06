package com.jk.service.role;

import java.util.List;

import com.jk.pojo.UserReponse;
import com.jk.pojo.UserRequest;
import com.jk.pojo.menus.MenusRequest;
import com.jk.pojo.menus.MenusResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;

public interface RoleService {

	int selectRoleCount(RoleRequest roleRequest);

	List<RoleResponse> selectRoleList(RoleRequest roleRequest);

	List<MenusResponse> selectRoleMenuListJson(MenusRequest menusRequest);

	void insertRoleMenuList(List<MenusRequest> menusRequestList);

	void insertRole(RoleRequest roleRequest);

	void deleteroleInfo(RoleRequest roleRequest);

	

	

	

}
