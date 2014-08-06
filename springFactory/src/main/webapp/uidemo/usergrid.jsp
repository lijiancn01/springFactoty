<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/uidemo/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="p" class="easyui-panel" title="查询条件"
        style="height:100px;padding:10px;background:#fafafa">
		<table style="width:680px;height:40px">   
				<tr>
					<td>
						<label for="name">Name:</label>
			        	<input class="easyui-validatebox" type="text" name="name" data-options="required:true" />
					</td>
				</tr>
				<tr>
					<td>
						<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="query()">查询</a>
					</td>
				</tr>
		</table>
	</div>
	<table id="test"></table>
	<div id="win">
		<form id="addForm">
			<div>
				<label for="add_userName">姓名:</label>
				<input class="easyui-validatebox" type="hidden" name="add_uuid" data-options="required:true" />
				<input class="easyui-validatebox" type="text" name="add_userName" data-options="required:true" value="李剑" />
			</div>
			<div>
				<label for="add_userPwd">密码:</label>
				<input class="easyui-validatebox" type="text" name="add_userPwd" data-options="required:true" value="12345678" />
			</div>
			<div>
				<label for="add_nickName">账号:</label>
				<input class="easyui-validatebox" type="text" name="add_nickName" data-options="required:true" value="EVC012" />
			</div>
			<div>
				<a id="addCommitbtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提交</a>
			</div>
		</form>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function() {
		$('#test').datagrid({
			title : '用户列表',
			iconCls : 'icon-ok',
			//width : 870,
			height : 450,
			nowrap : false,
			striped : true,
			url : '/user/uilist_queryUserListByCriteria.action',
			//sortName : 'initid',
			//sortOrder : 'desc',
			//idField : 'initid',
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			}, {
				title : 'initid',
				field : 'initid',
				width : 280,
				//sortable : true
			} ] ],
			columns : [ [ {
				title : '基本信息',
				colspan : 4
			}, {
				field : 'opt',
				title : '操作',
				width : 100,
				align : 'center',
				rowspan : 2,
				formatter : function(value,row,index) {
					
				}
			} ], [ {
				field : 'username',
				title : 'username',
				width : 120
			}, {
				field : 'password',
				title : 'password',
				width : 120,
				//sortable : true
			}, {
				field : 'nickname',
				title : 'nickname',
				width : 180
			}, {
				field : 'born',
				title : 'born',
				width : 180
			} ] ],
			pagination : true,
			rownumbers : true,
			singleSelect : false,
			onDblClickRow: function(rowIndex, rowData){
				$("input[name=add_userName]").val(rowData.username);
				$("input[name=add_userPwd]").val(rowData.password);
				$("input[name=add_nickName]").val(rowData.nickname);
				$("input[name=add_uuid]").val(rowData.initid);
				$('#win').window('open');  // close a window
			    $('#addCommitbtn').bind('click', function(){
			    	updateUser();
			    });
			},
			toolbar : [ {
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					$('#win').window('open');  // open a window
				}
			}, '-', {
				text : '保存',
				iconCls : 'icon-save',
				handler : function() {
					$('#win').window('open');  // open a window
					//$('#win').window('refresh', 'get_content.php');
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					delUser();
				}
			}, '-', {
				text : 'clear',
				iconCls : 'icon-mini-refresh',
				handler : function() {
					//$("#test").datagrid("clearChecked");
					$("#test").datagrid("clearSelections");
				}
			} ]
		});
		
		$('#win').window({    
		    width:600,    
		    height:400,    
		    modal:true
		});
		$('#win').window('close');  // close a window  
		
	    $('#delbtn').bind('click', function(){
	    	
	    });
	    
	    $('#editbtn').bind('click', function(){
	    	alert("edit");
	    });
	    
	});
	
	function query(){
		$('#test').datagrid('load',{userName:$("input[name=name]").val()});
	}
	
	function addUser(){
	    $('#addCommitbtn').bind('click', function(){
	    	addUser();
	    });
		var data = '&userName=' + $("input[name=add_userName]").val();
		data += '&password=' + $("input[name=add_userPwd]").val();
		data += '&nickName=' + $("input[name=add_nickName]").val();
		$.ajax({
			url: bathPath + "/user/map_addUserMap.action",
			metnod: "post",
			data: data,
			success: function(data){
				if(data.Flag != 'success'){
					alert(data.Msg);
				}else{
					$('#win').window('close');  // close a window
					$.messager.show({
						title:'添加用户',
						msg:'添加用户成功',
						timeout:5000,
						showType:'slide'
					});
					query();
				}
	    	}
		});
	}
	
	function updateUser(){
		var data = '&userName=' + $("input[name=add_userName]").val();
		data += '&password=' + $("input[name=add_userPwd]").val();
		data += '&nickName=' + $("input[name=add_nickName]").val();
		data += '&uuid=' + $("input[name=add_uuid]").val();
		$.ajax({
			url: bathPath + "/user/map_updateUserMap.action",
			metnod: "post",
			data: data,
			success: function(data){
				if(data.Flag != 'success'){
					alert(data.Msg);
				}else{
					$('#win').window('close');  // close a window
					$.messager.show({
						title:'修改用户',
						msg:'修改用户成功',
						timeout:5000,
						showType:'slide'
					});
					query();
				}
	    	}
		});
	}
	
	function delUser(){
		var rows = $("#test").datagrid("getSelections");
		var inits = [];
		for(var i = 0; i < rows.length; i ++){
			inits.push(rows[i].initid);
		}
		var data = '&inits=' + inits;
		$.ajax({
			url: bathPath + "/user/map_delUserMap.action",
			metnod: "post",
			data: data,
			success: function(data){
				if(data.Flag != 'success'){
					alert(data.Msg);
				}else{
					$.messager.show({
						title:'删除用户',
						msg:'删除用户成功',
						timeout:5000,
						showType:'slide'
					});
					query();
				}
	    	}
		});
	}
</script>