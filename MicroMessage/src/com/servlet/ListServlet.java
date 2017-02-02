package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.QueryService;

/**
 * ҳ���ʼ��������
 * @author Kexin_Li
 *
 */
@SuppressWarnings("serial")
public class ListServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ���ñ���
		req.setCharacterEncoding("UTF-8");
		// �õ�������
		String command = req.getParameter("command");
		String description = req.getParameter("description");
		// ��ҳ�洫�ݲ���
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		// ���� service
		QueryService queryService = new QueryService();
		// �������ǡ���ҳ�洫�ݷ��ؽ��
		req.setAttribute("messageList", queryService.queryMessageList(command, description));
		// ��ҳ����ת
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
