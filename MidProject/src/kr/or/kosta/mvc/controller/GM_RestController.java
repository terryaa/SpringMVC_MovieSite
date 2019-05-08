package kr.or.kosta.mvc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.or.kosta.dto.GM_AgeVO;
import kr.or.kosta.dto.GM_MovieVO;
import kr.or.kosta.dto.GM_RankVO;
import kr.or.kosta.mvc.dao.GM_MovieDao;
import kr.or.kosta.mvc.service.MovieService;

@RestController
@RequestMapping("/GM")
public class GM_RestController {

	@Autowired
	GM_MovieDao mdao;

	@Autowired
	MovieService mservice;

	@RequestMapping("/getchart_data")
	public GM_AgeVO getchar_data() {
		GM_AgeVO avo = mdao.get_login_log_age();

		return avo;
	}

	@GetMapping("/getLink")
	public Map<String, Object> viewMovieDetail(String title, HttpSession session) {
		String movie_number = mdao.getMovie_number(title);

		System.out.println(title);
		Map<String, String> getRating_Map = new HashMap<String, String>();
		getRating_Map.put("movie_title", title);
		getRating_Map.put("member_id", session.getAttribute("uid").toString());
		String my_rating = mdao.getMyRating(getRating_Map);

		GM_MovieVO mov = mdao.getMovieVO(title);

		int p = mov.getMovie_price();
		boolean flag = false;
		int chk_event = mdao.chk_event(movie_number);
		if (chk_event != 0) {
			p--;
			flag = true;
		}

		int movie_price = 0;
		if (p == 5) {
			movie_price = 10000;
		} else if (p == 4) {
			movie_price = 5000;
		} else if (p == 3) {
			movie_price = 2500;
		} else if (p == 2) {
			movie_price = 1000;
		} else if (p == 1) {
			movie_price = 0;
		}
		if (my_rating == null) {
			my_rating = "0";
		}
//		String my_rating = "0";

		List<HashMap<String, String>> comment_list = mservice.getComment(movie_number);

		List<String> tag_list = mdao.get_movie_tag_list(movie_number);

		Map<String, Object> data = new HashMap<String, Object>();
		String URL = mservice.getMovie_Data(title);
		Document doc;
		try {
			doc = Jsoup.connect(URL).get();

			Elements content = doc.select("#content");
			String story1 = content.select(".story_area>.title_area").toString();
			String story2 = content.select(".story_area>.h_tx_story").toString();
			String story3 = content.select(".story_area>.con_tx").toString();
			String m_Title = content.select(".h_movie>a:nth-child(1)").text();
			String m_EngTitle = content.select(".h_movie2:nth-child(2)").text();
			String m_info_step1 = content.select(".info_spec>dd>p>span").text();
			String m_info_director = content.select(".info_spec>dd:nth-child(4)").text();
			String m_info_actor = content.select(".info_spec>dd:nth-child(6)>p").text();
			String m_info_filmrate = content.select(".info_spec>dd:nth-child(8)").text();
			String m_img = content.select(".poster:nth-child(2) img").toString();
			String m_story = story1 + story2 + story3;

			data.put("m_Title", m_Title);
			data.put("m_EngTitle", m_EngTitle);
			data.put("m_info_step1", m_info_step1);
			data.put("m_info_director", m_info_director);
			data.put("m_info_actor", m_info_actor);
			data.put("m_info_filmrate", m_info_filmrate);
			data.put("m_img", m_img);
			data.put("m_story", m_story);
			data.put("my_rating", my_rating);
			data.put("m_price", movie_price);
			data.put("tag_list", tag_list);
			data.put("event", flag);
			data.put("comment_list", comment_list);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;

	}

	@GetMapping("/setRank")
	public String setRank(String movie_number, String rank, HttpSession session) {

		String member_id = session.getAttribute("uid").toString();

		int member_code = mdao.getMember_number(member_id);
		GM_RankVO rankvo = new GM_RankVO();
		rankvo.setMember_number(member_code);
		rankvo.setMovie_number(movie_number);
		rankvo.setRank_number(Integer.parseInt(rank));
		int res_chk = mdao.chk_rank_list(rankvo);

		if (res_chk == 0) {
			mdao.add_rank(rankvo);
			return "success";
		} else if (res_chk == 1) {
			mdao.update_rank(rankvo);
			return "success";
		} else {
			return "error";
		}

	}

	@GetMapping("/setM_data")
	public String setM_data(String movie_number, int rating, String text, HttpSession session) {

		String member_id = session.getAttribute("member_id").toString();

		int member_code = mdao.getMember_number(member_id);

		int res_rating = mservice.addrating(movie_number, rating, member_code);

		int res_review = mservice.addreview(movie_number, text, member_code);

		if (res_rating == 1 && res_review == 1) {
			return "success";
		} else {
			return "error";
		}

	}

}
