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
		style="height: 100px; padding: 10px; background: #fafafa">
		<table style="width: 680px; height: 40px">
			<tr>
				<td><label for="name">Name:</label> <input
					class="easyui-validatebox" type="text" name="name"
					data-options="required:true" /></td>
			</tr>
			<tr>
				<td align="center"><a id="btn" href="#"
					class="easyui-linkbutton" onclick="queryUser()"
					data-options="iconCls:'icon-search'">查询</a></td>
			</tr>
		</table>
	</div>
	<table id="tt"></table>
	<div id="win">
		<form id="addForm">
			<div>
				<label for="add_userName">姓名:</label> <input
					class="easyui-validatebox" type="text" name="add_userName"
					data-options="required:true" value="李剑" />
			</div>
			<div>
				<label for="add_userPwd">密码:</label> <input
					class="easyui-validatebox" type="text" name="add_userPwd"
					data-options="required:true" value="12345678" />
			</div>
			<div>
				<label for="add_nickName">账号:</label> <input
					class="easyui-validatebox" type="text" name="add_nickName"
					data-options="required:true" value="EVC012" />
			</div>
			<div>
				<a id="addCommitbtn" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-save'" onclick="addUser()">提交</a>
			</div>
		</form>
	</div>
</body>
</html>
<script type="text/javascript">
	//定义全局变量记录分页信息
	var oPage = {
		pageIndex : 1,
		pageSize : 20
	};

	$(function() {
		//初始化dategrid
		$('#tt').datagrid({
			url : null,
			pagination : true,
			pageSize : 20,
			pageNumber : 1,
			rownumbers : true,
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			}, {
				title : 'initid',
				field : 'initid',
				width : 280,
			//sortable : true
			}, {
				field : 'username',
				title : 'username',
				width : 120
			}, {
				field : 'password',
				title : 'password',
				width : 120,
			//sortable : true
			}, {
				field : 'born',
				title : 'born',
				width : 180
			} ] ],
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
			} ]
		});
		
		$('#tt').datagrid('getPager').pagination({
			onSelectPage:function(pageNumber, pageSize){
				oPage.pageIndex = pageNumber;
            	oPage.pageSize = pageSize;
            	queryUser();
			}
		});
		
		$('#win').window({    
		    width:600,    
		    height:400,    
		    modal:true
		});
		$('#win').window('close');  // close a window

	});

	function queryUser() {
		var data = '&userName=' + $("input[name=name]").val();
		data += '&pageIndex=' + oPage.pageIndex;
		data += '&pageSize=' + oPage.pageSize;
		$.ajax({
			url : bathPath + "/user/map_queryUserListByCriteria2.action",
			metnod : "post",
			data : data,
			success : function(data) {
				if (data.Flag != 'success') {
					alert(data.Msg);
				} else {
					$('#tt').datagrid('loadData', {
						"total" : data.total,
						"rows" : data.rows
					});
				}
			}
		});
	}
	
	function addUser(){
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
					queryUser();
				}
	    	}
		});
	}
</script>