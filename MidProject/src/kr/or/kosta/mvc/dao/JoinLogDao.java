package kr.or.kosta.mvc.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.kosta.mvc.inter.LogDao;

@Repository
public class JoinLogDao implements LogDao {
	@Autowired
	private SqlSessionTemplate ss;
	@Override
	public int getLogCount_All() {
		return ss.selectOne("joinlog.joinlogcount_all");
	}
	@Override
	public int getLogCount_CurrentDate() {
		return ss.selectOne("joinlog.joinlogcount_currentdate");
	}
	@Override
	public List<HashMap<String, BigDecimal>> getLogCount_Days() {
		// TODO Auto-generated method stub
		return ss.selectList("joinlog.joinlogcount_days");
	}
	@Override
	public List<HashMap<String, BigDecimal>> getLogCount_Months() {
		// TODO Auto-generated method stub
		return ss.selectList("joinlog.joinlogcount_months");
	}
	@Override
	public List<HashMap<String, BigDecimal>> getLogCount_Years() {
		// TODO Auto-generated method stub
		return ss.selectList("joinlog.joinlogcount_years");
	}
	@Override
	public int getLogCount_Yesterday() {
		// TODO Auto-generated method stub
		return ss.selectOne("joinlog.joinlogcount_yesterday");
	}

}
