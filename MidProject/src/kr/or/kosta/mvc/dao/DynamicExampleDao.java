package kr.or.kosta.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.kosta.dto.InserttagVO;
import kr.or.kosta.dto.MemberVO;
import kr.or.kosta.dto.MovieVO;
import kr.or.kosta.dto.MovietagVO;

@Repository
public class DynamicExampleDao {
	private static DynamicExampleDao dao;

	@Autowired
	private SqlSessionTemplate ss;
	


	public static DynamicExampleDao getDao() {
		if (dao == null)
			dao = new DynamicExampleDao(); 
		return dao;
	}
	

	public List<MovieVO> getMovieList(Map<String, String> map) {
		List<MovieVO> list = ss.selectList("movie.movielist", map);
		return list;
	}
	//��ȭ �˻�â���� �ʱⰪ ����� ���Ѱ� 1
	
	public List<MovieVO> getMovieTitleList(Map<String, String> map) {
		List<MovieVO> list = ss.selectList("movie.movietitlelist", map);
		return list;
	}
	//��ȭ �˻�â���� �ʱⰪ ����� ���Ѱ� 2

	
	
	public List<Integer> getprice1() {
		return ss.selectList("movie.price");
	}
	//��Ʈ�� ���� ��ȭ ���� �̾ƿ���
	

	public List<MovieVO> getMovieList3(Map<String, String> map) {
		return ss.selectList("movie2.moviehashmap", map);
	}
	//��ȭ �˻��� ����� ���� ��

	public void updateprice(MovieVO vo){ 
		ss.update("movie.update", vo);
	}
	//��ȭ ���� ������ ���
	
	public void updatestatus(MovieVO vo){
		ss.update("movie.updatestatus", vo);
	}
	//��ȭ active_check_number ������ ���� ������Ʈ
	
	public void createmovie(MovieVO vo) {
		ss.insert("movie.insert",vo);
	}
	//��ȭ����
	
	public void beforeinsertmovie(MovietagVO vo) {
		ss.insert("movie.beforeinsert",vo);
	}
	//��ȭ �߰�. �±��߰��� ���Ŀ� �ؾ� ��.
	
	public void inserttag(InserttagVO vo) {
		ss.insert("inserttag.insert",vo);
	}
	//�±�insert dao

	public List<MovieVO> getsavetaglist() {
		return ss.selectList("inserttag.select");
	}
	//�±׸� �Է��ϱ� ���� ����Ʈ �ҷ�����
	
	public void insertmovietag(Map<String,String> map) {
		ss.insert("inserttag.insertmovietag",map);
	}
	//movie_tag ���̺� �� ������ �� ���


	public int checktagname(String tag_name) {
		return ss.selectOne("inserttag.checktagname",tag_name);
	}
	//�±��̸��ҷ�����
	
	public void updatecommunitymember(MemberVO vo) {
		ss.update("updatecommunity.update",vo);
	}
	//����ȭ �� ����� ������Ʈ
	
	public int insertcommunity(MemberVO vo) {
		return ss.selectOne("updatecommunity.select",vo);
	
	}
	//����ȭ�� �����ҷ�����
	
	public int memberfinalnumber() {
		return ss.selectOne("updatecommunity.memberfinalnumber");
	}
	//��� �� ���� ������
	public List<MovieVO> movie_advice(MovieVO vo){
        return ss.selectList("movieadvice.insert",vo);
    }
	//aop�� ���� dao

	
	public void updatemoviepriceyear() {
		ss.update("movie.updateyearprice");
	}
	//���� ������Ʈ
	
	
	public String outputmovierecommend(int member_number) {
        return ss.selectOne("outputmovierecommend.select", member_number);
    }
	
	public int user_number(String id) {
		return ss.selectOne("outputmovierecommend.userid",id);
	}
	
}
