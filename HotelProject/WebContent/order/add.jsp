<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%     String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>���ӷ���</title>
	</head>
	
	<body>
		<%@ include file="/info/adminInfo.jsp" %>
		<h1><font color="red">���ӷ���</font></h1>
		<s:form action="saveRoom">
			<s:textfield name="room.name" label="��������" />
			<s:textfield name="room.price" label="����۸�" />
			<s:textfield name="room.category" label="��������" />
			
			<tr>
				<td class="tdLabel">����״̬</td>
				<td>
					<select name="room.status">
						<option value="0" selected="selected">����</option>
						<option value="1">����ס</option>
					</select>
				</td>
			</tr>
			<s:submit value="����" />
		</s:form>
	</body>
</html>