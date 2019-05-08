package kr.or.kosta.mvc.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.kosta.dto.MovieVO;
import kr.or.kosta.dto.WatchLogVO;

@Repository
public class ResearchDao {
	@Autowired
	private SqlSessionTemplate ss;
	
	public int newbee() {
		return ss.selectOne("research.newbee");
	}
	public List<String> movietitle(){
		return ss.selectList("research.mt");
	}
	public String mnum(MovieVO mvvo) {
		return ss.selectOne("research.mnum",mvvo);
	}
	public void insertresearch(WatchLogVO wlvo) {
		ss.insert("research.insertresearch",wlvo);
	}
}
