<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%     String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>房间列表</title>
		
		<!-- dialog -->
		<script type="text/javascript">
		function del() {
			if (confirm("确定删除房间信息吗？")) 
				return true;
			return false;
		}
		</script>
	</head>
	
	<body>
		<%@ include file="/info/adminInfo.jsp" %>
		<h1><font color="red">房间列表</font></h1>
		
		<table border="1" width="80%" align="center">
			<tr>
				<td>房间序号</td>
				<td>房间名称</td>
				<td>房间价格</td>
				<td>房间类型</td>
				<td>房间状态</td>
				<td>删除</td>
				<td>更新</td>
			</tr>
			
			<s:iterator value="#request.list" id="room">
				<tr>
					<td><s:property value="#room.roomid" />
					<td><s:property value="#room.name" />
					<td><s:property value="#room.price" />
					<td><s:property value="#room.category" />
					<td><s:if test="#room.status == 0">空闲</s:if><s:else>已入住</s:else></td>
					<td><a href="deleteRoom.action?room.roomid=%{room.roomid}" onClick="return del();">delete</a>
					<td><a href="updateRoom.action?room.roomid=%{room.roomid}">update</a>
				</tr>
			</s:iterator>
		</table>		
	</body>
</html>