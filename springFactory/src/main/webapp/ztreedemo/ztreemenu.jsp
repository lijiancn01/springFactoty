<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/uidemo/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>menu</title>
</head>
<body class="easyui-layout">
	<div
		data-options="region:'north',title:'',split:true,href:'<%=request.getContextPath()%>/uidemo/top.jsp'"
		style="height: 57px;"></div>
	<div data-options="region:'south',title:'',split:true"
		style="height: 34px;" id="copyright" class="group">
		亚信联创科技（中国）有限公司 版权所有</div>
	<div data-options="region:'west',title:'系统菜单',split:true"
		style="width: 300px;">
		<iframe src="./treeleft.jsp" width="98%" height="98%"></iframe>
	</div>
	<div data-options="region:'center',title:''"
		style="padding: 5px; background: #eee;">
		<div id="mainTab" class="easyui-tabs" style="width: 1050px; height: 450px;"></div>
	</div>
</body>
</html>
<script>
var menuMap = new Map();
$('#mainTab').tabs({
	onBeforeClose: function(title,index){
		var tab = $('#mainTab').tabs('getTab', index);
		menuMap.remove(tab.panel('options').id);
	}
});

function addTab(treeNode){
	if(menuMap.size() >= 8){
		return;
	}
	var old = menuMap.get(treeNode.id);
	if(old){
		return;
	}else{
		$('#mainTab').tabs('add',{
			id: treeNode.id,
		    title: treeNode.name,
		    href: bathPath + treeNode.file,
		    closable:true  
		});
		menuMap.put(treeNode.id, treeNode);
	}
}
</script>