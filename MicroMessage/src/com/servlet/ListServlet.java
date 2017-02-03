package com.servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Page;
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
		String currentPage = req.getParameter("currentPage");
		// ������ҳ����
		Page page = new Page();
		// ʹ��������ʽ��У��
		Pattern pattern = Pattern.compile("[0-9]{1,9}");
		if (currentPage == null || !pattern.matcher(currentPage).matches()) {
			page.setCurrentPage(1);
		} else {
			page.setCurrentPage(Integer.valueOf(currentPage));
		}
		// ���� service
		QueryService queryService = new QueryService();
		// ��ѯ��Ϣ�б�����ҳ��
//		req.setAttribute("messageList", queryService.queryMessageList(command, description, page));
		req.setAttribute("messageList", queryService.queryMessageListByPage(command, description, page));
		// ��ҳ�洫�ݲ���
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		req.setAttribute("page", page);
		// ��ҳ����ת
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
