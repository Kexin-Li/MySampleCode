package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.QueryService;

/**
 * �Զ��ظ�������
 * ���Servlet�����ajax����ģ����Բ�������תҳ��ΪĿ�ģ�����ͨ��PrintWriter����Ҫ��������������ȥΪĿ�ġ�
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
		// ��service���ȡ�������ݿ�������ݴ���ҳ��
		out.write(queryService.queryCommandById(req.getParameter("content")));
		out.flush();
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}