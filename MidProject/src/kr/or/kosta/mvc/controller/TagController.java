package kr.or.kosta.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.or.kosta.dto.Tag_ProfitVO;
import kr.or.kosta.dto.YS_MovieVO;
import kr.or.kosta.dto.YS_Movie_tagVO;
import kr.or.kosta.dto.YS_Tag_CategoryVO;
import kr.or.kosta.dto.YS_Tag_TableVO;
import kr.or.kosta.mvc.service.EventService;
import kr.or.kosta.mvc.service.TagService;

//@RestController("/movie")
@RestController
@RequestMapping
public class TagController {

	@Autowired
	private TagService tagservice;
	
	@RequestMapping(value="/tag_chk", produces="application/text;charset=utf8")
	public String tag_chk(String tag_name, String tag_type) {
		return tagservice.tag_chk(tag_name, tag_type);
	}
	
	@RequestMapping("/tag_profit_se")
	public List<Tag_ProfitVO> tag_profit() {
		return tagservice.tag_profit();
	}
	
	@RequestMapping("/tag_ex")
	public ModelAndView tag_ex(String tag_Table_Num, ModelAndView mav) {
		 tagservice.tag_ex(tag_Table_Num, mav);
		 mav.setViewName("index");
		return mav;
		
	}
}
