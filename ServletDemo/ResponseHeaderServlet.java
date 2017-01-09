package com.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 响应头信息，请求头信息在HeaderServlet中
 * @author Kexin_Li
 */
public class ResponseHeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResponseHeaderServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Excel表格
//		response.setContentType("Application/vnd.ms-excel");
//		PrintWriter out = response.getWriter();
//		out.println("\tQ1\tQ2\Q3\tQ4\tTotal");
//		out.println("\tQ1\tQ2\Q3\tQ4\tTotal");

//		response.setContentType("image/jpeg");
//		InputStream in = (InputStream) this.getClass().getClassLoader().getResourceAsStream("setHeader1.jpg");
//		int len = in.available();
//		byte[] buffer = new byte[len];
//		in.read(buffer);
//		// output
//		OutputStream out = response.getOutputStream();
//		out.write(buffer);
//		out.flush();
//		out.close();

		// 定时刷新页面
		response.setHeader("refresh", "1");
		response.setContentType("text/html;charset=gbk");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML>");
		out.println("<html");
		out.println("<body>");

		out.println("当前页面刷新时间为：");
		out.println(new Date().toString());

		out.println("</body>");
		out.println("</html>");

		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
