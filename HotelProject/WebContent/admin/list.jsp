<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%     String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>�û��б�</title>
		
		<!-- dialog -->
		<script type="text/javascript">
		function del() {
			if (comfirm("ȷ��Ҫɾ���ÿͻ���"))
				return true;
			return false;
		}
		</script>
	</head>
	
	<body>
		<%@ include file="/info/adminInfo.jsp" %>
		<h1><font color="red">�ͻ��б�</font></h1>
		
		<table border="1" width="80%" align="center">
			<tr>
				<td>���</td>
				<td>����</td>
				<td>�绰</td>
				<td>����</td>
				<td>ɾ��</td>
				<td>����</td>
			</tr>
			
			<s:iterator value="#request.list" id="us">
				<tr>
					<td><s:property value="#us.userid"/></td>
					<td><s:property value="#us.username"/></td>
					<td><s:property value="#us.mobile" /></td>
					<td><s:property value="#us.email" /></td>
					<td><a href="deleteUser.action?user.userid=%{us.userid}" onClick="return del();">delete</a></td>
					<td><a href="updateUser.action?user.userid=%{us.userid}">update</a></td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>