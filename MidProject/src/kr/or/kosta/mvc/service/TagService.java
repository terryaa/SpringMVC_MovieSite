package kr.or.kosta.mvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.kosta.dto.Tag_ProfitVO;
import kr.or.kosta.dto.YS_MovieVO;
import kr.or.kosta.dto.YS_Movie_tagVO;
import kr.or.kosta.dto.YS_Tag_CategoryVO;
import kr.or.kosta.dto.YS_Tag_TableVO;
import kr.or.kosta.mvc.dao.EventDao;
import kr.or.kosta.mvc.dao.TagDao;

@Service
public class TagService {

	@Autowired
	private TagDao tagdao;
	
	@RequestMapping(value="/tag_chk", produces="application/text;charset=utf8")
	public String tag_chk(String tag_name, String tag_type) {
	
		System.out.println("tag_type"+tag_type);
		System.out.println("tag_name"+tag_name);
		Map<String, String> map = new HashMap<>();
		map.put("tag_type", tag_type);
		map.put("tag_name", tag_name);
		
		List<YS_MovieVO> list = tagdao.tag_in_target(map);
		
		StringBuffer sb = new StringBuffer();
		
		
		for(YS_MovieVO i: list) {
			sb.append("<option value='");
			List<YS_Movie_tagVO> tvo=i.getMovie_tag();
			for(YS_Movie_tagVO m : tvo) {
				List<YS_Tag_TableVO> ttvo=m.getTag_table();
				for(YS_Tag_TableVO t : ttvo) {
					List<YS_Tag_CategoryVO> tcvo=t.getTag_category();
					for(YS_Tag_CategoryVO c : tcvo) {
						sb.append(t.getTag_Table_Num()+"'>");
						sb.append("영화 제목 : "+i.getMovie_Title());
						sb.append("/종류 : "+c.getTag_Category_Name());
						sb.append("/태그이름 : "+t.getTag_Name()).append("</option>");
						
					}
					
				}
			}
		}
		
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public List<Tag_ProfitVO> tag_profit() {
		List<Tag_ProfitVO> list = tagdao.tag_profit();
		return list;
	}
	
	public void tag_ex(String tag_Table_Num, ModelAndView mav) {
		tagdao.tag_ex(tag_Table_Num);
		
	}
}
