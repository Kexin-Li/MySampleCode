<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%     String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>�����б�</title>
		
		<!-- dialog -->
		<script type="text/javascript">
		function del() {
			if (confirm("ȷ��ɾ��������Ϣ��")) 
				return true;
			return false;
		}
		</script>
	</head>
	
	<body>
		<%@ include file="/info/adminInfo.jsp" %>
		<h1><font color="red">�����б�</font></h1>
		
		<table border="1" width="80%" align="center">
			<tr>
				<td>�������</td>
				<td>��������</td>
				<td>����۸�</td>
				<td>��������</td>
				<td>����״̬</td>
				<td>ɾ��</td>
				<td>����</td>
			</tr>
			
			<s:iterator value="#request.list" id="room">
				<tr>
					<td><s:property value="#room.roomid" />
					<td><s:property value="#room.name" />
					<td><s:property value="#room.price" />
					<td><s:property value="#room.category" />
					<td><s:if test="#room.status == 0">����</s:if><s:else>����ס</s:else></td>
					<td><a href="deleteRoom.action?room.roomid=%{room.roomid}" onClick="return del();">delete</a>
					<td><a href="updateRoom.action?room.roomid=%{room.roomid}">update</a>
				</tr>
			</s:iterator>
		</table>		
	</body>
</html>