<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 表工具栏 -->
	<div>
	
		<div class="btn-group">
		  <button type="button" class="btn btn-success" onclick="show_add_dialog()">新增</button>
		</div>
		
	</div>
	
	 <!-- datagrid -->
 	<table id="menusList"></table>
 	
<script type="text/javascript">
$(function() {
	init_menu_list();
});
//初始化菜单列表
function init_menu_list() {
	$('#menusList').treegridData({
        id: 'id',
        parentColumn: 'pid',
        type: "GET", //请求数据的ajax类型
        url: '<%=request.getContextPath() %>/selectMenuListJson.jhtml',   //请求数据的ajax的url
        ajaxParams: {}, //请求数据的ajax的data属性
        expandColumn: null,//在哪一列上面显示展开按钮
        striped: true,   //是否各行渐变色
        bordered: true,  //是否显示边框
        //expandAll: false,  //是否全部展开
        columns: [
            {
                title: '菜单ID',
                field: 'id'
            }, {
                title: '菜单名称',
                field: 'name'
            }, {
                title: '菜单链接',
                field: 'url'
            }, {
                title: '菜单父ID',
                field: 'pid'
            }, {
            	title: '操作',
            	formatter:function(row) {
            		var zc_btn_group = '<div class="btn-group">'
			        	+ '<button type="button" class="btn btn-xs btn-success" onclick="show_edit_dialog(\'' + row.id + '\')">编辑</button>'
			        	+ '</div>&nbsp;&nbsp;'
			        	+ '<div class="btn-group">'
			        	+ '<button type="button" class="btn btn-xs btn-danger" onclick="delete_checked_menus(\'' + row.id + '\')">删除</button>'
			        	+ '</div>';
			    	return zc_btn_group;
            	}
            }
        ]
    });
}



</script> 


<script type="text/javascript">

//--------------------------------------------
//新增权限
function show_add_dialog(){
	
	BootstrapDialog.show({
		title:"新增角色",
		message:$('<div></div>').load('<%=request.getContextPath() %>/toAddMenuPage.jhtml'),
		buttons: [{
            label: '确定',
            cssClass:"btn btn-success",
            action: function(dialogItself){
           
            	//使用ajax保存结果
            	$.ajax({
            		url:"<%=request.getContextPath() %>/insertMenus.jhtml",
            		data:$("#add_menu_form").serialize(),
            		dataType:"json",
            		type:"post",
            		success:function(data) {
            			$("#menusList").html("");
            			init_menu_list();
            			//刷新表格
            			$('#menusList').bootstrapTable("refresh");
            			//关闭对话框
            			dialogItself.close();
            		},
            	});
            }
        }, 
        {
            label: '取消',
            cssClass:"btn btn-danger",
            action: function(dialogItself){
            	dialogItself.close();
            }
        }]
	});
	
}

//---------------------------------------------
function delete_checked_menus(menuID){
	alert(menuID)
	$.ajax({
		url:"<%=request.getContextPath()%>/deleteMenuInfo.jhtml?menuID="+menuID,
		type:"post",
		data:{"menuID":menuID.trim()},
		success:function(){
			//刷新表格
			$("#menusList").html("");
			init_menu_list();
			$('#menusList').bootstrapTable("refresh");
		}
	});

	
	
}
</script>	
</body>
</html>