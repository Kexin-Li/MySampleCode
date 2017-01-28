<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>管理员管理首页</title>
	</head>
	
	<body>
		<h1><font color="red">管理菜单</font></h1>
		<s:a href="/hotel/authorityAdmin/listUser.action">客户管理</s:a>&nbsp;
		<s:a href="/hotel/authrityAdmin/listRoom.action">房间管理</s:a>&nbsp;
		<s:a href="/hotel/authrityAdmin/listOrder.action">订单管理</s:a>&nbsp;
		<s:a href="/hotel/authrityAdmin/adminService?loginOut">退出</s:a>
	</body>
</html>