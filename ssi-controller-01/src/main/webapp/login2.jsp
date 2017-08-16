<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录-有点</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
 <link rel="stylesheet" type="text/css" href="css/public.css" />
<link rel="stylesheet" type="text/css" href="css/page.css" /> 
<script type="text/javascript" src="js/ajaxSetup.js"></script>
 <script type="text/javascript" src="js/public.js"></script> 
</head>
<body>
	<form id="loginForm">
	<!-- 登录页面头部 -->
	<div class="logHead">
		<img src="img/logLOGO.png" />
	</div>
	<!-- 登录页面头部结束 -->

	<!-- 登录body -->
	<div class="logDiv">
		<img class="logBanner" src="img/logBanner.png" />
		<div class="logGet">
			<!-- 头部提示信息 -->
			<div class="logD logDtip">
				<p class="p1">登录</p>
				<p class="p2">后台管理系统</p>
			</div>
			<!-- 输入框 -->
			<div class="lgD">
				<img class="img1" src="img/logName.png" /><input type="text"
					placeholder="输入用户名" name="userName"/>
			</div>
			<div class="lgD">
				<img class="img1" src="img/logPwd.png" /><input type="password"
					placeholder="输入用户密码" name="userPassword"/>
			</div>
			<div class="lgD">
				<img class="img1" src="img/logPwd.png" /><input  name="userImgCode"
					placeholder="输入验证码" />
				<span onclick="change_imgcode()">
					<img id="imgcode_src_node"  src="imgcode">
					<font color="red">看不清，点击换一张</font>
				</span>
			</div>
			<center>
			
				<input type="button" class="logC" value="登录" onclick="login()">
			
			</center>
		</div>
	</div>
	<!-- 登录body  end -->

	<!-- 登录页面底部 -->
	<div class="logFoot">
		<p class="p1">版权所有：南京设易网络科技有限公司</p>
		<p class="p2">南京设易网络科技有限公司 登记序号：苏ICP备11003578号-2</p>
	</div>
</form>
	
	
<script type="text/javascript"> 


	$(function(){
		change_imgcode();
	})
	 




function login(){

	$.ajax({
		type:"post",
		url:"login.jhtml",
		data:$("#loginForm").serialize(),
		dataType:"json",
		success:function(result){
			
			if(1==result.flag){
				alert("登录成功");
				//成功
				location.href = "index.jsp";
			}else if(2==result.flag){
				alert("用户不存在");
				return;
			}else if(3==result.flag){
				alert("密码错误"+result.loginFailNum);
				return;
			}else if(4==result.flag){
				alert("验证码错误");
				return;
			}else if(5==result.flag){
				alert("验证码为空");
				return;
			}else {
				alert("账号被锁定");
				return;
			}
		}
		
		
		
		
	})
}






	 //切换验证码
function change_imgcode() {
	
	$("#imgcode_src_node").attr("src","<%=request.getContextPath() %>/imgcode?time=" + new Date().getTime());
}



</script>	
	
</body>
</html>