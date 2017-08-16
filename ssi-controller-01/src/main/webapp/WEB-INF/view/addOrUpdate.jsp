<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ include file="/common/mystyle.jsp" %>
        
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 引入fileinput的js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fileinput/js/fileinput.min.js"></script>
<!--  引入fileinput的js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fileinput/js/locales/zh.js"></script>
<!-- 引入fileinput的css -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/js/fileinput/css/fileinput.min.css" />
</head>
</head>
<body>
${book }
	<form action="<%=request.getContextPath()%>/addOrUpdate.jhtml" method="post" enctype="multipart/form-data">
		<input type="hidden" name="bookID" value="${book.bookID }">
		<input type="hidden" value="${book.bookImg }" name="bookImg" id="bookImg">
		<table border="1px">
			<tr>
				<td>书籍名称:</td>
				<td><input type="text" name="bookName" value="${book.bookName }"></td>
			</tr>
			<tr>
				<td>书籍价格:</td>
				<td><input type="text" name="bookPrice" value="${book.bookPrice }"></td>
			</tr>
			<tr>
					<td>学生头像:</td>
					<td>
					<img src="<%=request.getContextPath()%>${book.bookImg}"  width="80px"/>
					<input type="file"  name="upImg"/></td>
				</tr>
		</table>
		<input type="submit" value="提交">
		
		
		
	</form> 
	
<%-- 	
<form id="infoForm" method="post" >
	<input type="hidden" name="bookID" value="${book.bookID }">
 
  <div class="form-group">
    <label for="stuName">名字</label>
    <input type="text" class="form-control" value="${book.bookName}"  name="bookName"  id="bookName" placeholder="学生姓名">
  </div>
  
  <div class="form-group">
    <label for="exampleInputPassword1">价格</label>
    <input type="number" name="bookPrice" value="${book.bookPrice}" class="form-control" placeholder="学费" />
  </div>
  
  <div class="form-group">
    <label for="stucWriter">班主任</label>
    <input type="text" class="form-control" value="${map.stucWriter}"  name="map['stucWriter']"  id="stucWriter" placeholder="班主任">
  </div>
  <div class="form-group">
    <label for="proDate">入学日期</label>
    <input class="form-control" id="stuTime" name="map['stuTime']" value="${map.stuTime}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
  </div>
  <div class="form-group">
    	<label for="stuType">所在班级</label>
    	<select  name="map['stuType']" id="stuType" class="form-control">
     		<option value="">请选择</option>
	  		<option value="1" ${map.stuType==1?"selected":""}>Java1702A</option>
	  		<option value="2" ${map.stuType==2?"selected":""}>Java1702B</option>
	  		<option value="3" ${map.stuType==3?"selected":""}>前端1702A</option>
	  		<option value="4" ${map.stuType==4?"selected":""}>前端1702B</option>
	  		
		</select> 
		<!-- 如果类型是string类型的话  用复选框 -->
  		 <input type="checkbox" name="map['musicType']" value="1" ${map.musicType.contains('1')?"checked":""}>流行 
		<input type="checkbox" name="map['musicType']" value="2" ${map.musicType.contains('2')?"checked":""}>伤感 
		<input type="checkbox" name="map['musicType']" value="3" ${map.musicType.contains('3')?"checked":""}>摇滚  
  </div>
  <!-- <div class="form-group">
    <label for="proImage">学生照片</label>
    <input class="form-control" id="stuImg" name="stuImg"  type="file" />这的name值 必须和web层 相对应
    <input class="form-control" id="imgName" name="map['stuImg']"  type="hidden" />
   </div> -->
   <div class="form-group">
    <label for="shelves">是否住宿</label>
    <input type="radio" name="map['shelves']" value="1" ${map.shelves==1?"checked":""}>是
	<input type="radio" name="map['shelves']" value="2" ${map.shelves==2?"checked":""}>否
  </div>
</form> --%>
</body>
 <script type="text/javascript">
		function addOrUpdate(){
			var data = $("#addOrUpdate").serialize();
			alert(data)
			$.ajax({
				type:"post",
				data:data,
				url:"<%=request.getContextPath()%>/addOrUpdate.jhtml"
			})
		}
	</script> 
</html>