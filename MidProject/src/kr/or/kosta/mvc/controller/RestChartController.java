package kr.or.kosta.mvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kosta.dto.userChartViewVO;
import kr.or.kosta.mvc.dao.UserInfoDao;

@RestController
@RequestMapping("/userInfo")
public class RestChartController {
	 
	@Autowired
	private UserInfoDao uiDao;
	 
	//��Ʈ �����͸� �ҷ����� ���� restcontroller 
	@RequestMapping("/chartType/{type}")
	@ResponseBody 
	public List<userChartViewVO> getChartDataJson(@PathVariable String type,String keyword){
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("m_id","oracle6@naver.com");//���� ���ǿ� ���̵� ����Ǹ� �����ؾ� �� �κ�.
		
		if(type.equals("day")) {
			map.put("keyword","day");
		}
		else {
			map.put("keyword","month");
		}
	
		
		
		return uiDao.getAvgSoldList(map);
	}


	
}
