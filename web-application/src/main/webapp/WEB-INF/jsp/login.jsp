<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.2.3/jquery-confirm.min.css">
<!-- 可选的Bootstrap主题文件（一般不使用） -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.2.3/jquery-confirm.min.js"></script>
<script type="text/javascript">
function login() {
	var data = $("#loginForm").serialize();
	$.ajax({
		url : '/userLogin',
		type : "POST",
		data : data,
		dataType : "json",
		success : function(data) {
			if (!data.success){
				$.alert(data.message);
			} else {
				window.location.href = '/index';
			}
		}
	});
}
</script>
</head>

<body ng-app="app" ng-controller="MainController">
<div class="container">
	<div class="row">
		<div class="span12">
			<h3 class="text-center">
			欢迎登录
			</h3>
		</div>
	</div>
</div>
<form id="loginForm" class="form-horizontal" role="form">
	<div class="form-group">
		<label for="firstname" class="col-sm-5 control-label">账号</label>
		<div class="col-sm-2">
			<input ng-model="username" type="text" class="form-control" name="username" id="username" placeholder="请输入账号">
		</div>
	</div>
	<div class="form-group">
		<label for="lastname" class="col-sm-5 control-label">密码</label>
		<div class="col-sm-2">
			<input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-5 col-sm-7">
			<button type="button" class="btn btn-default"  value="登录" onclick="login();" >登录</button>
			<button type="reset" class="btn btn-default"  value="重置" >重置</button>
		</div>
	</div>
</form>
</body>
</html>