package com.jk.dao.role;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jk.pojo.UserReponse;
import com.jk.pojo.menus.MenusRequest;
import com.jk.pojo.menus.MenusResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;

@Repository
public class RoleDaoImpl extends SqlMapClientDaoSupport implements RoleDao {

	/**
	 * 查询总条数
	 */
	@Override
	public int selectRoleCount(RoleRequest roleRequest) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("role.selectRoleCount",roleRequest);
		
	}

	/**
	 * 查询角色信息
	 */
	@Override
	public List<RoleResponse> selectRoleList(RoleRequest roleRequest) {
		return this.getSqlMapClientTemplate().queryForList("role.selectRoleList",roleRequest);
	}

	/**
	 * 查询角色菜单被勾选的信息
	 */
	@Override
	public List<MenusResponse> selectRoleMenuListJson(MenusRequest menusRequest) {
		return this.getSqlMapClientTemplate().queryForList("role.selectRoleMenuListJson",menusRequest);
	}

	/**
	 * 修改角色菜单
	 * 先删除已有的角色菜单
	 */
	@Override
	public void deleteRoleMenusById(MenusRequest menusRequest) {
		this.getSqlMapClientTemplate().delete("role.deleteRoleMenusById",menusRequest);
	}

	/**
	 * 新增角色菜单
	 */
	@Override
	public void insertRoleMenuList(final List<MenusRequest> menusRequestList) {
		//匿名内部类  批量操作
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {

			@Override
			public Object doInSqlMapClient(SqlMapExecutor sqlMap) throws SQLException {
				sqlMap.startBatch();
				for (MenusRequest menusRequest : menusRequestList) {
					sqlMap.insert("role.insertRoleMenu",menusRequest);
				}
				sqlMap.executeBatch();
				return null;
			}
		});
	}

	/**
	 * 新增角色
	 */
	@Override
	public void insertRole(RoleRequest roleRequest) {
		this.getSqlMapClientTemplate().insert("role.insertRole",roleRequest);
	}
	
	/**
	 * 删除角色操作
	 */
	@Override
	public void deleteroleInfo(RoleRequest roleRequest) {
		this.getSqlMapClientTemplate().delete("role.deleteroleInfo",roleRequest);
	}

}
