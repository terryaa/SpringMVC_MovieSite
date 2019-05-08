package kr.or.kosta.mvc.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.kosta.dto.Login_LogVO;
import kr.or.kosta.dto.Movie_Sold_LogVO;
import kr.or.kosta.dto.Search_LogVO;
import kr.or.kosta.dto.View_LogVO;
import kr.or.kosta.dto.Watch_LogVO;

@Repository
public class LogInfoDao {

	@Autowired
	private SqlSessionTemplate ss;

	// �˻� �α� ����
	public List<Search_LogVO> getSearch_LogList() {
		return ss.selectList("loginfo.showSearch_Log");
	}

	// ��ȸ �α� ����
	public List<View_LogVO> getView_LogList() {
		return ss.selectList("loginfo.showView_Log");
	}

	// ��û �α� ����
	public List<Watch_LogVO> getWatch_LogList() {
		return ss.selectList("loginfo.showWatch_Log");
	}

	// �Ǹ� ��ȭ �α� ����
	public List<Movie_Sold_LogVO> getMovie_Sold_LogList() {
		return ss.selectList("loginfo.showMovie_Sold_Log");
	}

	// �α��� �α� ����
	public List<Login_LogVO> getLogin_LogList() {
		return ss.selectList("loginfo.showLogin_Log");
	}

}
