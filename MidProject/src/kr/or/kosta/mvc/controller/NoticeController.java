package kr.or.kosta.mvc.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.or.kosta.dto.NoticeVO;
import kr.or.kosta.mvc.dao.NoticeDao;

//@RestController("/movie")
@RestController
@Controller
public class NoticeController {
	
	@Autowired
	private NoticeDao noticeDao;
	
	//���������� ����ϴ� ��Ʈ�ѷ�
	@RequestMapping("/noticeselect")
	public List<NoticeVO> shownotice() {
		List<NoticeVO> list = noticeDao.shownotice();
		return list;
	}
	
	//���������� �Է��ϴ� ��Ʈ�ѷ�
	@RequestMapping("/innotice")
	public String insertnotice(NoticeVO nvo) throws Exception {
		noticeDao.insertnotice(nvo);
		return"success";
	}
	
}
