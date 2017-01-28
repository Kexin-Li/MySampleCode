<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>用户修改密码</title>
	</head>
	
	<body>
		<h1><font color="red">修改密码</font></h1>
		
		<s:form action="userService">
			<table>
				<tr><td><s:hidden name="user.userid" value="%{user.userid}" /></td></tr>
				<tr><td><s:textfield name="user.username" value="%{user.username}" label="用户名" /></td></tr>
				<tr><td><s:password name="user,password" value="%{user.password}" label="密码" /></td></tr>
				<tr><td><s:textfield name="user.mobile" value="%{user.mobile}" label="电话号码" /></td></tr>
				<tr><td><s:textfield name="user.email" value="%{user.email}" label="邮箱" /></td></tr>
				<tr><td><s:submit method="updateUser" value="修改" />
			</table>
		</s:form>
	</body>
</html>