<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>�û��޸�����</title>
	</head>
	
	<body>
		<h1><font color="red">�޸�����</font></h1>
		
		<s:form action="userService">
			<table>
				<tr><td><s:hidden name="user.userid" value="%{user.userid}" /></td></tr>
				<tr><td><s:textfield name="user.username" value="%{user.username}" label="�û���" /></td></tr>
				<tr><td><s:password name="user,password" value="%{user.password}" label="����" /></td></tr>
				<tr><td><s:textfield name="user.mobile" value="%{user.mobile}" label="�绰����" /></td></tr>
				<tr><td><s:textfield name="user.email" value="%{user.email}" label="����" /></td></tr>
				<tr><td><s:submit method="updateUser" value="�޸�" />
			</table>
		</s:form>
	</body>
</html>