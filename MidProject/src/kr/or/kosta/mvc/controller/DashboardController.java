package kr.or.kosta.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.or.kosta.mvc.dao.JoinLogDao;
import kr.or.kosta.mvc.dao.LoginLogDao;
import kr.or.kosta.mvc.dao.MovieDao;
import kr.or.kosta.mvc.dao.MovieSoldLogDao;
import kr.or.kosta.mvc.dao.ViewLogDao;
import kr.or.kosta.mvc.dao.WatchLogDao;
import kr.or.kosta.mvc.inter.LogDao;
import kr.or.kosta.mvc.service.RecommendLogicService;

@Controller
public class DashboardController {
	
	@Autowired
	private WatchLogDao watchLogDao;
	@Autowired
	private ViewLogDao viewLogDao;
	@Autowired
	private MovieSoldLogDao movieSoldLogDao;
	@Autowired
	private LoginLogDao loginLogDao;
	@Autowired
	private JoinLogDao joinLogDao;
	@Autowired
	private MovieDao movieDao;
	private LogDao logDao;
	
	
	private double riskPie=0.0;
	@Autowired
	private RecommendLogicService rls;
	
	String path="C:\\Users\\KOSTA\\Desktop\\";
	
	@RequestMapping(value="/upload", method=RequestMethod.POST )
	public String ex2(MultipartHttpServletRequest request) throws IllegalStateException, IOException {
		Iterator itr = request.getFileNames();
		if(itr.hasNext()) {
			List mpf=request.getFiles((String) itr.next());
			
			for(int i=0;i<mpf.size();i++) {
				File file=new File(path+((MultipartFile) mpf.get(i)).getOriginalFilename());
				((MultipartFile) mpf.get(i)).transferTo(file);
			}
		}
		return "success";
	}
	@RequestMapping("/test11")
	public String testRecommend(Model m) {
		rls.getUserTagWeightedList();
		return "test11";
	}
	
	//Dashboard 페이지에 기본적인 정보를 불러오기위한 작업을 처리하는 컨트롤러. 
	@RequestMapping("/index")
	public String index(Model m,HttpSession session) {
		DecimalFormat df=new DecimalFormat("#,###");
		int watchlogcount_all=watchLogDao.getLogCount_All();
		int watchlogcount_currentdate=watchLogDao.getLogCount_CurrentDate();
		int watchlogcount_yesterday=watchLogDao.getLogCount_Yesterday();
		calCulateRisk( watchlogcount_currentdate, watchlogcount_yesterday);
		
		int viewlogcount_all=viewLogDao.getLogCount_All();
		int viewlogcount_currentdate=viewLogDao.getLogCount_CurrentDate();
		int viewlogcount_yesterday=viewLogDao.getLogCount_Yesterday();
		calCulateRisk( viewlogcount_currentdate, viewlogcount_yesterday);
		
		int moviesoldLogcount_all=movieSoldLogDao.getLogPrice_All();
		int moviesoldLogcount_currentdate=movieSoldLogDao.getLogPrice_CurrentDate();
		int moviesoldLogcount_yesterday=movieSoldLogDao.getLogCount_Yesterday();
		calCulateRisk(moviesoldLogcount_currentdate, moviesoldLogcount_yesterday);
		System.out.println("moviesoldLogcount_all");
		int loginLogcount_all=loginLogDao.getLogCount_All();
		int loginLogcount_currentdate=loginLogDao.getLogCount_CurrentDate();
		int loginLogcount_yesterday=loginLogDao.getLogCount_Yesterday();
		calCulateRisk(  loginLogcount_currentdate, loginLogcount_yesterday);
		
		int joinLogcount_all=joinLogDao.getLogCount_All();
		int joinLogcount_currentdate=joinLogDao.getLogCount_CurrentDate();
		int joinLogcount_yesterday=joinLogDao.getLogCount_Yesterday();
		calCulateRisk( joinLogcount_currentdate, joinLogcount_yesterday);
		
		int purchase_pastmonth=movieDao.getMoviePurchaseCount_pastMonth();
		int purchase_thismonth=movieDao.getMoviePurchaseCount_thisMonth();
		
		int copyrightcost_pastmonth=movieDao.getMovieCopyRightCost_pastMonth();
		int copyrightcost_thismonth=movieDao.getMovieCopyRightCost_thisMonth();
		
		int investment_pastmonth=movieDao.getMovieInvestment_pastMonth();
		int investment_thismonth=movieDao.getMovieInvestment_thisMonth();
		
		int networth_pastmonth;
		int networth_thismonth;
		if(investment_pastmonth==0)
			networth_pastmonth=0;
		else
			networth_pastmonth=copyrightcost_pastmonth/investment_pastmonth*100;
		
		if(investment_thismonth==0) 
			networth_thismonth=0;
		else
			networth_thismonth=copyrightcost_thismonth/investment_thismonth*100;
		
		
		m.addAttribute("watchlogcount_all",df.format(watchlogcount_all));
		m.addAttribute("watchlogcount_currentdate",df.format(watchlogcount_currentdate));
		
		m.addAttribute("viewlogcount_all",df.format(viewlogcount_all));
		m.addAttribute("viewlogcount_currentdate",df.format(viewlogcount_currentdate));
		
		m.addAttribute("moviesoldlogcount_all",df.format(moviesoldLogcount_all));
		m.addAttribute("moviesoldlogcount_currentdate",df.format(moviesoldLogcount_currentdate));
		m.addAttribute("loginlogcount_all",df.format(loginLogcount_all));
		m.addAttribute("loginlogcount_currentdate",df.format(loginLogcount_currentdate));
		
		m.addAttribute("joinlogcount_all",df.format(joinLogcount_all));
		m.addAttribute("joinlogcount_currentdate",df.format(joinLogcount_currentdate));
		
		m.addAttribute("purchase_pastmonth",df.format(purchase_pastmonth));
		m.addAttribute("purchase_thismonth",df.format(purchase_thismonth));
		
		m.addAttribute("copyrightcost_pastmonth",df.format(copyrightcost_pastmonth));
		m.addAttribute("copyrightcost_thismonth",df.format(copyrightcost_thismonth));
		
		m.addAttribute("investment_pastmonth",df.format(investment_pastmonth));
		m.addAttribute("investment_thismonth",df.format(investment_thismonth));
		
		m.addAttribute("networth_pastmonth",networth_pastmonth);
		m.addAttribute("networth_thismonth",networth_thismonth);
		
		m.addAttribute("riskPieRed",(int)riskPie);
		m.addAttribute("riskPieWhite",100-(int)riskPie);
		
		return "index";
	}

	private void calCulateRisk(int top, int bottom) {
		double risk;
		if(bottom==0)
			risk=0.0;
		else
			risk=top/bottom;
		if(risk>=1) {
			risk=0.0;
		}
		riskPie+=20*risk;
	}


}
