<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form id="infoForm" method="post"  enctype="multipart/form-data">
 <input type="hidden" name="userID"  value="${userReponse.userID}" >
 
  <div class="form-group">
    <label for="stuName">用户名</label>
    <input type="text" class="form-control" value="${userReponse.userName}" name="userName"  id="userName" placeholder="用户名">
  </div>
  
  <div class="form-group">
    <label for="exampleInputPassword1">密码</label>
    <input type="password" name="userPassword" value="${userReponse.userPassword}" class="form-control" placeholder="密码" />
  </div>
  
  <div class="form-group">
    <label for="stucWriter">昵称</label>
    <input type="text" class="form-control" value="${userReponse.usersName}"  name="usersName"  id="stucWriter" placeholder="昵称">
  </div>
  
   <div class="form-group">
    <label for="shelves">性别</label>
    <input type="radio" name="userSex" value="1" ${userReponse.userSex==1?"checked":""}>男
	<input type="radio" name="userSex" value="2" ${userReponse.userSex==2?"checked":""}>女
  </div>
  <div class="form-group">
    <label for="stucWriter">年龄</label>
    <input type="text" class="form-control" value="${userReponse.userAge}"  name="userAge"  id="userAge" placeholder="年龄">
  </div>
  <%-- <div class="form-group">
    <label for="proDate">出生日期</label>
    <input class="form-control" id="userDate" name="userDate" value="${userDate}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
  </div>
 --%>
  <div>
    <label for="proImage">用户头像</label>
     
    <input id="userPhoto"  type="file" />
   	<img alt="无图片" src="ftp://root:root@192.168.1.116:21/${userReponse.userPhoto}" id="userPhoto">
    <input  id="imgName" name="userPhoto"  type="hidden" />
   </div>
	
  
</form>


</body>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/uploadify/uploadify.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/uploadify/jquery.uploadify.min.js"></script>

 <script type="text/javascript">
	    //初始化一个uploadfiy上传控件
	    $(function(){
	    	$("#userPhoto").uploadify({
	    		'swf':'<%=request.getContextPath() %>/js/uploadify/uploadify.swf', //swf uploadify的控制展示属性 flash基础文件 上传的进度条 和上传按钮功能
	    		'uploader':'<%=request.getContextPath() %>/uploadUserPhoto.jhtml',//声明文件的上传地址 上传到对应的action请求
	    		'auto':true,
	    		'buttonText':'上传照片',//设置按钮文本值 默认值为 select files
	    		//'fileSizeLimit':1,
	    		'fileTypeDesc':'只能上传图片',
	    		'fileTypeExts':'*.jpg;*.png',
	    		//'multi':false
	    		 'queueSizeLimit':3,
	    		 'fileObjName':'userPhoto',
	    		 'onUploadSuccess':function(response,data){//第二个参数为后台返回的数据
		    			data = eval("("+data+")");
		    		 	alert(data.imgName);
		    		   // 替换图片原有路径 达到上传图片预览的目的
		    		    $("#userPhoto").attr("src","ftp://root:root@192.168.1.116:21/"+data.imgName); 
		    		    $("#imgName").val(data.imgName);//隐藏域的ID	
	    		 }
	    	})
	    })
	</script>

</html>