package com.jk.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jk.pojo.User;
import com.jk.pojo.UserReponse;
import com.jk.pojo.UserRequest;
import com.jk.pojo.menus.MenusRequest;
import com.jk.pojo.menus.MenusResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;
import com.jk.pojo.upload.Upload;

@Repository
public class UserDaoImpl extends SqlMapClientDaoSupport implements UserDao {

	/**
	 * 登录
	 */
	@Override
	public UserReponse login(UserRequest userRequest) {
																			//和user.xml命名空间  有个ID对应上
		return (UserReponse) this.getSqlMapClientTemplate().queryForObject("user.login",userRequest);
	}

	//验证码输入错误  加1
	@Override
	public void updateLoginFailnum(UserRequest userRequest) {
		this.getSqlMapClientTemplate().update("user.loginFailNum",userRequest);
	}

	
	
	/**
	 * 根据用户ID 查询用户对应的权限
	 */
	@Override
	public List<RoleResponse> selectUserRoleListJson(RoleRequest roleRequest) {
		
		return getSqlMapClientTemplate().queryForList("user.selectUserRoleListJson", roleRequest);
	}

	/**
	 * 根据ID删除原来的用户已存在的权限
	 */
	@Override
	public void deleteAllRolesByUserID(RoleRequest roleRequest) {
		this.getSqlMapClientTemplate().delete("user.deleteAllRolesByUserID", roleRequest);
		
	}

	/**
	 * 重新添加用户权限
	 */
	@Override
	public void insertUserRoleList(final List<RoleRequest> roleRequestList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {

			@Override
			public Object doInSqlMapClient(SqlMapExecutor sqlMap) throws SQLException {
				//开启批量
				sqlMap.startBatch();
				//添加批量操作语句
				for (RoleRequest roleRequest : roleRequestList) {
					sqlMap.insert("user.insertUserRole", roleRequest);
				}
				//执行批量操作
				sqlMap.executeBatch();
				return null;
			}
		});
	}

	/**
	 * 新增用户信息
	 */
	@Override
	public void insertUser(UserRequest userRequest) {
		this.getSqlMapClientTemplate().insert("user.insertUser",userRequest);
		
	}

	/**
	 * 删除用户信息
	 */
	@Override
	public void deleteUserInfo(UserRequest userRequest) {
		
		getSqlMapClientTemplate().delete("user.deleteUserInfo",userRequest);
	}

	/**
	 * 查询总条数
	 */
	@Override
	public int selectUserCount(UserRequest userRequest) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("user.selectUserCount",userRequest);
	}

	/**
	 * 查用户
	 */
	@Override
	public List<UserReponse> selectUserList(UserRequest userRequest) {
		return this.getSqlMapClientTemplate().queryForList("user.selectUserList", userRequest);
	}

	/**
	 * 查询树
	 */
	@Override
	public List<Map<String, Object>> selectTreeListJson(MenusRequest menusRequest) {
		return this.getSqlMapClientTemplate().queryForList("user.selectTreeListJson", menusRequest);
	}

	@Override
	public void insertUserRole(UserRequest userRequest) {
		this.getSqlMapClientTemplate().insert("user.insertUserRole",userRequest);
	}

	/**
	 * 根据用户登录查询用户权限树
	 */
	@Override
	public List<MenusResponse> selectMenusListJson(MenusRequest menusRequest) {
		return this.getSqlMapClientTemplate().queryForList("user.selectMenusListJson",menusRequest);
	}
	
	/**
	 * <pre>selectUploadPhoto(根据指纹查询)   
	 * Founder：吴茜     
	 * Found_time：2017年8月4日 下午8:42:32    
	 * Updater：吴茜       
	 * Update_Time：2017年8月4日 下午8:42:32    
	 * Update_Remark： 
	 * @param upload
	 * @return</pre>
	 */
	@Override
	public Upload selectUploadPhoto(Upload upload) {
		return (Upload) this.getSqlMapClientTemplate().queryForObject("user.selectUploadPhoto",upload);
	}
	
	/**
	 * <pre>insertUploadFile(新增文件图片信息)   
	 * Founder：吴茜     
	 * Found_time：2017年8月4日 下午8:52:38    
	 * Updater：吴茜       
	 * Update_Time：2017年8月4日 下午8:52:38    
	 * Update_Remark： 
	 * @param upload</pre>
	 */
	@Override
	public void insertUploadFile(Upload upload) {
		this.getSqlMapClientTemplate().insert("user.insertUploadFile",upload);
	}

	/***
	 * 修改先查询ID 做回显
	 */
	@Override
	public UserReponse selectUserById(UserRequest userRequest) {
		return (UserReponse) this.getSqlMapClientTemplate().queryForObject("user.selectUserById",userRequest);
	}

	/**
	 * 修改用户信息
	 */
	@Override
	public void updateUserInfo(UserRequest userRequest) {
		this.getSqlMapClientTemplate().update("user.updateUserInfo",userRequest);
	}
}
