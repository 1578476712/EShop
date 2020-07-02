package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import po.CategoryPO;
import po.MerchandisePO;

import dao.MerchandiseDAO;

import service.MemberManagerService;
import service.MerchandiseManagerService;

@WebServlet("/browseIndex")
public class BrowseIndexController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//ʵ������Ʒ�������
		MerchandiseManagerService merchandiseManagerService = new MerchandiseManagerService();
		//��װ����
		//��Ʒ����б��ϣ����Է�װ���
		List<CategoryPO> categoryList = merchandiseManagerService.getCategoryList();
		//������
		req.setAttribute("cateList", categoryList);
		System.out.println("controller����Ʒ���ĸ�����"+categoryList.size());
		//�ؼۼ���1
		List<MerchandisePO> specialMerList = merchandiseManagerService.getMerchandiseList(1);
		//�ؼۼ���0
		List<MerchandisePO> newMerList = merchandiseManagerService.getMerchandiseList(0);
		//��װ����������
		req.setAttribute("specialMerList",specialMerList);
		req.setAttribute("newMerList",newMerList);
		System.out.println("�ؼ���Ʒ�еĸ�����"+specialMerList.size());
		System.out.println("���ϼ���Ʒ�еĸ�����"+newMerList.size());
		
		System.out.println("��ӭ��½��վ��ҳ");
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}


















