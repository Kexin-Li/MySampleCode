package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 一个简单的Servlet实例
 * @author Kexin_Li
 */
public class MyServlet extends HttpServlet{

	/**
	 * Servlet implementation class RigisterServlet
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Logic Code
    	PrintWriter out = resp.getWriter();
		String value = this.getServletContext().getInitParameter("likexin's blog");
	    out.println(value);
		// 重定向
		resp.sendRedirect("http://www.likexin.org");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
