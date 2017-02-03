package com.servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Page;
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
		String currentPage = req.getParameter("currentPage");
		// 创建分页对象
		Page page = new Page();
		// 使用正则表达式做校验
		Pattern pattern = Pattern.compile("[0-9]{1,9}");
		if (currentPage == null || !pattern.matcher(currentPage).matches()) {
			page.setCurrentPage(1);
		} else {
			page.setCurrentPage(Integer.valueOf(currentPage));
		}
		// 调用 service
		QueryService queryService = new QueryService();
		// 查询消息列表并传给页面
//		req.setAttribute("messageList", queryService.queryMessageList(command, description, page));
		req.setAttribute("messageList", queryService.queryMessageListByPage(command, description, page));
		// 向页面传递参数
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		req.setAttribute("page", page);
		// 向页面跳转
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
