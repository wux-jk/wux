package com.jk.service;

import java.util.List;
import java.util.Map;

import com.jk.pojo.User;
import com.jk.pojo.UserReponse;
import com.jk.pojo.UserRequest;
import com.jk.pojo.menus.MenusRequest;
import com.jk.pojo.menus.MenusResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;
import com.jk.pojo.upload.Upload;

import common.util.Page;

public interface UserService {

	Map<String, Object> login(UserRequest userRequest);


	List<RoleResponse> selectUserRoleListJson(RoleRequest roleRequest);

	void insertUserRoleList(List<RoleRequest> roleRequestList);

	void insertUser(UserRequest userRequest);

	void deleteUserInfo(UserRequest userRequest);

	int selectUserCount(UserRequest userRequest);

	List<UserReponse> selectUserList(UserRequest userRequest);


	List<Map<String, Object>> selectTreeListJson(MenusRequest menusRequest);


	List<MenusResponse> selectMenusListJson(MenusRequest menusRequest);


	Upload selectUploadPhoto(Upload upload);


	void insertUpload(Upload upload);


	UserReponse selectUserById(UserRequest userRequest);


	void updateUserInfo(UserRequest userRequest);


	
	
	



}
