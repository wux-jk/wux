package com.jk.pojo;

public class UserRequest extends User{
	
	private String userImgCode;
	
	private String sysImgCode;
	
	private int roleID;

	public String getUserImgCode() {
		return userImgCode;
	}

	public void setUserImgCode(String userImgCode) {
		this.userImgCode = userImgCode;
	}

	public String getSysImgCode() {
		return sysImgCode;
	}

	public void setSysImgCode(String sysImgCode) {
		this.sysImgCode = sysImgCode;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	
	

}
