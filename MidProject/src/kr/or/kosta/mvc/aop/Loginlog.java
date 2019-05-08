package kr.or.kosta.mvc.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import kr.or.kosta.dto.Login_LogVO;
import kr.or.kosta.dto.MyMemberVO;
import kr.or.kosta.mvc.controller.LoginController;
import kr.or.kosta.mvc.dao.MemberDao;

@Component
@Aspect
public class Loginlog {
	@Autowired
	private MemberDao mdao;
	
	@AfterReturning(pointcut="execution(* kr.or.kosta.mvc.controller.*.login*(..))",returning="ret")
	public void loginlog(JoinPoint jp,Object ret) {//������Ʈ�� �޾ƿͼ� �Ķ���Ͱ� �����
		System.out.println(ret.getClass().getMethods().toString());
		System.out.println(ret instanceof ModelAndView);
		Object[] o=jp.getArgs();//�޼ҵ��� �Ķ���Ͱ��� ������� �޾ƿ�
		MyMemberVO mvo= null;
		for(Object temp: o) {
			if(temp instanceof MyMemberVO) {
				mvo = (MyMemberVO) temp;
			}
		}//�Ķ���Ͱ��� ������� �����鼭 ���ϴ� Ÿ���� ���� �޾ƿ´�.
		mdao.llogging(mvo);//�α��ν� �α��� �α׿� �α׸� ����� aop
	}
	
	@Before("execution(* kr.or.kosta.mvc.controller.*.logout*(..))")
	public void logoutlog(JoinPoint jp) {//�α׾ƿ��� �α׿� ������Ʈ�� ���ֱ� ���� �����̽�
		Object[] o=jp.getArgs();//�α׾ƿ� �޼ҵ��� �Ķ���Ͱ��� �����´�.
		HttpSession hs =null;
		for(Object temp: o) {
			if(temp instanceof HttpSession) {
				hs = (HttpSession) temp;
				System.out.println("hstest");
			}//������ �Ķ������ ���ǿ� ���õ� ���� hs�� ����
		}
		System.out.println(hs);
		String uid = String.valueOf(hs.getAttribute("uid"));//���ǿ� �ö��ִ� uid�� ��Ʈ�� Ÿ������ ��ȯ���ش�
		mdao.logoutlogging(uid);//�α׾ƿ� �α׸� ����� ���� �޼ҵ� ȣ��
	}//�α׾ƿ��ÿ��� �α��� ���� ���� �ο츦 ������Ʈ �ϴ� ����!

}
