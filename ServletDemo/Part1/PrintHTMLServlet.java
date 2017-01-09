package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 输出HTML页面
 * @author Kexin_Li
 */
public class PrintHTMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintHTMLServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
//		out.println("<h1>hello servlet</h1>");
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>");
		sb.append("Test Servlet Print...");
		sb.append("</title>");
		sb.append("</head>");

		sb.append("<body>");

		sb.append("<ul>");

		sb.append("<li>");
			sb.append("A");
		sb.append("</li>");

		sb.append("<li>");
			sb.append("B");
		sb.append("</li>");

		sb.append("<li>");
			sb.append("C");
		sb.append("</li>");

		sb.append("</ul>");

		sb.append("</body>");
		sb.append("</html>");

		out.println(sb.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
