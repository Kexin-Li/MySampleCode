package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.QueryService;

/**
 * 自动回复控制器
 * 这个Servlet是针对ajax请求的，所以不是以跳转页面为目的，而是通过PrintWriter把需要输出的内容输出出去为目的。
 * @author Kexin_Li
 *
 */
@SuppressWarnings("serial")
public class AutoReplyServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		QueryService queryService = new QueryService();
		// 将service里读取到的数据库里的内容传到页面
		out.write(queryService.queryCommandById(req.getParameter("content")));
		out.flush();
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
