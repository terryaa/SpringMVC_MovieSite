package kr.or.kosta.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.kosta.dto.Tag_ProfitVO;
import kr.or.kosta.dto.YS_MovieVO;

@Repository
public class TagDao {
	@Autowired
	private SqlSessionTemplate ss;
	
	public List<YS_MovieVO> tag_in_target(Map<String, String> map) {
		List<YS_MovieVO> list = ss.selectList("tag.tag_search", map);
		
		return list;
		
	}
	
	public List<Tag_ProfitVO> tag_profit() {
		List<Tag_ProfitVO> list = ss.selectList("tag.tag_profit");
		System.out.println(list.size());
		return list;
		
	}
	
	public void tag_ex(String tag_Table_Num) {
		ss.update("tag.tag_ex", tag_Table_Num);
		
	}

}
