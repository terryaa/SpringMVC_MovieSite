package kr.or.kosta.mvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kosta.dto.MovieVO;
import kr.or.kosta.dto.SearchVO;
import kr.or.kosta.mvc.dao.GM_MovieDao;
import kr.or.kosta.mvc.dao.MovieDao;
import kr.or.kosta.mvc.service.MovieService;

@RestController
public class SearchController {
	@Autowired
	private MovieDao dm;
	
	@Autowired
	private MovieService mservice;
	
	@Autowired
	private GM_MovieDao mdao;
	//�˻��� �����ϱ� ���� ��Ʈ�ѷ�
	@GetMapping("/u_search")
	public List<List<String>> Search(SearchVO sevo) {
		
		List<MovieVO> list1 = dm.moviesearch(sevo);//�˻������ ����Ʈ�� �޾ƿ�
		List<String> list = new ArrayList<>();
		int count = 0;
		for(MovieVO mvo: list1) {
			if(count == 20) {
				break;
			}else {
			String title = mvo.getMovie_title();
			//�޾ƿ� ����Ʈ���� ��ȭ�� ���� �������� ��� ��������Ƿ�, ���߿� �ʿ��� ������ ��ȭ ������ �̾� ����Ʈ�� ����� �۾��̴�.
			list.add(title.trim());
			count++;
			}
		}
		
		List<List<String>> search_list = mservice.make_movie_list(list);
//		m.addAttribute("search_list",search_list);
		return search_list;
	}
	
}
