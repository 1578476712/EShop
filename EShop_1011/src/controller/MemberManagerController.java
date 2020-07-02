package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import po.MemberPO;

import service.MemberManagerService;

@WebServlet("/member")
public class MemberManagerController extends HttpServlet{
	String method;
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		method = request.getParameter("m");
		
		if("login".equals(method)){
			login(request,response);
		}else if("exit".equals(method)){
			exit(request,response);
		}
	}
	private void exit(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		// session��װ����������
		request.getSession().setAttribute("memberPO", null);
		// ת��
		request.getRequestDispatcher("/browseIndex").forward(request,
				response);
	}
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginName = request.getParameter("loginName");
		String loginPwd = request.getParameter("loginPwd");
		System.out.println("�û����ǣ�" + loginName);
		System.out.println("�����ǣ�" + loginPwd);
		MemberManagerService memberManagerService = new MemberManagerService();
		MemberPO memberPO = memberManagerService.login(loginName, loginPwd);
		if (memberPO != null) {
			//��װ����
			request.setAttribute("loginStatus", "1");
			// session��װ����������
			request.getSession().setAttribute("memberPO", memberPO);
			// ת��
			request.getRequestDispatcher("/browseIndex").forward(request,
					response);
		} else {
			//��װ����
			request.setAttribute("loginStatus", "0");
			// session��װ����������
			request.getSession().setAttribute("memberPO", memberPO);
			// ת��
			request.getRequestDispatcher("/browseIndex").forward(request,
					response);
		}

	}
}
