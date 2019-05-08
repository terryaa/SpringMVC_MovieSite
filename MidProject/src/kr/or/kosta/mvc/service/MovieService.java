package kr.or.kosta.mvc.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import kr.or.kosta.dto.GM_Movie_RatingVO;
import kr.or.kosta.dto.GM_ReviewVO;
import kr.or.kosta.mvc.dao.GM_MovieDao;
import kr.or.kosta.mvc.dao.MovieDao;

@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class,readOnly=true)
@Service
public class MovieService {
	
	private StringBuilder sb;

	@Autowired
	MovieDao movieDao;
	
	@Autowired
	GM_MovieDao mdao;
	
	//영화 판권관련 정보를 불러오는 서비스 로직.
	public HashMap<String,Integer> getMovieStatistic(){
		HashMap<String,Integer> statistic=new HashMap<>();
		statistic.put("purchase_thismonth", movieDao.getMoviePurchaseCount_thisMonth());
		statistic.put("purchase_pastmonth", movieDao.getMoviePurchaseCount_pastMonth());
		statistic.put("copyrightcost_thismonth", movieDao.getMovieCopyRightCost_thisMonth());
		statistic.put("copyrightcost_pastmonth", movieDao.getMovieCopyRightCost_pastMonth());
		statistic.put("investment_thismonth", movieDao.getMovieInvestment_thisMonth());
		statistic.put("investment_pastmonth", movieDao.getMovieInvestment_pastMonth());
		return statistic;
	}
	

	// 영화제목으로 
	public List<List<String>> make_movie_list(List<String> movie_list) {

		

		List<List<String>> movie_src_list = new ArrayList<List<String>>();
		for (String i : movie_list) {
			
			String src = getMovie_img(i);
			int num = 0;
			while(num <200) {
				if(src.equals("NoData") || src.equals("error")) {
					src = getMovie_img(i);
					num++;
				}else {
					break;
				}
				
			}
			num = 0;
			List<String> list = new ArrayList<String>();
			String m_code = mdao.getMovie_number(i);
			list.add(m_code);
			list.add(i);
			list.add(src);
			movie_src_list.add(list);
		}

		return movie_src_list;
	}
	
	

	public String getMovie_img(String txt) {
		String clientId = "U8YzQ_7AjBaS8A19j_uR";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "YSP3_ozkfq";// 애플리케이션 클라이언트 시크릿값";\
		int display = 100; // 검색결과갯수. 최대100개

		try {
			int start = 1;
			while (true) {

				String text = URLEncoder.encode(txt, "utf-8");
				String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + text + "&display=" + display
						+ "&start=" + start + "&";

				URL url = new URL(apiURL);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("X-Naver-Client-Id", clientId);
				con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
				int responseCode = con.getResponseCode();
				BufferedReader br;
				if (responseCode == 200) {
					br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				} else {
					br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
				}
				sb = new StringBuilder();
				String line;

				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}

				JSONParser parser = new JSONParser();
				JSONObject obj = (JSONObject) parser.parse(sb.toString());

				JSONArray parse_items = (JSONArray) obj.get("items");
				if (parse_items == null) {
					return "NoData";
				}
				for (Object parse_mv : parse_items) {

					JSONObject i = (JSONObject) parse_mv;
					String titleEx = i.get("title").toString();

					if (titleEx.length() > 7) {
						String title = titleEx.substring(3, titleEx.length() - 4);
						if (txt.equals(title)) {

							return i.get("image").toString();
						}
					}

				}
				start++;
				br.close();
				con.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";

	}
	
	public List<HashMap<String, String>> getComment(String movie_number){
		return mdao.get_movie_comment(movie_number);
	}
	
	public int addreview(String movie_number, String text, int member_number) {
		GM_ReviewVO rvo = new GM_ReviewVO();
		rvo.setMember_number(member_number);
		rvo.setMember_number(member_number);
		rvo.setUsedwords_contents(text);
		
		int res = mdao.add_review(rvo);
		
		return res;
		
	}
	
	public String getMovie_Data(String txt) {
		String clientId = "U8YzQ_7AjBaS8A19j_uR";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "YSP3_ozkfq";// 애플리케이션 클라이언트 시크릿값";\
		int display = 100; // 검색결과갯수. 최대100개

		try {
			int start = 1;
			while (true) {

				String text = URLEncoder.encode(txt, "utf-8");
				String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + text + "&display=" + display
						+ "&start=" + start + "&";

				URL url = new URL(apiURL);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("X-Naver-Client-Id", clientId);
				con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
				int responseCode = con.getResponseCode();
				BufferedReader br;
				if (responseCode == 200) {
					br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				} else {
					br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
				}
				sb = new StringBuilder();
				String line;

				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}

				JSONParser parser = new JSONParser();
				JSONObject obj = (JSONObject) parser.parse(sb.toString());

				JSONArray parse_items = (JSONArray) obj.get("items");
				if (parse_items == null) {
					return "NoData";
				}
				for (Object parse_mv : parse_items) {

					JSONObject i = (JSONObject) parse_mv;
					String titleEx = i.get("title").toString();

					if (titleEx.length() > 7) {
						String title = titleEx.substring(3, titleEx.length() - 4);
						if (txt.equals(title)) {

							return i.get("link").toString();
						}
					}

				}
				start++;
				br.close();
				con.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";

	}

	public int addrating(String movie_number, int rating, int member_number) {
		GM_Movie_RatingVO mrvo = new GM_Movie_RatingVO();
		mrvo.setMovie_rating(rating);
		mrvo.setMember_number(member_number);
		mrvo.setMovie_number(movie_number);
		
		int chk_code = mdao.chk_Movie_rating(mrvo);
		if(chk_code == 0) {
			mdao.addMovie_rating(mrvo);
			return 1;
		}else if(chk_code == 1) {
			mdao.updateMovie_rating(mrvo);
			return 1;
		}else {
			return 0;
		}
		
	}
	

}
