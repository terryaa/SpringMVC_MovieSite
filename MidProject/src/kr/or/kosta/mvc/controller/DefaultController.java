package kr.or.kosta.mvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import kr.or.kosta.dto.InserttagVO;
import kr.or.kosta.dto.MemberVO;
import kr.or.kosta.dto.MovieVO;
import kr.or.kosta.dto.MovietagVO;
import kr.or.kosta.mvc.dao.CommunityDao;
import kr.or.kosta.mvc.dao.DynamicExampleDao;
import kr.or.kosta.mvc.dao.GM_MovieDao;
import kr.or.kosta.mvc.dao.TagListDao;
import kr.or.kosta.mvc.service.MovieService;
import kr.or.kosta.mvc.service.RecommendLogicService;

import static kr.or.kosta.mvc.dao.CommunityDao.countlist;
import static kr.or.kosta.mvc.dao.TagListDao.namelist;

@Controller
public class DefaultController {
	
	@Autowired
	private MovieService mservice;

	@Autowired
	private DynamicExampleDao dao; 
	
	@Autowired
	private GM_MovieDao mdao; 
	
	@Autowired
	private RecommendLogicService rls;
	
	@GetMapping("/")//메인페이지
	public String MainPage(Model m) {
		List<String> title_list_popular = mdao.get_m_title_popular5();
		List<String> title_list_boxOffice = mdao.get_m_title_boxOffice5();
		List<String> title_list_lastviewed = mdao.get_m_title_lastviewed5(11);
		List<String> title_list_saved = new ArrayList<String>();
		List<String> title_list_pay = mdao.get_m_title_pay5();
		List<String> title_list_free = mdao.get_m_title_free5();
		List<String> title_list_similar_mylike = new ArrayList<String>();
		List<String> title_list_similar_tome = new ArrayList<String>();
		
		List<List<String>> list_popular = mservice.make_movie_list(title_list_popular);
		List<List<String>> list_boxOffice = mservice.make_movie_list(title_list_boxOffice);
		List<List<String>> list_lastviewed = mservice.make_movie_list(title_list_lastviewed);
		List<List<String>> list_saved = mservice.make_movie_list(title_list_saved);
		List<List<String>> list_pay = mservice.make_movie_list(title_list_pay);
		List<List<String>> list_free = mservice.make_movie_list(title_list_free);
		List<List<String>> list_similar_mylike = mservice.make_movie_list(title_list_similar_mylike);
		List<List<String>> list_similar_tome = mservice.make_movie_list(title_list_similar_tome);
		
		m.addAttribute("list_popular", list_popular);
		m.addAttribute("list_boxOffice", list_boxOffice);
		m.addAttribute("list_lastviewed", list_lastviewed);
		m.addAttribute("list_saved", list_saved);
		m.addAttribute("list_pay", list_pay);
		m.addAttribute("list_free", list_free);
		m.addAttribute("list_similar_mylike",list_similar_mylike);
		m.addAttribute("list_similar_tome", list_similar_tome);
		
		
		return "u_main";
	}
	
	@GetMapping("/movie_watch")
	public String movie_watch(String movie_number, HttpSession session, Model m) {

		m.addAttribute("movie_number", movie_number);
		return "movie_watch";
	}
	
	@GetMapping("/{path}")
	public String ex1(@PathVariable String path) {
		return path;
	}
	//각각 경로를 알아서 보내기 위한 Get Mapping
	
	

	@GetMapping(value={"/blank5"})
	public String movielist5(Model m) {
		Map<String, String> map= new HashMap<String, String>();		
		m.addAttribute("list",dao.getMovieList3(map));
		return "blank5";
	}
	//blank5 ( 영화 검색 및 추가 등)를 위한 Get방식의 Mapping
	
	@GetMapping("/chartjs")
	public String sendchartvalue(String cmd, Model m) {
		List<Integer> result = null;
		result = dao.getprice1();
		System.out.println(result);
		m.addAttribute("chart1",dao.getprice1());
		return "chartjs";
	}
	//차트에 값을 보내기 위한 Mapping.
	
