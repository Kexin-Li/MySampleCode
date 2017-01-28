<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>用户登录</title>
	</head>
	
	<body>
		<center>
			<h1><font color="red">用户登录</font></h1>
			<s:form action="userService">
				<s:textfield name="user.username" label="用户名" />
				<s:password name="user.password" label="密码" />
				<s:submit method="login" value="登录" /><br>
				<s:a href="/adminLogin.jsp">管理员登录</s:a>
				<s:a href="/register.jsp">新用户注册</s:a><br><br><br>
			</s:form>
		</center>
	</body>
</html>