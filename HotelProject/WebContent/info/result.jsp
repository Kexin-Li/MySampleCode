<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>房间预定成功</title>
	</head>

	<body>
		<%@include file="/info/userInfo.jsp"%>
		<br><br><br>
		提示：<s:property value="message" />
	</body>
</html>