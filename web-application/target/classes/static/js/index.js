/**
 * 显示所有用户
 * @param pageNow
 */
function listUsers(pageNow) {
	if (pageNow == null)
		pageNow = 1;
	$.ajax({
		url: '/users',
		type: 'GET',
		data: {
			'pageNow': pageNow,
			'pageSize': 18,
		},
		dataType: 'json',
		success: function(data) {
			var dataObj = data.pageList.list;
			var html = "<tr class='ss'>"
				+ "<th><input type='checkbox' id='allboxs' onclick='allcheck()'/></th><th>用户名</th>"
				+ "<th>手机号</th>"
				+ "<th>邮箱</th><th class='span2'>创建时间</th>"
				+ "</tr>";
			for (var i = 0; i < dataObj.length; i++) {
				html += "<tr>";
				html += "<td>"
					 + "<input name='boxs' type='checkbox' value=" + dataObj[i].userId + ">"
					 + "</td>";
				html += "<td>" + dataObj[i].username + "</td>";
				html += "<td>" + dataObj[i].userPhone + "</td>";
				html += "<td>" + dataObj[i].userEmail + "</td>";
				html += "<td>" + dataObj[i].createTime + "</td>";
				html += " </tr>";
			}
			$("#usersTable").html(html);
			// 分页
			/*var pageNext = data.pageNow+1;
			var pagePre = data.pageNow-1;
			var htm = "";
			if(data.pageNow != 1){
				htm += "<li><a href='#' onclick='listUsers("+pagePre+")'>&laquo;</a>" + "</li>";
			}
			htm += "<li><a href='#'>"+data.pageNow+"</a></li>";
			if(data.pageNow <= data.totalPage){
				htm += "<li><a href='#' onclick='listUsers("+pageNext+")'>&raquo;</a>" + "</li>";
			}
			$("#page").html(htm);*/
		}
	});
}

/**
 * 添加或更新用户
 */
function addOrUpdateUser() {
	if (checkInput() == false)
		return false;
	var dataSet = $("#addForm").serialize();
//	if ($("#id").val != "") { // update
//		var url = 'users/' + $("#id").val();
//		var type = "PUT";
//	} else { // add
//		var url = 'users/';
//		var type = "POST";
//	}
	
	var url = '/users';
	var type = "POST";
	
	$.ajax({
		url: url,
		type: type,
		data: dataSet,
		dataType: 'json',
		success: function(data) {
			closeModal();
			listUsers();
		}
	});
}

/**
 * 修改数据
 */
function toUpdateUser() {
	var id = getValueChecked();
	if (id.length == 0) {
		$.alert("请选择要修改的数据");
		return ;
	}
	if (id.length > 1) {
		$.alert("请只选择一条数据");
		return ;
	}
	$("#demoModalLabel span").html("修改用户");
	$("#addModal").modal();
	$.ajax({
		url: 'users/' + id,
		type: 'POST',
		dataType: 'json',
		success: function(datas) {
			var data = datas.data;
			$("#id").val(data.id);
			$("#username").val(data.username);
			$("#userEmail").val(data.userEmail);
			$("#userPhone").val(data.userPhone);
			$("#password").val(data.password);
			$("#password1").val(data.password);
		}
	});
}

/**
 * 删除用户
 * @returns {Boolean}
 */
function deleteByIds() {
	var idArray = getValueChecked();
	var idStr = idArray.join(",");
	if (idStr == "") {
		$.alert("请选择要删除的数据");
		return false;
	}
	$.confirm({
		title: null,
		content: '确认删除吗？',
		buttons: {
			'确认': function() {
				$.ajax({
					url: 'users/',
					type: 'POST',
					data: {
						'idStr': idStr,
						_method: 'DELETE'
					},
					dataType: 'json',
					success: function(data) {
						if (data.message == "删除成功") {
							$.alert("删除成功");
							listUsers();
						} else {
							$.alert("删除失败");
							listUsers();
						}
					}
				});
			},
			'取消': function() {
			}
		}
	});
}

/**
 * 根据用户名搜索用户
 * @param pageNow
 */
function searchUsers(pageNow) {
	if (pageNow == null)
		pageNow = 1;
	var search = $("#searchInput").val();
	search = $.trim(search);
	if (search == "") {
		listUsers();
		return ;
	}
	$.ajax({
		url: 'userSearch',
		type: 'POST',
		data: {
			'search': search,
			'pageNow': pageNow,
			'pageSize': 18
		},
		dataType: 'json',
		success: function(data) {
			var dataObj = data.pageList.list;
			var html = "<tr class='ss'>"
				+ "<th><input type='checkbox' id='allboxs' onclick='allcheck()'/></th><th>用户名</th>"
				+ "<th>手机号</th>"
				+ "<th>邮箱</th><th class='span2'>创建时间</th>"
				+ "</tr>";
			for (var i = 0; i < dataObj.length; i++) {
				html += "<tr>";
				html += "<td>"
					 + "<input name='boxs' type='checkbox' value=" + dataObj[i].userId + ">"
					 + "</td>";
				html += "<td>" + dataObj[i].username + "</td>";
				html += "<td>" + dataObj[i].userPhone + "</td>";
				html += "<td>" + dataObj[i].userEmail + "</td>";
				html += "<td>" + dataObj[i].createTime + "</td>";
				html += " </tr>";
			}
			$("#usersTable").html(html);
		}
	});
}

