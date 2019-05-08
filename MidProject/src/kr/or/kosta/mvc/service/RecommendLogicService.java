package kr.or.kosta.mvc.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.or.kosta.dto.MovieVO;
import kr.or.kosta.dto.UserTagWeightedList;
import kr.or.kosta.dto.TagVO;
import kr.or.kosta.mvc.dao.MemberDao;
import kr.or.kosta.mvc.dao.MovieDao;
import kr.or.kosta.mvc.dao.RecommendDao;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
@Service
public class RecommendLogicService {

	@Autowired
	RecommendDao recommendDao;
	@Autowired
	MemberDao memberDao;

	public String getUserTagWeightedList() {


		List<Integer> member_numbers=memberDao.getMemberNumberAll();
		List<TagVO> genre_list=recommendDao.getGenreTagList();
		List<UserTagWeightedList> utwl_list=new ArrayList<>();

		//태그별 가중치를 누적하여 평균을 구하는데 쓰이는 클래스객체.
		UserTagAverageAlgo utaa=new UserTagAverageAlgo();
		utaa.initialize(genre_list);
		
		
				
				//실제 모든 멤버로 돌리는 코드
				//******주의 시간꽤걸림!!********
////		for(String member_number: member_numbers) {
////		int member_number_int=Integer.parseInt(member_number);
		for(Integer member_number: member_numbers) {
			int member_number_int=member_number;
			System.out.println(member_number_int);
			//태그별 가중치 평균과 유저의 가중치값을 비교하기위해 리스트로 유저의 태그별 가중치정보를 저장
			UserTagWeightedList utwl = new UserTagWeightedList();
			utwl.setRank_div_mylist_moviecount(recommendDao.getRankDivMyListMovieCount(member_number_int));
			// 모든 멤버에 대한 리스트 구하는 코드 추가해야함.
			// 한 유저가 가지고있는 마이리스트들의 모든 영화가가진 태그들과 그 태그들의 가중치를 불러옴
			utwl.setMember_number(member_number_int);
			utwl = recommendDao.getMyList(utwl);
			//21개의 장르를 순서대로 주입. 
			utwl.setGenreList(genre_list);
			//20%안에들어가는 영화수중 mylist에있는 영화 수  / 전체 mylist들의 영화수

			utwl_list.add(utwl);
			//유저의 가중치 알고리즘에 주입 (축척)
			utaa.accumulateWeightedSum(utwl);
		}
		
				
				
				
		//태그별 가중치 평균과 유저의 가중치값을 비교하기위해 리스트로 유저의 태그별 가중치정보를 저장
//		UserTagWeightedList utwl = new UserTagWeightedList();
//
//		//20%안에 드는 영화수 /mylist에있는 전체영화수를 불러옴
//		utwl.setRank_div_mylist_moviecount(recommendDao.getRankDivMyListMovieCount(11));
//		// 한 유저가 가지고있는 마이리스트들의 모든 영화가가진 태그들과 그 태그들의 가중치를 불러옴
//		utwl.setMember_number(11);
//		utwl = recommendDao.getMyList(utwl);
//		//21개의 장르를 순서대로 주입. 
//		utwl.setGenreList(genre_list);
//		//20%안에들어가는 영화수중 mylist에있는 영화 수  / 전체 mylist들의 영화수
//
//		utwl_list.add(utwl);
//		//유저의 가중치 알고리즘에 주입 (축척)
//		utaa.accumulateWeightedSum(utwl);
//		
//		
//		
//		
//		utwl=new UserTagWeightedList();
//		utwl.setMember_number(12);
//		utwl = recommendDao.getMyList(utwl);
//		//유저의 태그 리스트에 장르가 없으면 추가. 
//		utwl.setGenreList(genre_list);
//		//20%안에들어가는 영화수중 mylist에있는 영화 수  / 전체 mylist들의 영화수
//		utwl.setRank_div_mylist_moviecount(recommendDao.getRankDivMyListMovieCount(12));
//		//유저들의 리스트 저장
//		utwl_list.add(utwl);
//		//유저의 가중치 알고리즘에 주입 (축척)
//		utaa.accumulateWeightedSum(utwl);
		
		UserTagAverageDistanceCal utadc=new UserTagAverageDistanceCal();
		
		//유저별로 전체 태그 평균과 자기가 가지고있는 태그리스트의 태그별 차를 계산하여 다시 리스트에 저장.
		utwl_list=utadc.getUserTagWeigthedList_List(utwl_list, utaa);
		
		
		// 최종 유저별 평균-유저 가중치 사용방법
		// 리턴은 List<double> 형태로 나오게된다. 
		// List<double> : 0~20 - 장르 가중치 차이
		//                21~25 - 배우 가중치 차이
		//                26~30 - 감독 가중치 차이
		//                31 - 대중성( 모든회원의 20%안의영화개수/mylist전체개수 의 합을 멤버 숫자로 나눈수  - 각 회원의 20%안의영화개수/mylist전체개수) 차이
		//				  32 - 배우 카운트 차이  ( 배우 태그 전체 수/ 전체 멤버수  - 각 회원의 배우 태그 전체수)
		//                33 - 감독 카운트 차이  ( 감독 태그 전체 수/ 전체 멤버수  - 각 회원의 감독 태그 전체수)
		System.out.println(utwl_list.get(0).getAverge_distance_list());
		System.out.println(utwl_list.get(1).getAverge_distance_list());
		
		
		//모든 유저가 반복하도록 코드 수정해야함. 지금은 한명에대한 코드.
		//태그가 들어있는 리스트를 iterate하며 태그별 가중치 합산과 카테고리별 가중치 합산을 진행한다.
		

		/*
		 * utwl에 들어있는 멤버번호, 멤버가 가진 모든 리스트에대한 태그와 태그의 가중치들 정보가 저장되어있음. 연수씨꺼!!!
		 */

//		List<UserTagWeightedList> user_list = null;
//
		String path = "C:\\bigdataStudy\\SpringProject\\SpringProject\\MidProject\\WebContent\\resources\\data\\";
		
		StringBuffer svp = new StringBuffer();
		svp.append(path).append("utwl_list").append(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).append(".csv");
		PrintWriter pw = null;

		try {

			pw = new PrintWriter(new File(svp.toString()));
			StringBuffer sb = new StringBuffer();

			int size = utwl_list.size();
				for (int i = 0; i < size; i++) {
					System.out.println("유저번호"+utwl_list.get(i).getMember_number());
					sb.append(utwl_list.get(i).getMember_number()).append(",");
					System.out.println("사이즈"+utwl_list.get(i).getAverge_distance_list().size());
					int size_list = utwl_list.get(i).getAverge_distance_list().size();
					for (int j = 0; j <size_list; j++) {
						System.out.println("값"+j+utwl_list.get(i).getAverge_distance_list().get(j));
						sb.append(utwl_list.get(i).getAverge_distance_list().get(j)).append(",");
					}
					sb.append("\n");
				}
				
				pw.write(sb.toString());
				pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("마무리");

		return "success";
	}
	
	
}
