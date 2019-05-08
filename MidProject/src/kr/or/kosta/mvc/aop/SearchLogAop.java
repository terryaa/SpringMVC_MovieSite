package kr.or.kosta.mvc.aop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;


import kr.or.kosta.mvc.dao.MemberDao;
import kr.or.kosta.mvc.dao.UserInfoDao;

@Component
@Aspect
public class SearchLogAop {
	@Autowired
	private UserInfoDao uiDao;

	
	@Before("execution(* kr.or.kosta.mvc.controller.*.searchMovie*(..))")
	public void searchLog(JoinPoint jp) {//�˻��ϸ� ���� ��µǱ� ���� �α׺��� ���δ�.
		Object[] o=jp.getArgs();//��� �Ű������� ������ object �� �޾ƿ´�.
		HttpSession hs =null; //������ ���� ��
		String keyword=""; //�˻�� �ޱ����� ����� ����
		 
		for(Object temp: o) {
			if(temp instanceof HttpSession) {
				hs = (HttpSession) temp;
				
			}//������ �Ķ������ ���ǿ� ���õ� ���� hs�� ����
		}
		if(o[1] instanceof String && o[1] != null) { //����ε� ������ �˻�
			keyword = String.valueOf(o[1]);
		}
		//System.out.println("keyword  ==  : "+keyword);
		Map<String,String> map = new HashMap<>();
		String uid = "oracle6@naver.com"; 
		//String uid = (String) hs.getAttribute("uid");//���ǿ� �ö��ִ� uid�� ��Ʈ�� Ÿ������ ��ȯ���ش�
		//�׽�Ʈ�� ���� �ӽ÷� uid �� �׽�Ʈ������ ����
		
		map.put("userId",uid);
		map.put("keyword",keyword);
		uiDao.instUserSearchLog(map);
	}

}
