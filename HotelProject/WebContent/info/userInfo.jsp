<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>用户管理首页</title>
	</head>
	
	<body>
		<h1><font color="red">操作菜单</font></h1>
		<s:a href="userService!userUpdateInfo">修改密码</s:a>&nbsp;
		<s:a href="saveOrder.action">预定房间</s:a>&nbsp;
		<s:a href="orderService">查看预订信息</s:a>&nbsp;
		<s:a href="userService!loginOut">退出</s:a>
	</body>
</html>