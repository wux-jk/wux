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
		<div class="btn-group">
		  <button type="button" class="btn btn-danger" onclick="delete_all_user()">删除</button>
		</div>
	</div>
	
	 <!-- datagrid -->
 	<table id="roleList"></table>
 	
<script type="text/javascript">

//初始化数据表格
$('#roleList').bootstrapTable({
	url:"<%=request.getContextPath() %>/selectRoleListJson.jhtml",
	dataType:"json",
	//请求方式
	method:"post",
	//必须的，！！！！不然会造成中文乱码
	contentType: "application/x-www-form-urlencoded",
	//斑马线
	striped:true,
	//设置分页											
    pagination:true,
	paginationLoop:true,
	pageNumber:1,
	pageSize:3,
	pageList:[3,5,8,10],
	queryParamsType:"",
	//工具条
	/* toolbar:"#book_tb", */
	//设置后台分页
	 sidePagination:"server", 								
	//开启搜索框
	/* search:true, */				
	//显示刷新按钮
	showRefresh:true,
    columns: [{
        checkbox:true
    }, 
    {
    	title: 'id',
    	field: 'roleID',
    },
    {
        title: '角色名称',
        field: 'roleName',
    }, 
    {
        title: '角色描述',
        field: 'roleDesc',
    }, 
	
   
  
    {
        field: 'cz',
        title: '操作',
        formatter:function(value, row, index) {
        	var zc_btn_group = '<div class="btn-group">'
        	+ '<button type="button" class="btn btn-xs btn-success" onclick="show_edit_dialog(\'' + row.bookID + '\')">编辑</button>'
        	+ '</div>&nbsp;&nbsp;'
        	+ '<div class="btn-group">'
        	+ '<button type="button" class="btn btn-xs btn-danger" onclick="delete_checked_role(\'' + row.roleID + '\')">删除</button>'
        	+ '</div>&nbsp;&nbsp;'
        	+ '<div class="btn-group">'
        	+ '<button type="button" class="btn btn-xs btn-success" onclick="edit_role_menus(\'' + row.roleID + '\')">菜单操作</button>'
        	+ '</div>';
        	return zc_btn_group;
        }
    }
     
   
    ]
});

</script> 

<script type="text/javascript">
//给角色赋予菜单
function edit_role_menus(role_id){
	BootstrapDialog.show({
		title:"角色赋菜单",
		message: $('<div></div>').load('<%=request.getContextPath() %>/toRoleMenusPage.jhtml?roleID=' + role_id),
		buttons: [{
            label: '确定',
            cssClass:"btn btn-success",
            action: function(dialogItself){
            	var role_json_array = get_selection_tree_nodes();//获取被选中的树节点的方法
            	//使用ajax保存结果
            	$.ajax({
            		url:"<%=request.getContextPath() %>/insertRoleMenusList.jhtml",
            		data:JSON.stringify(role_json_array),
            		dataType:"json",
            		type:"post",
            		success:function(data) {
            			//关闭对话框
            			dialogItself.close();
            		},
            		contentType:"application/json"
            	});
            }
        }, {
            label: '取消',
            cssClass:"btn btn-danger",
            action: function(dialogItself){
            	dialogItself.close();
            }
        }]	
	
	
	
	
	});
		

}

//--------------------------------------------
//新增角色
function show_add_dialog(){
	
	BootstrapDialog.show({
		title:"新增角色",
		message:$('<div></div>').load('<%=request.getContextPath() %>/toAddRolePage.jhtml'),
		buttons: [{
            label: '确定',
            cssClass:"btn btn-success",
            action: function(dialogItself){
           
            	//使用ajax保存结果
            	$.ajax({
            		url:"<%=request.getContextPath() %>/insertRole.jhtml",
            		data:$("#roleForm").serialize(),
            		dataType:"json",
            		type:"post",
            		success:function(data) {
            			//刷新表格
            			$('#roleList').bootstrapTable("refresh");
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

function delete_checked_role(id){
	alert(id)
	$.ajax({
		url:"<%=request.getContextPath()%>/deleteroleInfo.jhtml?roleID="+id,
		type:"post",
		success:function(){
		
			$('#roleList').bootstrapTable("refresh");
		}
	});

	
	
}



</script>	
</body>
</html>