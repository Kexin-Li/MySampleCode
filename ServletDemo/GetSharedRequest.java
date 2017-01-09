package com.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * GetSharedRequest，配合SetSharedVar.java使用
 * @author Kexin_Li
 */
public class GetSharedRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSharedRequest() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get ServletContext
		ServletContext ctx = this.getServletContext();
		// get HttpSession
		HttpSession session = request.getSession();
		// get ctx_value
		String ctx_value = (String) ctx.getAttribute("ctx_name");
		// get session_value
		String session_value = (String) session.getAttribute("session_name");
		// get request_value
		String request_value = (String) request.getAttribute("request_name");
		// print them
		PrintWriter out = response.getWriter();
		out.println("ctx: "+ctx_value);
		out.println("session: "+session_value);
		out.println("request: "+request_value);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
