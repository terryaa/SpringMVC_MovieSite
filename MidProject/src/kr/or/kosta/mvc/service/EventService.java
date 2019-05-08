package kr.or.kosta.mvc.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.or.kosta.dto.EventVO;
import kr.or.kosta.dto.MovieVO;
import kr.or.kosta.dto.NoticeVO;
import kr.or.kosta.dto.Tag_CategoryVO;

import kr.or.kosta.dto.YS_MovieVO;
import kr.or.kosta.dto.YS_Movie_tagVO;
import kr.or.kosta.dto.YS_Tag_CategoryVO;
import kr.or.kosta.dto.YS_Tag_TableVO;
import kr.or.kosta.mvc.dao.EventDao;
import kr.or.kosta.mvc.dao.LoadPoiDao;
import kr.or.kosta.mvc.dao.NoticeDao;

@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class,readOnly=true)
@Service
public class EventService {
	
	
	@Autowired
	private EventDao eventdao;
	
	@Autowired
	private NoticeDao noticeDao;
	
public String eventinsert_chk(String event_name, String event_type) {

		Map<String, String> map = new HashMap<>();
		map.put("event_type", event_type);
		map.put("event_name", event_name);
		
		List<YS_MovieVO> list1 = eventdao.event_in_target(map);
		StringBuffer sb = new StringBuffer();
		
		for(YS_MovieVO i: list1) {
			sb.append("<option value='");
			List<YS_Movie_tagVO> tvo=i.getMovie_tag();
			for(YS_Movie_tagVO m : tvo) {
				List<YS_Tag_TableVO> ttvo=m.getTag_table();
				for(YS_Tag_TableVO t : ttvo) {
					List<YS_Tag_CategoryVO> tcvo=t.getTag_category();
					for(YS_Tag_CategoryVO c : tcvo) {
						sb.append(t.getTag_Table_Num()+"'>");
						sb.append("태그이름 : "+t.getTag_Name());
						sb.append("/영화 제목 : "+i.getMovie_Title());
						sb.append("/ 종류 : "+c.getTag_Category_Name()).append("</option>");
						
					}
					
				}
			}
		}
		return sb.toString();

}

	public void event_upload(EventVO evo , MultipartFile event_fileimg,
				 HttpServletRequest request, NoticeVO nvo, ModelAndView mav){
	System.out.println(nvo.getNotice_content());
	System.out.println(nvo.getNotice_title());

	String img_path = "img\\event";
	String r_path = request.getRealPath("/");
	String oriFn = event_fileimg.getOriginalFilename();
	StringBuffer path = new StringBuffer();
	path.append(r_path).append(img_path).append("\\");
	path.append(oriFn);
	System.out.println(path);
	File f = new File(path.toString());
	evo.setEvent_Image(oriFn);
	
	try {
	event_fileimg.transferTo(f);
	} catch (IllegalStateException | IOException e) {
	e.printStackTrace();
	}
	
	noticeDao.insertnotice(nvo);
	int res=noticeDao.select_notice_num();
	evo.setNotice_number(res);
	eventdao.event_insert(evo);
	
	
}
}
