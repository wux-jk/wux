package com.jk.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jk.pojo.UserReponse;
import com.jk.pojo.UserRequest;
import com.jk.pojo.role.Role;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;
import com.jk.service.UserService;
import com.jk.service.role.RoleService;

@Component
public class MyTask {
	
	/*@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	@Scheduled(cron="0 2 10 * * ?")
	public void sayHello(){
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("你好啊>>"+sd.format(new Date()));
	}*/
	
								//项目启动完 时间以后触发
	/*@Scheduled(fixedDelay=5000,initialDelay=5000)
	public void myTask(){
		UserRequest userRequest=new UserRequest();
		int selectUserCount = userService.selectUserCount(userRequest);
		userRequest.setTotalCount(selectUserCount);
		//调用util里的计算方法
		userRequest.calculate();
		List<UserReponse> selectUserList = userService.selectUserList(userRequest);
		for (UserReponse userReponse : selectUserList) {
			System.out.println(userReponse.getUserName());
		}
		SimpleDateFormat sd1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("我是频度》》"+sd1.format(new Date()));
	}*/
	
	/*@Scheduled(fixedDelay=5000,initialDelay=5000)
	public void getRole(){
		RoleRequest roleRequest=new RoleRequest();
		int selectRoleCount = roleService.selectRoleCount(roleRequest);
		roleRequest.setTotalCount(selectRoleCount);
		roleRequest.calculate();
		List<RoleResponse> selectRoleList = roleService.selectRoleList(roleRequest);
		for (RoleResponse roleResponse : selectRoleList) {
			System.out.println(roleResponse.getRoleName());
		}
		SimpleDateFormat sd1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("我是频度》》"+sd1.format(new Date()));
	}
*/
}
