<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%     String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>�޸ķ�����Ϣ</title>
	</head>
	
	<body>
		<%@ include file="/info/adminInfo.jsp" %>
		<h1><font color="red">�޸ķ���</font></h1>
		
		<s:form action="updateRoom">
			<table>
			
				<!-- hidden -->
				<tr>
					<td><s:hidden name="room.roomid" value="%{room.roomid}"></s:hidden></td>
				</tr>
				
				<!-- textfield -->
				<tr>
					<td><s:textfield name="room.name" value="%{room.name}" label="��������" readonly="true"></s:textfield></td>
				</tr>
				<tr>
					<td><s:textfield name="room.price" value="%{room.price}" label="����۸�" ></s:textfield></td>
				</tr>
				<tr>
					<td><s:textfield name="room.category" value="%{room.category}" label="��������"></s:textfield></td>
				</tr>
				
				<tr>
					<td>
						<tr>
							<td class="tdLebel">����״̬</td>
							<s:if test="room.status == 0">
								<td>
									<select name="room.status">
										<option value="0" selected="selected">����</option>
										<option value="1">����ס</option>
									</select>
								</td>
							</s:if>
							<s:else>
								<td><select name="room.struts">
									<option value="0" selected="selected">����</option>
									<option value="1">����ס</option> 
								</select>
							</s:else>
						</tr>
				<!-- td, tr -->
				<s:submit value="�޸�" />
			</table>
		</s:form>
	</body>
</html>