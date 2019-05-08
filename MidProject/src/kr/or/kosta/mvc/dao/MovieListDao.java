package kr.or.kosta.mvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.kosta.dto.List_tableVO;
import kr.or.kosta.dto.MovieVO;
import kr.or.kosta.dto.MyListsVO;


@Repository
public class MovieListDao {
	
	@Autowired
	private SqlSessionTemplate ss;
	
	//���������� ���� �ϴܿ� �� �����ϵ� ����� �� ��� ks
	public List<MyListsVO> getMyMovieList(String userId){
		
		return ss.selectList("mymovielist.getMovieList",userId);
	}
	
	//�� ������ �̸��鸸 ���ö� ���ks
	public List<MyListsVO> getMvListNames(String userId){
		
		return ss.selectList("mymovielist.getMvListName",userId);
	}
	 
	
	//�� ������ �߰��Ҷ� ���ks
	public void insertMyList(Map<String,String> map) {
		
		ss.insert("mymovielist.addMovieList",map);
	}
	//�� ������-> ��ȭ �˻��Ҷ� ���ks
	public List<MovieVO> getSearchMvList(String keyword){
		return ss.selectList("mymovielist.getSearchMvList",keyword);
	}
	
	//�� �����Ͽ��� �˻��� ��ȭ���� �÷��̸���Ʈ�� ����ks
	public void instMvPlayList(Map<String, String> map) {
		
		String[] temp = map.get("mvNumList").split(",");
		for(String a : temp) {
			map.put("movieNum", a);
		
		ss.insert("mymovielist.insertMvPlayList",map);
		}
	}
	
	//�÷��̸���Ʈ�� ����� ���� ��ϵ� �ҷ��ö� ���ks
	public List<List_tableVO> getPlaylistMv(String mylists_number){
		return ss.selectList("mymovielist.getPlayListMv", mylists_number);
	}
	
	
	//�÷��̸���Ʈ ����� �κ�ks
	public void delPlayList(Map<String,String> map) {
		//�÷��̸���Ʈ�� �����Ϸ��� ������ �÷��̸���Ʈ�� ����� ������� �� ������ ���Ŀ� �����ؾ�
		//�ϴµ� Ʈ���Ÿ� ����� ���¶� �÷��̸���Ʈ�� �����ϸ� ����� ����鵵 ���� �� �����ȴ�.
		
		ss.delete("mymovielist.delPlayList",map);
	}
		
	//�÷��̸���Ʈ�� ����� ���� ����� �κ�ks
	public void delMvPlayList(Map<String,String> map) {
		
		ss.delete("mymovielist.delMvPlayList",map);
	}
	
	

}
