package kr.or.kosta.mvc.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kosta.mvc.service.MovieService;

@RestController
@Controller
//��ȭ ���� �� �Ǳ� ���� ���Աݾ�, ���Ծ��� �ٷ������ ��Ʈ�ѷ�
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@GetMapping("/getMovieStatistic")
	public HashMap<String,Integer> getMovieStatistic() {
		
		return movieService.getMovieStatistic();
	}
}
