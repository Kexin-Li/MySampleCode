package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.MaintainService;

/**
 * 批量删除控制层
 * @author Kexin_Li
 *
 */
@SuppressWarnings("serial")
public class DeleteBatchServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置编码
		req.setCharacterEncoding("UTF-8");
		// 拿到表单参数（多个）
		String[] ids = req.getParameterValues("id");
		// 调用 service
		MaintainService maintainService = new MaintainService();
		maintainService.deleteBatch(ids);
		// 向页面跳转
		req.getRequestDispatcher("/List.action").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
