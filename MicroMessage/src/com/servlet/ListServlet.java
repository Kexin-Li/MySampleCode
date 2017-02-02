package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.QueryService;

/**
 * 页面初始化控制器
 * @author Kexin_Li
 *
 */
@SuppressWarnings("serial")
public class ListServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置编码
		req.setCharacterEncoding("UTF-8");
		// 拿到表单参数
		String command = req.getParameter("command");
		String description = req.getParameter("description");
		// 向页面传递参数
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		// 调用 service
		QueryService queryService = new QueryService();
		// 【已忘记】向页面传递返回结果
		req.setAttribute("messageList", queryService.queryMessageList(command, description));
		// 向页面跳转
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
