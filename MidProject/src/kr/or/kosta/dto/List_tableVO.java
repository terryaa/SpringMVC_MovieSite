package kr.or.kosta.dto;

public class List_tableVO { 
	//listNo - ����Ʈ�� �ε���, memNo - ȸ���� �ε���. ȸ������ �������� ���� ���, movieNo - ��ȭ�� �ε���. ��ȭ���� �������� ���� ���, 
	//mListNo ��ȣ - ���Ǹ���Ʈ ������ �������� ���� ���
	
	private int movie_number,mylists_number,list_category_number;
	 
	
	private MyMemberVO memvo;	
	private MovieVO movievo;     							
	private MyListsVO mylistsvo;
	private List_CategoryVO lc_vo;
	
	public int getMovie_number() {
		return movie_number;
	}
	public void setMovie_number(int movie_number) {
		this.movie_number = movie_number;
	}
	public int getMylists_number() {
		return mylists_number;
	}
	public void setMylists_number(int mylists_number) {
		this.mylists_number = mylists_number;
	}
	public int getList_category_number() {
		return list_category_number;
	}
	public void setList_category_number(int list_category_number) {
		this.list_category_number = list_category_number;
	}
	public MyMemberVO getMemvo() {
		return memvo;
	}
	public void setMemvo(MyMemberVO memvo) {
		this.memvo = memvo;
	}
	public MovieVO getMovievo() {
		return movievo;
	}
	public void setMovievo(MovieVO movievo) {
		this.movievo = movievo;
	}
	public MyListsVO getMylistsvo() {
		return mylistsvo;
	}
	public void setMylistsvo(MyListsVO mylistsvo) {
		this.mylistsvo = mylistsvo;
	}
	public List_CategoryVO getLc_vo() {
		return lc_vo;
	}
	public void setLc_vo(List_CategoryVO lc_vo) {
		this.lc_vo = lc_vo;
	}   						

	
	
}
