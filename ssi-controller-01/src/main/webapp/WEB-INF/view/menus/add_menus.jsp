<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="add_menu_form">
		<table>
			<tr>
				<td>所属模块：</td>
				<td>
					<select name="pid">
						<option value="0">新增模块</option>
						<c:forEach items="${menuList }" var="menu">
						<option value="${menu.id }">${menu.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td>菜单名称：</td>
				<td><input name="menuName"></td>
			</tr>
			<tr>
				<td>菜单链接：</td>
				<td><input name="url"></td>
			</tr>
			<tr>
				<td>菜单类型：</td>
				<td>
					<input name="menuType" type="radio" value="0" checked="checked">常规功能
					<input name="menuType" type="radio" value="1">ajax请求
					<input name="menuType" type="radio" value="2">基本功能
					<input name="menuType" type="radio" value="3">按钮功能<br>
				</td>
			</tr>
		</table>
	
		
	</form>
</body>
</html>