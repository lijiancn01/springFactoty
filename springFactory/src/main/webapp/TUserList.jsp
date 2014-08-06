<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%String path=request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
</head>

<body>	
	<s:iterator value="userlist">
		<li>
			<s:property value="username" />
			<s:property value="nickname" />
			<s:property value="born" />
			<a href="javascript:del('<s:property value="initid" />')">删除</a>
		</li>
	</s:iterator>
</body>
</html>
<script>
function del(initid){
	
}
</script>