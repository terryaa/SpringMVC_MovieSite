package kr.or.kosta.mvc.dao;

import java.util.List;
import java.util.Map;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import kr.or.kosta.dto.GM_Movie_tagVO;

@Repository
public class AlgorithmDao {
	
	@Autowired
	private SqlSessionTemplate ss;
	
	
	public List<GM_Movie_tagVO> getMovie_number_InTags(List<String> list) {
		return ss.selectList("alg.getMovie_number_InTags",list);
	}
	
	public int getMovie_tot_price(String movie_number) {
		return ss.selectOne("alg.getMovie_tot_price",movie_number);
	}
	
	public List<String> getMember_of_sold_log(Map<String, Object> map) {
		return ss.selectList("alg.getMember_of_sold_log",map);
	}
	
	
	
}
