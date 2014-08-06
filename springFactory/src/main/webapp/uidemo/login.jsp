<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/uidemo/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>menu</title>
<style type="text/css">
label{width:100px;}
</style>
</head>
<body>
	<div id="p" class="easyui-panel" title="User login"
		style="width: 500px; height: 150px; top: 200px; left: 300px; padding: 10px; background: #fafafa;"
		data-options="iconCls:'icon-save',closable:false,
	                collapsible:true,minimizable:false,maximizable:false">
		<form id="ff" method="post">
			<div>
				<label for="name">Name:</label>
				<input class="easyui-validatebox" type="text" name="name" data-options="required:true" />
			</div>
			<div>
				<label for="email">Email:</label>
				<input class="easyui-validatebox" type="text" name="email" data-options="validType:'email'" />
			</div>
			<div>
				<input type="submit" value="提交"/>
			</div>
		</form>
	</div>
</body>
</html>
<script>

$('#ff').form({    
    //url: bathPath + "/test/loginAction.do",
    url: bathPath + "/user/login.do?method=check",
    onSubmit: function(param){
    	param.userName = "EBC002";
    	param.password = "689180";
    },    
    success:function(data){
    	var data = eval('(' + data + ')');  // change the JSON string to javascript object    
        if (data.FLAG != 'success'){    
            alert(data.MSG)    
        }else{
    		window.open(bathPath + "/test/loginSuccessAction.do");
        }
    }    
});

</script>