<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>�û���Ԥ�������б�</title>
		<script type="text/javascript">
		function del() {
			if (confirm("��ȷ��Ҫ�˶��÷�����")) 
				return true;
			return false;
		}
		</script>
	</head>
	
	<body>
		<%@ include file="/info/userInfo.jsp" %>
		<h1><font color="red">��Ԥ���ķ����б�</font></h1>
		
		<table border="1" width="80%" align="center">
			<tr>
				<td>�������</td>
				<td>��������</td>
				<td>����۸�</td>
				<td>�����ͺ�</td>
				<td>����</td>
			</tr>
			
			<s:iterator value="#request.listUserRoom" id="room">
				<tr>
					<td><s:property value="#room.roomid" /></td>
					<td><s:property value="#room.name" /></td>
					<td><s:property value="#room.price" /></td>
					<td><s:property value="#room.category" /></td>
					<td><s:a href="/hotel/orderService!delete?room.roomid=%{#room.roomid}" onClick="return del();">�˶�</s:a></td>
			</tr>
			</s:iterator>
		</table>
	</body>
</html>