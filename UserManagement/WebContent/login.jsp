<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Login</title>
	</head>
	<body>
	<center>
		<s:form action="login" method="post">
			<s:textfield name="username" label="用户名" />
			<s:password name="password" label="密码" />
			<s:textfield name="usertype" label="类型" />
			<s:submit value="提交" />
		</s:form>
	</center>
	</body>
</html>