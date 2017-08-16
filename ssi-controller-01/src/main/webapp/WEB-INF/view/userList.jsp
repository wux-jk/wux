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
		<div class="btn-group">
		  <button type="button" class="btn btn-danger" onclick="export_user()">导出用户</button>
		</div>
	</div>
	
	
	 <!-- datagrid -->
 	<table id="userList"></table>

		
<script type="text/javascript">
	
			//初始化数据表格
			$('#userList').bootstrapTable({
				url:"<%=request.getContextPath() %>/selectUserListJson.jhtml",
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
			        field: 'userID',
			        title: 'id'
			    },
			    {
			        field: 'userName',
			        title: '账号'
			    }, 
			    {
			        field: 'usersName',
			        title: '昵称'
			    }, 
				{
			        field: 'userSex',
			        title: '性别',
			        formatter:function (value,row,index){
			    		  if(value==1){
			    			  return "男";
			    		  }else{
			    			  return "女";  
			    		  }
			    	  }	 
			    
			    },
			    {
			        field: 'userAge',
			        title: '年龄'
			    },
			    {field:'userPhoto',title:'用户头像',width:100,
					
			    	formatter:function(value,row,index){//value 当前字段值  row当前行的数据  index当前行
						return "<img  class='img-responsive img-circle'   width='100' heigth='100' src='ftp://root:root@192.168.1.116:21/"+value+"'/>";
					}
				},
			    /* {
			        field: 'userTime',
			        title: '生日',
			        //width:92,
			        formatter:formatDatebox
			    },  */
			    
			    {
			        field: 'cz',
			        title: '操作',
			        formatter:function(value, row, index) {
			        	var zc_btn_group = '<div class="btn-group">'
			        	+ '<button type="button" class="btn btn-xs btn-success" onclick="update_user(\'' + row.userID + '\')">编辑</button>'
			        	+ '</div>&nbsp;&nbsp;'
			        	+ '<div class="btn-group">'
			        	+ '<button type="button" class="btn btn-xs btn-danger" onclick="delete_checked_user(\'' + row.userID + '\')">删除</button>'
			        	+ '</div>&nbsp;&nbsp;'
			        	+ '<div class="btn-group">'
			        	+ '<button type="button" class="btn btn-xs btn-success" onclick="edit_user_role(\'' + row.userID + '\')">角色操作</button>'
			        	+ '</div>';
			        	return zc_btn_group;
			        }
			    }
			     
			   
			    ]
			});
	
</script>

<script type="text/javascript">
//给用户修改角色
function edit_user_role(user_id){
	BootstrapDialog.show({
		title:"用户赋角色",
		message: $('<div></div>').load('<%=request.getContextPath() %>/toUserRolePage.jhtml?userID=' + user_id),
		buttons: [{
            label: '确定',
            cssClass:"btn btn-success",
            action: function(dialogItself){
            	var role_json_array = get_selection_tree_nodes();//获取被选中的树节点的方法
            	//使用ajax保存结果
            	$.ajax({
            		url:"<%=request.getContextPath() %>/insertUserRoleList.jhtml",
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
//-----------------------------------------------------

//新增用户
function show_add_dialog(){
	BootstrapDialog.show({
		title:"新增用户",
		message:$('<div></div>').load('<%=request.getContextPath() %>/toAddUserPage.jhtml'),
		buttons: [{
            label: '确定',
            cssClass:"btn btn-success",
            action: function(dialogItself){
           
            	//使用ajax保存结果
            	$.ajax({
            		url:"<%=request.getContextPath() %>/insertUser.jhtml",
            		data:$("#infoForm").serialize(),
            		dataType:"json",
            		type:"post",
            		success:function(data) {
            			//刷新表格
            			$('#userList').bootstrapTable("refresh");
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

//-----------------------------------------------------

function delete_checked_user(id){
	alert(id)
		$.ajax({
			url:"<%=request.getContextPath()%>/deleteUserInfo.jhtml?userID="+id,
			type:"post",
			success:function(){
			
				$('#userList').bootstrapTable("refresh");
			}
		});
	

	
}
//-------------------------------------------
function export_user(){

		
		location.href="exeportAllUser.jhtml";
	
}
//-------------------------------------------------
//修改用户
function update_user(userID){
	alert(userID);
	BootstrapDialog.show({
		title:"修改用户",
		message:$('<div></div>').load('<%=request.getContextPath() %>/toAddUserPage.jhtml?userID='+userID),
		buttons: [{
            label: '确定',
            cssClass:"btn btn-success",
            action: function(dialogItself){
           
            	//使用ajax保存结果
            	$.ajax({
            		url:"<%=request.getContextPath() %>/updateUserInfo.jhtml",
            		data:$("#infoForm").serialize(),
            		dataType:"json",
            		type:"post",
            		success:function(data) {
            			//刷新表格
            			$('#userList').bootstrapTable("refresh");
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

</script>
		
</body>
</html>