<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%     String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>添加管理员</title>
	</head>
	
	<body>
		<%@ include file="/info/adminInfo.jsp" %>
		<h1><font color="red">添加管理员</font></h1>
		
		<s:form action="saveAdmin">
			<s:textfield name="admin.username" label="用户名" />
			<s:password name="admin.password" label="密码" />
			<s:submit value="添加" />
		</s:form>
	</body>
</html>