/**
 * 修改密码
 * @returns {Boolean}
 */
function updatePassword() {
	var oldPassword = $("#oldPassword").val();
	if (oldPassword == "") {
		$.alert("原密码不能为空");
		return false;
	}
	var password = $("#newPassword").val();
	if (password == "") {
		$.alert("密码不能为空");
		return false;
	}
	var ret = /^[a-zA-Z0-9]{5,20}$/;
	var rets = /^[0-9]$/;
	if (!ret.test(password) || rets.test(password)) {
		$.alert("密码格式不正确");
		return false;
	}
	var password_verify = $("#password_2").val();
	if (password != password_verify) {
		$.alert("两次输入密码不一致");
		return false;
	}
	var data = $("#updateForm").serialize();
	$.ajax({
		url: 'users/password/' + $("#id_1").val(),
		type: 'PUT',
		data: data,
		dataType: 'json',
		success: function(data) {
			if (data.message == "修改成功") {
				$.alert("修改成功");
				$("#updateModal").modal('hide');
				listUsers();
			} else {
				$.alert("原密码错误");
			}
		}
	});
}

/**
 * 退出登录
 */
function loginOut() {
	$.confirm({
		title: null,
		content: "确认退出吗？",
		buttons: {
			'确认': function() {
				window.open('/','_top');
			},
			'取消': function() {
			}
		}
	});
}

/**
 * 表单校验
 * @returns {Boolean}
 */
function checkInput() {
	//用户名
	var username = $("#username").val()
	if(username == ""){
		 $.alert("用户名不能为空！")
		return false;
	}
	var ret = /^[a-zA-Z0-9]{3,20}$/;
	var rets = /^[0-9]$/;
	if(!ret.test(username) || rets.test(username)){
	     $.alert("用户名格式错误！");
	    return false;
	} 
	//邮箱
	var userEmail = $("#userEmail").val()
	if(userEmail == ""){
		 $.alert("邮箱不能为空！")
		return false;
	}
	var ret =  /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
	if(!ret.test(userEmail)){
	     $.alert("邮箱格式错误！");
	    return false;
	} 
	//手机号
	var userPhone = $("#userPhone").val();
	if(userPhone == ""){
		 $.alert("手机号不能为空！")
		return false;
	}
	var ret = /^((\d2,3)|(\d{3}\-))?13\d{9}$/;
	if(!ret.test(userPhone)){
	     $.alert("手机号码不正确！");
	    return false;
	}
	//密码
	var password = $("#password").val();
	if(password == ""){
		 $.alert("密码不能为空！")
		return false;
	}
	var ret = /^[a-zA-Z0-9]{5,20}$/;
	var rets = /^[0-9]$/;
	if(!ret.test(password) || rets.test(password)){
	     $.alert("密码格式不正确！");
	    return false;
	}
	//确认密码
	var password1 = $("#password1").val();
	if(password != password1){
		  $.alert("两次输入密码不一致！");
		 return false;
	}
}

/**
 * 开启和关闭模态框
 */
function openModal() {
	$('#addForm input').removeAttr("checked");
	$("#demoModalLabel span").html("新增用户");
	$("#addModal").modal();
}

function closeModal() {
	$("#addModal").modal('hide');
	//清空form表中的值
	$('#addForm')[0].reset();
	$('#usersTable input').removeAttr("checked");
	$('#addForm input').removeAttr("checked");
}

/**
 * 选中框验证
 * @returns {Array}
 */
function getValueChecked() {
	var id_array = [];
	$("input[name ^= 'boxs']:checked").each(function() {
		id_array.push($(this).val());
	});
	return id_array;
}

/**
 * 全选全不选
 */
function allcheck() {
	var checkit = $("#allboxs").is(":checked");
	if (checkit == true) {
		var nameBox = $("input[name ^= 'boxs']");
		for (i = 0; i < nameBox.length; i++) {
			nameBox[i].checked = true;
		}
	} else {
		var nameBox = $("input[name ^= 'boxs']");
		for (i = 0; i < nameBox.length; i++) {
			nameBox[i].checked = false;
		}
	}
}

