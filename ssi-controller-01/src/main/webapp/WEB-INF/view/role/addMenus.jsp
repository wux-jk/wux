<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<title>Insert title here</title>

</head>
<body>
	<!-- class="form-inline" -->
<form id="roleForm" method="post" >
 
  <div class="form-group">
    <label for="roleName">角色名称</label>
    <input type="text" class="form-control" value="${roleName}" name="roleName"  id="roleName" placeholder="角色名称">
  </div>
  
  
  <div class="form-group">
    <label for="roleDesc">角色描述</label>
    <input type="text" class="form-control" value="${roleDesc}"  name="roleDesc"  id="roleDesc" placeholder="角色描述">
  </div>
  
  
  
</form>


</body>
</html>