<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>A Login Project</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <dir style="test-align:center">
    <form name="form1" method="post">
    <table style="margin:auto">
    <tr>
    	<td colspan="2">Welcome to A Login Project</td>
   	</tr>  	
   	<tr>
   		<td>username:</td>
   		<td><input type="text" name="username"></td>
   	</tr> 	
   	<tr>
   		<td>password:</td>
   		<td><input type="password" name="password"></td>
   	</tr>
   	<tr>
   		<td cosplan="2" align="center">
   		<button type="button" name="" value="" onClick="login()">login</button></td>
   		<td cosplan="2" align="center"><button type="button" name="" value="" onClick="javascript:location.href='register.jsp'">register</button>
    	</td>
    </tr>
    </table></form>
    </dir>
  </body>
</html>
