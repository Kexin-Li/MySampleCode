package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Test Cookie，配合CookieServlet使用
 * @author Kexin_Li
 */
public class TestCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestCookie() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// 简单读取Cookie

//		Cookie[] cs = request.getCookies();
//		for (Cookie c : cs) {
//			out.println(c.getName() + " : " + c.getValue());
//			System.out.println(c.getName() + " : " + c.getValue());
//		}

		// 验证是否是初访者
		String result = null;
		boolean newUser = true;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i=0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equals("repeatVisitor") && c.getValue().equals("yes")) {
					newUser = false;
					break;
				}
			}
		}
		if (newUser) {
			Cookie returnVisitorCookie = new Cookie("repeatVisitor", "yes");
			returnVisitorCookie.setMaxAge(60*60*24*365);
			response.addCookie(returnVisitorCookie);
			result = "Welcome Abroad";
		} else {
			result = "Welcome back";
		}
		out.println(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
