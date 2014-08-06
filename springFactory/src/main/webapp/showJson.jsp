<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
</head>
<body>
	<table>
		<tr>
			<td>url</td>
			<td><input id="url" type="text" value="/user/tree_queryMenu.action?parentMenuId=0" style="width:400px" /></td>
		</tr>
		<tr>
			<td>type</td>
			<td><input id="type" type="text" value="POST" /></td>
		</tr>
		<tr>
			<td>data</td>
			<td><input id="data" type="text" value="" /></td>
		</tr>
	</table>
	<button onclick="aa()">提交</button>
	<br>
	<textarea rows="20" cols="100" id="result"></textarea>
</body>
</html>
<script>
	function aa(){
		var url = '<%=request.getContextPath()%>' + $("#url").val();
		var type = $("#type").val();
		var data = $("#data").val();
		$("#result").val('');
		$.ajax({
			  url: url,
			  type:type,
			  data:data,
			  cache: false,
			  success: function(json){
			      $("#result").val(JSON.stringify(json));
			  }
			});
	}
</script>