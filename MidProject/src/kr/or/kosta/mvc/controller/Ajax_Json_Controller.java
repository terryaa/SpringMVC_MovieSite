package kr.or.kosta.mvc.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.or.kosta.dto.GM_Movie_tagVO;
import kr.or.kosta.mvc.dao.AlgorithmDao;


@RestController
@RequestMapping("/rest")
public class Ajax_Json_Controller {
	

	@Autowired
	AlgorithmDao adao;
	
	
	@RequestMapping("/getdata")
	public List<List<Integer>> getdata(String tags){
		System.out.println(tags);
		StringTokenizer stz = new StringTokenizer(tags,",");
		List<String> tag_list = new ArrayList<String>();
		while(stz.hasMoreTokens()) {
			tag_list.add(stz.nextToken().trim());
		}
		System.out.println(tag_list);
		
		List<List<Set<Integer>>> T_list = test(tag_list);
		
		List<List<Integer>> list = price_list(T_list);
		
		return list;
	}
	
	public List<List<Set<Integer>>> test(List<String> list) {
		// 영화에 해당되는 태그들 모음 map
		// movie_tags_map = key : 영화번호 value : 태그번호s
		Map<String, List<String>> movie_tags_map = new HashMap<String, List<String>>();

		// 가상 데이터 (태그들)
//		List<String> list = new ArrayList<String>();
//		list.add("C0000004"); // 멜로/로맨스
//		list.add("C0000048"); // 코미디
//		list.add("10028667"); // 박보영
		int tags_size = list.size();

		// Movie_Tag 테이블에서 받은 태그들을 포함한 영화번호, 태그번호를 리스트로 DB조회
		List<GM_Movie_tagVO> mtvo_in_tag_list = adao.getMovie_number_InTags(list);

		// 들고온 DB 데이터를 각각 row로 분리
		for (GM_Movie_tagVO vo : mtvo_in_tag_list) {
			// map의 키 값 중에 해당 영화번호가 있으면
			if (movie_tags_map.containsKey(vo.getMovie_number())) {
				// value의 리스트를 가져와 태그를 add(추가) 한다.
				movie_tags_map.get(vo.getMovie_number()).add(vo.getTag_Table_num());
				// map의 키 값 중에 해당 영화번호가 없다면
			} else {
				// tag_list를 만들어 해당 태그를 add(추가) 한다.
				List<String> tag_list = new ArrayList<String>();
				tag_list.add(vo.getTag_Table_num());
				movie_tags_map.put(vo.getMovie_number(), tag_list);
			}
		}

		// tag_matching_list = [1개일치하는 영화(list),2개일치하는 영화(list) ,
		// ...,x개 일치하는 영화(list)] => index = 0 ~ x-1
		List<List<String>> tag_matching_list = new ArrayList<List<String>>();

		// movie_tags_map = key : 영화번호 value : 태그번호s
		//
		for (int i = 1; i <= tags_size; i++) {
			// 같은 갯수의 태그가 일치하는 영화번호 리스트
			List<String> m_number_list = new ArrayList<String>();
			// 해당 map에 키값을 Set으로 받아온다.
			Set<String> keys = movie_tags_map.keySet();
			// Set의 키값을 들고올 iterator
			Iterator<String> it = keys.iterator();
			// 남은 키값이 있다면...
			while (it.hasNext()) {
				// 키값을 String 변수로 저장 | 영화번호
				String m_number = it.next();
				// 키값을 통해 태그들이 담겨있는 list를 받아온다.
				List<String> tags = movie_tags_map.get(m_number);
				// 받은 태그와 해당 영화 태그들이 일치하는 갯수가 (1, 2, 3, ..., x)
				if (tags.size() == i) {
					// 해당 리스트에 영화 번호 추가
					m_number_list.add(m_number);

				}
			}
			
			tag_matching_list.add(m_number_list);
		}
		// 검증
//		System.out.println(tot_list.get(0).size());
//		System.out.println(tot_list.get(1).size());
//		System.out.println(tot_list.get(2).size());
		
		
		// pay를 배열에 담아둠
		int[] pay = { 5, 4, 3, 2 };
		
		// ****************			 최종  (일치 갯수 * 금액)리스트 			****************
		List<List<Set<Integer>>> T_list = new ArrayList<List<Set<Integer>>>();
		
		
		for (int i = tags_size - 1; i >= 0; i--) {
			// 전부 일치하는 list => ... => 1개 일치하는 list
			List<String> m_number_list = tag_matching_list.get(i);
			// 중복 제거를 위한 Set
			Set<String> movie_num_set = new HashSet<String>();
			// list의 영화번호 중복을 Set에 넣어 중복 제거
			for (String m_number : m_number_list) {
				movie_num_set.add(m_number);
			}
			
			// Set의 값을 가져올 iterator | 영화번호
			Iterator<String> it = movie_num_set.iterator();
			
			// 아래 금액별 리스트를 한번에 관리할 중간 리스트
			List<Set<Integer>> M_list = new ArrayList<Set<Integer>>();
			
			// 해당 영화를 10000원에 구매한 회원Set , set으로 만든 이유 : 중복을 제거
			Set<Integer> list_10000 = new HashSet<Integer>();
			Set<Integer> list_5000 = new HashSet<Integer>();
			Set<Integer> list_2500 = new HashSet<Integer>();
			Set<Integer> list_1000 = new HashSet<Integer>();
			// movie_num_set에서 영화번호가 있다면
			while (it.hasNext()) {
				// 영화번호를 가져와 변수에 저장
				String movie_number = it.next();
				// 해당 영화를 얼마에 구매했는지 조회
				for (Integer p : pay) {
					// 구매 내역 조회를 위한 map
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("movie_number", movie_number);
					map.put("movie_price", p);
					
					// A영화를 a가격에 구매한 사람들  | 회원번호 리스트
					List<String> member_number_list = adao.getMember_of_sold_log(map);
					
					// 검증 | 영화번호가 4번씩 출력 => 10000원, 5000원, 2500원, 1000원
//					System.out.println(movie_number);
//					System.out.println(member_number_list);
					
					// 10000원으로 구매했다면
					if (p == 5) {
						for (String m_n : member_number_list) {
							// 해당 리스트에 추가
							list_10000.add(Integer.parseInt(m_n));
						}
					// 5000원으로 구매했다면
					} else if (p == 4) {
						for (String m_n : member_number_list) {
							// 10000원 리스트에 포함되지 x => 추가
							if (!list_10000.contains(m_n)) {
								list_5000.add(Integer.parseInt(m_n));
							}
						}
					// 2500원으로 구매했다면
					} else if (p == 3) {
						for (String m_n : member_number_list) {
							// 10000원 리스트에 포함되지 x and 5000원 리스트에 포함되지 x => 추가
							if (!list_10000.contains(m_n) && !list_5000.contains(m_n)) {
								list_2500.add(Integer.parseInt(m_n));
							}
						}
					// 1000원으로 구매했다면
					} else if (p == 2) {
						for (String m_n : member_number_list) {
							// 10000원 리스트에 포함되지 x and 5000원 리스트에 포함되지 x and 2500원 리스트에 포함되지 x => 추가
							if (!list_10000.contains(m_n) && !list_5000.contains(m_n) && !list_2500.contains(m_n)) {
								list_1000.add(Integer.parseInt(m_n));
							}
						}
					}
				}
				// 한번에 관리하기 위해 중간 리스트에 추가
				M_list.add(list_10000);
				M_list.add(list_5000);
				M_list.add(list_2500);
				M_list.add(list_1000);
			}
			// 최종 리스트에 추가 | 전부 일치하는 리스트 부터 추가됨 : 이유는 for문을  tags_size - 1 개 부터  0 까지 반복
			T_list.add(M_list);
		}
		
		return T_list;
	}
	
	
	public List<List<Integer>> price_list(List<List<Set<Integer>>> T_list){
		
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		
		int t_size = T_list.size();
		for(int i=0; i<t_size; i++) {
			List<Integer> list_inner = new ArrayList<Integer>();
			for(int j=0; j<4; j++) {
				
				int p=0;
				if(j==0) {
					p = 10000;
				}else if(j==1) {
					p = 5000;
				}else if(j==2) {
					p = 2500;
				}else if(j==3) {
					p = 1000;
				}
				BigDecimal a = new BigDecimal(T_list.get(i).get(j).size() * p);
				BigDecimal b = new BigDecimal((t_size-i));
				BigDecimal c = new BigDecimal(t_size);
				BigDecimal price = a.multiply(b).divide(c,0,BigDecimal.ROUND_FLOOR).setScale(-3,BigDecimal.ROUND_DOWN);
				list_inner.add(price.intValue());
			}
			list.add(list_inner);
		}
		
		return list;
	}
	}

	
	
