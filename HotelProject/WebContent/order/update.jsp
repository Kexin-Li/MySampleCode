<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%     String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>修改房间信息</title>
	</head>
	
	<body>
		<%@ include file="/info/adminInfo.jsp" %>
		<h1><font color="red">修改房间</font></h1>
		
		<s:form action="updateRoom">
			<table>
			
				<!-- hidden -->
				<tr>
					<td><s:hidden name="room.roomid" value="%{room.roomid}"></s:hidden></td>
				</tr>
				
				<!-- textfield -->
				<tr>
					<td><s:textfield name="room.name" value="%{room.name}" label="房间名称" readonly="true"></s:textfield></td>
				</tr>
				<tr>
					<td><s:textfield name="room.price" value="%{room.price}" label="房间价格" ></s:textfield></td>
				</tr>
				<tr>
					<td><s:textfield name="room.category" value="%{room.category}" label="房间类型"></s:textfield></td>
				</tr>
				
				<tr>
					<td>
						<tr>
							<td class="tdLebel">房间状态</td>
							<s:if test="room.status == 0">
								<td>
									<select name="room.status">
										<option value="0" selected="selected">空闲</option>
										<option value="1">已入住</option>
									</select>
								</td>
							</s:if>
							<s:else>
								<td><select name="room.struts">
									<option value="0" selected="selected">空闲</option>
									<option value="1">已入住</option> 
								</select>
							</s:else>
						</tr>
				<!-- td, tr -->
				<s:submit value="修改" />
			</table>
		</s:form>
	</body>
</html>