	@PostMapping("/blank5")
	public String searchTitle5(String searchType,String search,Model m) {
		Map<String, String> map= new HashMap<String, String>();
		map.put("searchType", searchType);
		map.put("search", search); 
		System.out.println(search);
		m.addAttribute("list",dao.getMovieList3(map)); 
		m.addAttribute("searchType",searchType);
		return "blank5";
	}
	//검색에 값을 받기 위한 Post방식의 Mapping
	
	
	@RequestMapping(value="updatemovieprice.do", method=RequestMethod.POST)
	public String updatemovieprice(@ModelAttribute MovieVO vo) throws Exception{
		dao.updateprice(vo);
		return "redirect:blank5"; 
	}
	
	@RequestMapping(value="moviepriceyearupdate.do", method=RequestMethod.POST)
    public String updatemoviepriceyear(@ModelAttribute MovieVO vo) throws Exception{
        dao.updatemoviepriceyear();
        
        return "redirect:blank5";
    } 
	
	//영화에서 영화의 가격이 업데이트시 매핑
	
	@RequestMapping(value="updatestatus.do", method=RequestMethod.POST)
	public String updatestatus(@ModelAttribute MovieVO vo) throws Exception{
		dao.updatestatus(vo);
		return "redirect:blank5";
	}
	//영화에서 영화의 active_status_number 수정을 위한 매핑
	
	
	@RequestMapping(value="insert.do", method=RequestMethod.POST)
	public String insert(@ModelAttribute MovieVO vo, @ModelAttribute MovietagVO voo) throws Exception{
		dao.createmovie(vo);
		dao.beforeinsertmovie(voo);
		
		
		return "redirect:blank5";
	}
	//영화 추가를 위한 매핑
	
	@RequestMapping(value="inserttag.do",method=RequestMethod.POST)
	public String inserttag(@ModelAttribute InserttagVO vo ) throws Exception{
		
		TagListDao tldao = new TagListDao();
		
		List<MovieVO> movienumberlist =dao.getsavetaglist();
		
		String movie_num="";
	for(MovieVO mv: movienumberlist) {
		movie_num=mv.getMovie_number();
		System.out.println("movie_num : "+movie_num);
		
		
		
		for(int i = 0; i<=9; i++) {
			vo.setMovie_number(movie_num);
			
			
			
			tldao.connectR(movie_num);
			String tag_name;
			tag_name = TagListDao.namelist[i];
			vo.setTag_name(tag_name);
			Map<String,String> map = new HashMap<String,String>();
			
			map.put("movie_number",movie_num);
			map.put("tag_name",tag_name);
			
				
			if(dao.checktagname(tag_name)==0) {
				dao.inserttag(vo);
				dao.insertmovietag(map);
			} else {
				dao.insertmovietag(map);
			}
		
		
		}	
		}
		System.out.println("insert end");
		return "redirect:blank5";
	}	
	//댓글 탑 10개를 출력하기 위한 매핑

	
	
	@RequestMapping(value="updatecommunity.do",method=RequestMethod.POST)
	public String insertMemberCommunity(@ModelAttribute MemberVO vo ) throws Exception{
		CommunityDao co = new CommunityDao();
		
		
		int mem_community_number = 0;
		
		rls.getUserTagWeightedList();
		
		co.connectR();
		
		
		
		int member_list_number = dao.memberfinalnumber();
		
		
		for(int i=11; i<=member_list_number-10; i++) {
			System.out.println("시작!" + countlist[i-10]);
			vo.setMember_number(i);
			System.out.println("1까지성공");
			mem_community_number = countlist[i-10];
			System.out.println("2까지 성공");
			vo.setMember_community_number(mem_community_number);
			System.out.println("3까지성공");
			dao.updatecommunitymember(vo);
			System.out.println("4까지성공");
			
			
		}
		
	return "redirect:blank5";
	}
//군집화를 위한 매핑
	@RequestMapping(value="recommendmovie.do",method=RequestMethod.POST)
    public String recommendmovie(HttpSession session) throws Exception{
		String id = String.valueOf(session.getAttribute("uid"));
        int member_number = dao.user_number(id);
        System.out.println("추천영화 : " + dao.outputmovierecommend(member_number));
        return "redirect:blank5";
    }
	

	
	}
