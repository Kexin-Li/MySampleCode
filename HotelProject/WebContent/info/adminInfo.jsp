<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>����Ա������ҳ</title>
	</head>
	
	<body>
		<h1><font color="red">����˵�</font></h1>
		<s:a href="/hotel/authorityAdmin/listUser.action">�ͻ�����</s:a>&nbsp;
		<s:a href="/hotel/authrityAdmin/listRoom.action">�������</s:a>&nbsp;
		<s:a href="/hotel/authrityAdmin/listOrder.action">��������</s:a>&nbsp;
		<s:a href="/hotel/authrityAdmin/adminService?loginOut">�˳�</s:a>
	</body>
</html>