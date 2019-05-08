package kr.or.kosta.mvc.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.kosta.mvc.dao.JoinLogDao;
import kr.or.kosta.mvc.dao.LoginLogDao;
import kr.or.kosta.mvc.dao.MovieSoldLogDao;
import kr.or.kosta.mvc.dao.ViewLogDao;
import kr.or.kosta.mvc.dao.WatchLogDao;
import kr.or.kosta.mvc.inter.LogDao;



@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class,readOnly=true)
@Service
//로그 조회 관련 정보들에대한 요청에 로직을 생성하고 조회된 로그데이터를 전송하는 서비스 클래스
public class LogService {
	
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
	private LogDao logDao;
	
	int month=Calendar.getInstance().get(Calendar.MONTH)+1;
	int date=Calendar.getInstance().get(Calendar.DATE);
	int max_date_ofmonth=0;
	int year=Calendar.getInstance().get(Calendar.YEAR)-2000;

	public LogService() {
		
		if(month-1 ==2) {
			max_date_ofmonth=28;
		}else if(month-1 ==3||month-1 ==5||month-1 ==7||month-1 ==8||month-1 ==10||month-1 ==12) {
			max_date_ofmonth=31;
		}else
			max_date_ofmonth=30;
	}

	//방문자수 로그
	public List<Integer> getLoginLogData(String cmd) {
		logDao=loginLogDao;
		return getLogData(cmd);
		
	}

	//총판매액 로그
	public List<Integer> getMovieSoldLogData(String cmd) {
		logDao=movieSoldLogDao;
		return getLogData(cmd);
	}
	//총조회수 로그
	public List<Integer> getViewLogData(String cmd) {
		logDao=viewLogDao;
		return getLogData(cmd);
	}
	//총시청수 로그
	public List<Integer> getWatchLogData(String cmd) {
		logDao=watchLogDao;
		return getLogData(cmd);
	}
	//총 가입자수 로그
	public List<Integer> getJoinLogData(String cmd) {
		logDao=joinLogDao;
		return getLogData(cmd);
	}
	
	private List<Integer> alignMonths (List<Integer> result) {
		int period=result.size();
		int[] temp=new int[period];
		for(int i=0; i<period ; i++) {
			if(month+i>period-1) {
				temp[month+i-(period)]= result.get(i);
			}
			else
				temp[month+i]=result.get(i);
		}
		result.clear();
		for(int i=0; i<period;i++) {
			result.add(temp[i]);
		}
		return result;
	}
	
	private List<Integer> alignCalendar(List<HashMap<String,BigDecimal>> list,int period,int max_number,int daymonthyear){
		List<Integer> result=new ArrayList<>();
		int month_desc=daymonthyear;
		int temp[]=new int[period];
		for(HashMap<String,BigDecimal> item : list) {
			int month_from_map=item.get("month").intValue();
			for(int i=period-1;i>-1;i--) {
				if(month_from_map==month_desc) {
					temp[i]=item.get("count").intValue();
					month_desc=daymonthyear;
					break;
				}else {
					if(month_desc==1)
						month_desc=max_number;
					else
						month_desc--;
				}
			}
		}
		for(int i=0;i<temp.length;i++) {
			result.add(temp[i]);
		}
		return result;
		
	}
	
	
	//dao를 이용하여 데이터불러오기
	private List<Integer> getLogData(String cmd){
		List<Integer> result = null;
		List<HashMap<String,BigDecimal>> list=null;
		if(cmd.equals("0")) {
			list=logDao.getLogCount_Days();
			result=alignCalendar(list, 5, max_date_ofmonth,date);
		}
		else if(cmd.equals("1")) {
			list=logDao.getLogCount_Months();
			result=alignCalendar(list, 5, 12,month);
		}
		else if(cmd.equals("2")) {
			list=logDao.getLogCount_Years();
			result=alignCalendar(list, 5, 99,year);
		}
		return result;
	}
//	public List<Integer> getMovieSoldLogCountDate(String cmd){
//		List<Integer> result = null;
//		List<HashMap<String,BigDecimal>> list=null;
//		if(cmd.equals("0")) {
//			list=movieSoldLogDao.getLogPrice_Days();
//			result=alignCalendar(list, 5, max_date_ofmonth,date);
//		}
//		else if(cmd.equals("1")) {
//			list=movieSoldLogDao.getLogPrice_Days();
//			result=alignCalendar(list, 5, 12,month);
//		}
//		else if(cmd.equals("2")) {
//			result=alignCalendar(list, 5, 99,year);
//		}
//		return result;
//	}
}
