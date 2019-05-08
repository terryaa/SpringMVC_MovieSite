package kr.or.kosta.mvc.aop;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.or.kosta.dto.MovieVO;
import kr.or.kosta.mvc.dao.DynamicExampleDao;

@Component
@Aspect
public class MoviePriceAop {
    @Autowired
    private DynamicExampleDao dao;

    @Before("execution(* kr.or.kosta.mvc.controller.*.updatemovieprice(..))")
    public void searchLog(JoinPoint jp) {
    

        
        
        MovieVO vo = new MovieVO();
        
        
        
        
        
        
        
        
        Object[] o=jp.getArgs();//��� �Ű������� ������ object �� �޾ƿ´�.
        
        if(o[0] instanceof MovieVO && o[0] != null) { //����ε� ������ �˻�
            vo = (MovieVO) o[0];
        }
        System.out.println(vo.getMovie_number());
        System.out.println(vo.getMovie_price());
        
        vo.getMovie_number();
        vo.getMovie_price();
        //System.out.println("keyword  ==  : "+keyword);
        //String uid = (String) hs.getAttribute("uid");//���ǿ� �ö��ִ� uid�� ��Ʈ�� Ÿ������ ��ȯ���ش�
        //�׽�Ʈ�� ���� �ӽ÷� uid �� �׽�Ʈ������ ����
        
//        Map<String,String> map = new HashMap<>();
//        
//        map.put("movie_number",vo.getMovie_number());
//        map.put("movie_price",String.valueOf(vo.getMovie_price()));
        System.out.println("-------------");
        dao.movie_advice(vo);
    }

}
//    @Before("execution(* kr.or.kosta.mvc.controller.*.updatemovieprice(..))")
//    public void searchLog(JoinPoint jp) {//�˻��ϸ� ���� ��µǱ� ���� �α׺��� ���δ�.
//    	
        
        
        