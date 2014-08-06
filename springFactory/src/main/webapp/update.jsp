<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Young ABIN</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
<script type="text/javascript"> 
	var bathPath = '<%=request.getContextPath()%>';
	function Validate() {
		if ($("#user").val() == "") {
			alert("用户名不能为空!");
			$("#user").focus();
			return false;
		}
		if ($("#oldpass").val() == "") {
			alert("旧密码不能为空");
			$("#oldpass").focus();
			return false;
		}
		if ($("#newpass1").val() == "") {
			alert("新密码不能为空");
			$("newpass1").focus();
			return false;
		}
		if ($("#newpass2").val() == "") {
			alert("新确认密码不能为空");
			$("newpass2").focus();
			return false;
		}
		if ($("#newpass1").val() != $("#newpass2").val()) {
			alert("两次填写的新密码不一样，请您重新填写");
			$("newpass1").focus();
			return false();
		}

		var username = $("input[name='username']").val();
		//  alert("用户名="+username); 
		var password = $("input[name='password']").val();
		//  alert("password="+password); 
		var password11 = $("input[name='password11']").val();
		//  alert("password1="+password11); 
		var password22 = $("input[name='password22']").val();

		$.ajax({
			url : bathPath + "/user/update.action",
			type : "post",
			data : ({
				initid : username,
				password : password,
				newpassword : password11
			}),
			success : function(data) {
				if (data.result == "notexist") {
					alert("该用户不存在，请您先注册");
				} else if (data.result == "success") {
					// alert("修改密码成功"); 
					document.write("修改密码成功")
					document.write("<br/>");
					window.location.href = "../login.jsp";
				} else if (data.result == "failure") {
					alert("旧密码不正确");
				} else if (data.result == "updatefailure") {
					alert("用户密码修改失败,请您重新修改");
				}
			}

		});

	}
</script>
<body>
	用户名：
	<input id="user" name="username" type="text" />
	<br /> 旧密码：
	<input id="oldpass" name="password" type="password" />
	<br /> 密码：
	<input id="newpass1" name="password11" type="password" />
	<br /> 确认密码：
	<input id="newpass2" name="password22" type="password" />
	<br />
	<input value="提交" type="submit" onClick="Validate()" />

	<input value="重置" type="reset" />
	<br />

</body>
</html>
