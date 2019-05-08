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
import kr.or.kosta.dto.GM_MovieVO;
import kr.or.kosta.dto.GM_Movie_Sold_LogVO;
import kr.or.kosta.dto.GM_View_LogVO;
import kr.or.kosta.dto.GM_Watch_LogVO;
import kr.or.kosta.mvc.dao.GM_MovieDao;

@Component
@Aspect
public class MovieLog {
	@Autowired
	private GM_MovieDao mdao;

	
	@Before("execution(* kr.or.kosta.mvc.controller.*.viewMovieDetail(..))")
	public void viewMovielog(JoinPoint jp) {//오브젝트로 받아와서 파라미터값 열어보기
		Object[] params=jp.getArgs();//메소드의 파라미터값을 순서대로 받아옴
		String title = params[0].toString();
		HttpSession session = (HttpSession) params[1];
		String uid = session.getAttribute("uid").toString();
		System.out.println("uid : " + uid);
		GM_View_LogVO vlvo = new GM_View_LogVO();
		int member_number = mdao.getMember_number(uid);
		String movie_number = mdao.getMovie_number(title);
		
		GM_MovieVO mov = mdao.getMovieVO(title);
		
		int count = mov.getMovie_view_count();
		
		mov.setMovie_view_count(count+1);
		
		vlvo.setMember_number(member_number);
		vlvo.setMovie_number(movie_number);
		
		mdao.add_view_log(vlvo);
		mdao.update_movie_view(mov);
		
		System.out.println(uid + " | " + title + " | 조회");
		
	}
	
	@Before("execution(* kr.or.kosta.mvc.controller.*.movie_watch(..))")
	public void watchMovielog(JoinPoint jp) {
		Object[] params=jp.getArgs();
		String movie_number = params[0].toString();
		String title = mdao.getMovie_title(movie_number);
		HttpSession session = (HttpSession) params[1];
		String uid = session.getAttribute("uid").toString();
		int member_number = mdao.getMember_number(uid);
		GM_MovieVO mov = mdao.getMovieVO(title);
		GM_Watch_LogVO wlvo = new GM_Watch_LogVO();
		
		int movie_price = mov.getMovie_price();
		
		int chk_event = mdao.chk_event(movie_number);
		if(chk_event != 0) {
			movie_price--;
		}
		
		
		wlvo.setMember_number(member_number);
		wlvo.setMovie_number(movie_number);
		
		GM_Movie_Sold_LogVO mslvo = new GM_Movie_Sold_LogVO();
		mslvo.setMember_number(member_number);
		mslvo.setMovie_number(movie_number);
		mslvo.setMovie_price(movie_price);
		int watch_count = mov.getMovie_watch_count();
		mov.setMovie_watch_count(watch_count+1);
		mdao.add_watch_log(wlvo);
		mdao.update_movie_watch(mov);
		
		mdao.add_sold_log(mslvo);
		
		System.out.println(uid + " | " + title + " | 시청");
	}

}
