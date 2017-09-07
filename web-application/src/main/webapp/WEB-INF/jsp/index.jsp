<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户管理</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.2.3/jquery-confirm.min.css">

<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="http://malsup.github.io/jquery.form.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.2.3/jquery-confirm.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/js/index.js"></script>
<!-- <script src="/js/search.js"></script>
<script src="/js/updatePass.js"></script> -->
<style type="text/css">
	.ss th {
		text-align: center;
	}
	.int{
		margin: o auto;
		margin-left: 50px;
		margin-right: 50px;
	}
</style>
<script type="text/javascript">
	//将用户id定义全局变量
	var userId = "${user_name.userId}";
	$(document).ready(function() {
		listUsers();
	});
</script>

</head>
<body>
	<!-- 导航栏 -->
	<div class="topDiv">
		<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
				<a class="activ navbar-brand" href="#" data-toggle="modal" data-target="#showModal">登录用户: ${user_name.username}</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<!-- 
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">批量操作 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#" onclick="exportExcel();">导出excel</a></li>
							<%-- <c:if test="${user_name.role == 1}"> --%>
								<li><a href="#" data-toggle="modal" data-target="#demoModal">导入excel</a></li>
							<%-- </c:if> --%>
							<li><a href="/file/用户信息表模板.xls">下载导入模板.xsl</a></li>
							<li><a href="/file/用户信息表模板.xlsx">下载导入模板.xlsx</a></li>
						</ul></li>
				</ul>
				 -->
				<form class="navbar-form navbar-left">
					<div class="form-group">
						<input type="text" id="searchInput" class="form-control" placeholder="根据用户名搜索">
					</div>
					<button type="button" class="btn btn-default" onclick="searchUsers();">搜索</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
					<a href="#" class="dropdown-toggle" style="padding-right: 100px;" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">个人中心
					<span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="#" data-toggle="modal" data-target="#updateModal">修改密码</a></li>
							<li><a onclick="loginOut();">退出登录</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<!-- 列表栏 -->
	<div class="panel-group">
		<div class="panel">
			<div class="panel-body">
				<div class="list-op" id="list_op">
					<button type="button" class="btn btn-default btn-sm" data-toggle="modal" onclick="openModal();">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
					</button>
					<button type="button" class="btn btn-default btn-sm" onclick="toUpdateUser();">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
					</button>
					<button type="button" id="deleteByIds" class="btn btn-default btn-sm" onclick="deleteByIds();">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
					</button>
				</div>
			</div>

			<table id="usersTable" class="table table-hover"
				style="width: 100%; margin: 0 auto; text-align: center;">
			</table>

			<ul class="pagination" id="page" style="margin-left: 40px;">
			</ul>
		</div>

		<!-- 新增与修改文件模态框（Modal）-->
		<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="demoModalLabel" aria-hidden="true" data-backdrop="static">
			<div class="modal-dialog" style="width: 600px;">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="demoModalLabel">
							<span>新增用户</span>
						</h4>
					</div>
					<div class="modal-body">
						<form id="addForm" method="post" action="">
							<input type="hidden" name="id" id="id" />
							<div class="form-group int">
								<label for="username">用户名：</label> 
								<input type="text" name="username" id="username" class="form-control" placeholder="请输入用户名" />
							</div>
							<div class="form-group int">
								<label for="userEmail">邮箱：</label> 
								<input type="text" name="userEmail" id="userEmail" class="form-control" placeholder="请输入邮箱" />
							</div>
							<div class="form-group int">
								<label for="userPhone">手机：</label> 
								<input type="text" name="userPhone" id="userPhone" class="form-control"	placeholder="请输入手机号码" />
							</div>
							<div class="form-group int">
								<label for="password">密码：</label> 
								<input type="password" name="password" id="password" class="form-control" placeholder="请输入密码" />
							</div>
							<div class="form-group int">
								<label for="password1">确认密码：</label> 
								<input type="password" id="password1" class="form-control" placeholder="请确认密码" />
							</div>
						</form>
					</div>
					<!-- /.modal-body -->
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							onclick="closeModal();">关闭</button>
						<button type="button" class="btn btn-primary"
							onclick="addOrUpdateUser()">提交</button>
					</div>
				</div>
			</div>
		</div>

		<!-- 修改密码模态框（Modal）-->
		<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
			aria-labelledby="demoModalLabel" aria-hidden="true"
			data-backdrop="static">
			<div class="modal-dialog" style="width: 600px;">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="demoModalLabel">
							<span>修改密码</span>
						</h4>
					</div>
					<div class="modal-body">
						<form id="updateForm" method="post" action="">
							<input type="hidden" name="id_1" id="id_1"
								value="${user_name.userId}" />
							<div class="form-group int">
								<label for="password">原密码：</label> <input type="password"
									name="oldPassword" id="oldPassword" class="form-control"
									placeholder="请输入原密码" />
							</div>
							<div class="form-group int">
								<label for="password">新密码：</label> <input type="password"
									name="newPassword" id="newPassword" class="form-control"
									placeholder="请输入新密码" />
							</div>
							<div class="form-group int">
								<label for="password1">确认密码：</label> <input type="password"
									id="password_2" class="form-control"
									placeholder="请确认新密码" />
							</div>
						</form>
					</div>
					<!-- /.modal-body -->
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary"
							onclick="updatePassword();">提交</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 上传文件模态框（Modal）-->
		<!-- <div class="modal fade" id="demoModal" tabindex="-1" role="dialog" aria-labelledby="demoModalLabel" aria-hidden="true" data-backdrop="static">
			<div class="modal-dialog" style="width: 800px; height: 500px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="demoModalLabel">批量导入用户</h4>
					</div>
					<div class="modal-body" style="height: 200px;">
						<form action="" method="post" id="importForm" enctype="multipart/form-data">
							<input type="file" name="file" id="files" /> 
							<input type="button" value="导入" onclick="importExecl();" />
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div> -->
		
		<!-- 个人信息 -->
		<div class="modal fade" id="showModal" tabindex="-1" role="dialog" aria-labelledby="demoModalLabel" aria-hidden="true" data-backdrop="static">
			<div class="modal-dialog" style="width: 600px;">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="demoModalLabel">
							<span>个人信息</span>
						</h4>
					</div>
					<div class="modal-body">
						<form id="showForm" action="">
							<input type="hidden" name="id" id="id" />
							<div class="form-group int">
								<label for="username">用户名：${user_name.username}</label>
							</div>
							<div class="form-group int">
								<label for="userEmail">邮箱：${user_name.userEmail}</label> 
							</div>
							<div class="form-group int">
								<label for="userPhone">手机：${user_name.userPhone}</label> 
							</div>
							<div class="form-group int">
								<label for="createTime">创建日期：${user_name.createTime}</label> 
							</div>
						</form>
					</div>
					<!-- /.modal-body -->
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		
</body>
</html>