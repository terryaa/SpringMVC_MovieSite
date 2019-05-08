package kr.or.kosta.mvc.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MyHandlerInterceptor extends HandlerInterceptorAdapter {

	// Controller�� ������ ����
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("preHandle ����!");

		boolean isAuth = false;	// ���� �Ǵ� ����

		HttpSession session = request.getSession();
		int grade = (int) session.getAttribute("grade");	// ������ ȸ����� ������ �޾ƿ�
		
		System.out.println("ȸ�� ��� : "+grade);

		String reqUri = request.getRequestURI();
		String[] reqUris = reqUri.split("/");
		String firstPath = reqUris[2];	// �ش� ��Ʈ�ѷ��� URI �κ��� ����
		
		System.out.println("���� ��� : "+firstPath);

		if (firstPath.equals("member") && (grade == 5 || grade == 9)) {	// ȸ�������� or �ְ������
			isAuth = true;

		} else if (firstPath.equals("movie") && (grade == 6 || grade == 9)) {	// ��ȭ������ or �ְ������
			isAuth = true;

		} else if (firstPath.equals("complaint") && (grade == 7 || grade == 9)) {	// �ο������� or �ְ������
			isAuth = true;

		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������ �����ϴ�.'); history.go(-1);</script>");	// ������ ���� �� �˸�â ���
			out.flush();
		}

		return isAuth;
	}
}
