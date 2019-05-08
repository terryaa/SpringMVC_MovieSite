package kr.or.kosta.mvc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.kosta.dto.MovieVO;
import kr.or.kosta.dto.MyMemberVO;
import kr.or.kosta.dto.UserTagWeightedList;
import kr.or.kosta.dto.TagVO;

@Repository
public class RecommendDao {
	@Autowired
	private SqlSessionTemplate ss;
	
	//한 멤버의 모든 리스트에 잇는 영화들에 대한 태그들과 태그들의 가중치를 계산하여 불러옴.
	public UserTagWeightedList getMyList(UserTagWeightedList utwl){
		ss.selectList("recommend.getmylist_proc",utwl);
		return utwl;
	}
	public List<TagVO> getGenreTagList(){
		return ss.selectList("category.selectgenretag");
	}
	public int getCountMovieInRank(int member_number){
		return ss.selectOne("recommend.getcount_list_inrank",member_number);
	}
	public int getMyListMovieCount(int member_number) {
		return ss.selectOne("recommend.getmylist_movie_count",member_number);
	}
	public double getRankDivMyListMovieCount(int member_number) {
		return ss.selectOne("recommend.getrank_div_mylist_moviecount",member_number);
	}
	
	public int selectmynum(MyMemberVO mvo) {
		return ss.selectOne("recommend.mynum",mvo);
	}

	public int group(MyMemberVO mvo) {
		return ss.selectOne("recommend.group",mvo);
	}

	public List<MyMemberVO> member(MyMemberVO mvo) {
		return ss.selectList("recommend.grouplist",mvo);
	}

	public List<String> getgsb(MyMemberVO mvo) {
		return ss.selectList("recommend.gsblist",mvo);
	}

	public List<String> getlikeList(MyMemberVO mvo) {
		return ss.selectList("recommend.likelist",mvo);
	}
}
