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
		<ul id="tt" class="easyui-tree" data-options="url:'<%=request.getContextPath()%>/user/tree_queryMenu.action'"></ul>
	</div>
	<div data-options="region:'center',title:''"
		style="padding: 5px; background: #eee;">
		<div id="mainTab" class="easyui-tabs" style="width: 1050px; height: 450px;"></div>
	</div>
</body>
</html>
<script>
$("#tt").tree({
    onBeforeExpand:function(node,param){
    	$('#tt').tree('options').url = bathPath + '/user/tree_queryMenu.action?parentMenuId=' + node.id;// change the url                       
    },
    onClick: function(node){
    	var menuUrl = node.attributes.url;
    	if(menuUrl == null || menuUrl == ""){
    		return;
    	}

    	var tabObj = $('#mainTab').tabs("exists", node.text);
    	if(!tabObj){
	    	$('#mainTab').tabs('add',{
	    	    title: node.text,
	    	    href: bathPath + menuUrl,
	    	    closable:true  
	    	});
    	}
    }
});


</script>