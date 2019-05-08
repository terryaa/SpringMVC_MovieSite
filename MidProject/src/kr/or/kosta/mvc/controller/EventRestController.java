package kr.or.kosta.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.or.kosta.dto.EventVO;
import kr.or.kosta.dto.MovieVO;
import kr.or.kosta.dto.Movie_tagVO;
import kr.or.kosta.dto.NoticeVO;
import kr.or.kosta.dto.Tag_CategoryVO;
import kr.or.kosta.mvc.dao.EventDao;
import kr.or.kosta.mvc.service.EventService;

//@RestController("/movie")
@RestController
@RequestMapping
public class EventRestController {
	
	
	@Autowired
	private EventService eventservice;
	
	
	//이벤트 대상을 검색하기 위한 컨트롤러
	@RequestMapping(value="/eventinsert_chk", produces="application/text;charset=utf8")
	public String eventinsert_chk(String event_name, String event_type) {
		return eventservice.eventinsert_chk(event_name, event_type);

	}
	//이벤트와 공지사항을 등록하기 위한 컨트롤러
	@PostMapping("/event_upload")
	public ModelAndView event_upload(EventVO evo , MultipartFile event_fileimg,
			 HttpServletRequest request, NoticeVO nvo, ModelAndView mav){
		eventservice.event_upload(evo, event_fileimg, request, nvo, mav);
		mav.setViewName("index");
		return mav;
	}
	
	}
