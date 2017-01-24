<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>添加用户</title>
	</head>
	<body>
		<center>
			<s:form action="user" method="post">
			<tr>
				<td colspan="2" align="center">
					<h1><s:text name="欢迎注册" /></h1><br>
					<s:property value="exception.message" />
				</td>
			</tr>
			
			<s:textfield name="user.username" key="用户名" tooltip="Enter your name!" required="true" />
			<s:password name="user.password" key="密码" tooltip="Enter your password" required="true" />
			<s:textfield name="user.usertype" key="类型" tooltip="Enter your type" required="true" />
			<s:submit value="提交" />
			<s:set />
			</s:form>
		</center>
	</body>
</html>