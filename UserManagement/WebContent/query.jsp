<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>显示用户信息</title>
	</head>
	<body>
		<center>
			<h1>用户信息</h1>
			<table border="1" width="400">
				<tr>
					<th>用户 ID</th>
					<th>用户名</th>
					<th>密码</th>
					<th>用户类型</th>
					<th>是否删除</th>
					<th>是否修改</th>
				</tr>
				<!-- 对集合元素进行迭代 -->
				<s:iterator value="#request.userlist" id="st">
					<tr>
						<td align="center"><s:property value="#st.id" /></td>
						<td align="center"><s:property value="#st.username"/></td>
						<td align="center"><s:property value="#st.password"/></td>
						<td align="center"><s:property value="#st.usertype"/></td>
						<!-- 定义超链接 -->
						<td><a href="userdelete.action?id=<s:property value='#st.id' /> " >删除</a></td>
						<td><a href="update.jsp?id=<s:property value='#st.id' /> " >更新</a></td>
					</tr>
				</s:iterator>
			</table>
			<br>
				<a href="save.jsp">添加用户</a>
		</center>
	</body>
</html>