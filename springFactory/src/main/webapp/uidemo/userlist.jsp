<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/uidemo/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<h2>Basic DataGrid</h2>
	<p>The DataGrid is created from markup, no JavaScript code needed.</p>
	<div style="margin:20px 0;"></div>
	
	<table class="easyui-datagrid" title="用户表" style="width:650px;height:350px" data-options="
			singleSelect:true,
			collapsible:true,
			url:'/user/uilist_queryUserListByCriteria.action',
			pagination:true,
			method:'get'">
		<thead>
			<tr>
				<th data-options="field:'initid',width:80">主键</th>
				<th data-options="field:'username',width:100">用户名</th>
				<th data-options="field:'password',width:80">密码</th>
				<th data-options="field:'nickname',width:80">登陆账号</th>
				<th data-options="field:'born',width:160">时间</th>
			</tr>
		</thead>
	</table>
</body>
</html>
<script>
	
</script>