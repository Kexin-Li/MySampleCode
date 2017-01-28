<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%     String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>修改用户信息</title>
	</head>
	
	<body>
		<%@ include file="/info/adminInfo.jsp" %>
		<h1><font color="red">修改用户</font></h1>
		
		<s:form action="updateUser">
			<table>
				<tr><td><s:hidden name="user.userid" value="%{user.userid}" /></td></tr>
				<tr><td><s:textfield name="user.username" value="%{user.username}" /></td></tr>
				<tr><td><s:password name="user.password" value="%{user.password}" /></td></tr>
				<tr><td><s:textfield name="user.mobile" value="%{user.mobile}" /></td></tr>
				<tr><td><s:textfield name="user.email" value="%{user.email}" /></td></tr>
				<tr><td><s:submit value="修改" /></td></tr>
			</table>
		</s:form>
	</body>
</html>