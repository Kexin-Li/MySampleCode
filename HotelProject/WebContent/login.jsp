<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>�û���¼</title>
	</head>
	
	<body>
		<center>
			<h1><font color="red">�û���¼</font></h1>
			<s:form action="userService">
				<s:textfield name="user.username" label="�û���" />
				<s:password name="user.password" label="����" />
				<s:submit method="login" value="��¼" /><br>
				<s:a href="/adminLogin.jsp">����Ա��¼</s:a>
				<s:a href="/register.jsp">���û�ע��</s:a><br><br><br>
			</s:form>
		</center>
	</body>
</html>