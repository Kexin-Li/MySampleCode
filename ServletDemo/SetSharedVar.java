package com.demo;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SetSharedVar
 * 共享变量的获取，配合GetSharedResponse.java使用
 */
public class SetSharedVar extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetSharedVar() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ServletContext
		ServletContext ctx = this.getServletContext();
		ctx.setAttribute("ctx_name", "ctx_value");
		// HttpSession
		HttpSession session = request.getSession();
		session.setAttribute("session_name", "session_value");
		// HttpServletRequest
		request.setAttribute("request_name", "request_value");

		request.getRequestDispatcher("GetSharedRequest").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
