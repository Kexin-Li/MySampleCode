package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.MaintainService;

/**
 * ����ɾ�����Ʋ�
 * @author Kexin_Li
 *
 */
@SuppressWarnings("serial")
public class DeleteBatchServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ���ñ���
		req.setCharacterEncoding("UTF-8");
		// �õ��������������
		String[] ids = req.getParameterValues("id");
		// ���� service
		MaintainService maintainService = new MaintainService();
		maintainService.deleteBatch(ids);
		// ��ҳ����ת
		req.getRequestDispatcher("/List.action").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
