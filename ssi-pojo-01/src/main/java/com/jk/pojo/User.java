package com.jk.pojo;

import java.util.Date;

import common.util.Page;

public class User extends Page{
	
	private Integer userID;
	
	private String userName;
	
	private String userPassword;
	
	private String userPhoto;//头像
	
	private String userMail;//邮箱账号
	
	private int loginFailNum;//失败的次数
	
	private String usersName;//昵称
	
	private String userSex;
	
	private Integer userAge;
	
	private Date userTime;
	
	
	private String userDate;//临时的

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public int getLoginFailNum() {
		return loginFailNum;
	}

	public void setLoginFailNum(int loginFailNum) {
		this.loginFailNum = loginFailNum;
	}

	public String getUsersName() {
		return usersName;
	}

	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public Date getUserTime() {
		return userTime;
	}

	public void setUserTime(Date userTime) {
		this.userTime = userTime;
	}

	public String getUserDate() {
		return userDate;
	}

	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}

	
	
	
	
	

}
