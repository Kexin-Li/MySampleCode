<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%     String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>用户列表</title>
		
		<!-- dialog -->
		<script type="text/javascript">
		function del() {
			if (comfirm("确定要删除该客户吗？"))
				return true;
			return false;
		}
		</script>
	</head>
	
	<body>
		<%@ include file="/info/adminInfo.jsp" %>
		<h1><font color="red">客户列表</font></h1>
		
		<table border="1" width="80%" align="center">
			<tr>
				<td>序号</td>
				<td>姓名</td>
				<td>电话</td>
				<td>邮箱</td>
				<td>删除</td>
				<td>更新</td>
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