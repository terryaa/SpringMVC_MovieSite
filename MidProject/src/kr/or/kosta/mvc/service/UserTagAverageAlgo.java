package kr.or.kosta.mvc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import kr.or.kosta.dto.TagVO;
import kr.or.kosta.dto.UserTagWeightedList;
import kr.or.kosta.mvc.dao.RecommendDao;

public class UserTagAverageAlgo {

	//태그 카테고리별 가중치 합
	private HashMap<String,Double> tag_category_weighted_sum;
	private HashMap<String, TagVO> tag_weightedsum_map;
	private List<TagVO> genre_list;
	//회원수 카운트
	private int member_number_count=0;
	private double rank_div_mylist_moviecount_sum;
	private int actor_count=0,director_count=0;
	

	// 맵에 장르의 태그테이블넘버를 key로 정하고, value에 tagvo형태의 장르정보를 집어넘.
	
	public HashMap<String, Double> getTag_category_weighted_sum() {
		return tag_category_weighted_sum;
	}
	public void setTag_category_weighted_sum(HashMap<String, Double> tag_category_weighted_sum) {
		this.tag_category_weighted_sum = tag_category_weighted_sum;
	}
	public HashMap<String, TagVO> getTag_weightedsum_map() {
		return tag_weightedsum_map;
	}
	public void setTag_weightedsum_map(HashMap<String, TagVO> tag_weightedsum_map) {
		this.tag_weightedsum_map = tag_weightedsum_map;
	}
	public void saveGenreList(List<TagVO> list) {
		for (TagVO item : list) {
			tag_weightedsum_map.put(item.getTag_table_num(), item);
		}
		genre_list=list;
	}
	public void initialize(List<TagVO> genre_list) {
		tag_category_weighted_sum=new HashMap<String, Double>();
		tag_weightedsum_map= new HashMap<>();
		
		tag_category_weighted_sum.put("1", 0.0);
		tag_category_weighted_sum.put("2", 0.0);
		tag_category_weighted_sum.put("3", 0.0);
		tag_category_weighted_sum.put("4", 0.0);
		// 모든 장르의 태그넘버,카테고리넘버, 태그이름을 가져옴 객체생성하고 초기화시해저ㅜ야함.
		saveGenreList(genre_list);

	}
	public UserTagAverageAlgo() {
		//태그 카테고리별 가중치 합
		
	}
	
	public void accumulateWeightedSum(UserTagWeightedList utwl) {
		List<TagVO> list=utwl.getList();
		for (TagVO item : list) {
			int item_category_num=item.getTag_category_num();
			String item_tag_table_num=item.getTag_table_num();
			//장르는 이미 tagvo들이 존재함으로 존재하는지 검사를 하지않고
			//sum_div_total을 가져와 현재 item의 sum_div_total을 더한 값을 다시 저장한다.
			if (item_category_num == 1) {
				TagVO genre_sum = tag_weightedsum_map.get(item_tag_table_num);
				genre_sum.setSum_div_total(genre_sum.getSum_div_total() + item.getSum_div_total());
				genre_sum.setCount(genre_sum.getCount() + 1);
				tag_weightedsum_map.replace(item_tag_table_num, genre_sum);
			}else  {
				//배우,감독,댓글은 있는지 검사를하고 있으면 추가를, 없으면 덧셈을한다. 
				if(!tag_weightedsum_map.containsKey(item_tag_table_num)) {
					item.setCount(item.getCount());
					tag_weightedsum_map.put(item_tag_table_num, item);
				}else {
					TagVO tag_sum = tag_weightedsum_map.get(item_tag_table_num);
					tag_sum.setSum_div_total(tag_sum.getSum_div_total() + item.getSum_div_total());
					tag_sum.setCount(tag_sum.getCount() + 1);
					tag_weightedsum_map.replace(item_tag_table_num, tag_sum);
				}
				if(item_category_num==2)
					actor_count++;
				else if(item_category_num==3)
					director_count++;
					
			}
			//카테고리별 가중치 합
			tag_category_weighted_sum.replace(String.valueOf(item_category_num),
			tag_category_weighted_sum.get(String.valueOf(item_category_num))+item.getSum_div_total());
		}
		rank_div_mylist_moviecount_sum+=utwl.getRank_div_mylist_moviecount();
		member_number_count++;
	}
	//태그별 카테고리 번호를 입력받아 해당 태그의 최고 가중치값을가지고잇는 5개의 태그를 불러옴. 
	public List<TagVO> getTopFiveTag(int category_num){
		List<TagVO> top_five_list=new ArrayList<>();
		for(int i=0;i<5;i++) {
			Double max=0.0;
			String maxKey="";
			for(Entry<String, TagVO> entry: tag_weightedsum_map.entrySet()) {
				if(max<entry.getValue().getSum_div_total()
						&& entry.getValue().getTag_category_num()==category_num) {
					max=entry.getValue().getSum_div_total();
					maxKey=entry.getKey();
				}
			}
			top_five_list.add(tag_weightedsum_map.get(maxKey));
			tag_weightedsum_map.remove(maxKey);
		}
		for( TagVO item : top_five_list) {
			tag_weightedsum_map.put(item.getTag_table_num(), item);
		}
		return top_five_list;
		
	}
	// 모든 태그의 가중치 / 태그숫자  로 평균값을구함. 
	public void calculateTagAverage() {
		for( TagVO item: tag_weightedsum_map.values()) {
			item.setWeighted_sum_average(item.getSum_div_total() / item.getCount());
		}
	}
	public List<TagVO> getTagSortedAverageList(){
		List<TagVO> sorted_tag_weightedsum_list=new ArrayList<>();
		for(TagVO item : genre_list) {
			sorted_tag_weightedsum_list.add(tag_weightedsum_map.get(item.getTag_table_num()));
		}
		sorted_tag_weightedsum_list.addAll(getTopFiveTag(2));
		sorted_tag_weightedsum_list.addAll(getTopFiveTag(3));
		return sorted_tag_weightedsum_list;
	}
	public double getrank_div_mylist_moviecount_average() {
		return rank_div_mylist_moviecount_sum/member_number_count;
	}
	public double getCategoryCountDivTotal(int category) {
		if(category==2)
			return (double) actor_count/member_number_count;
		else
			return (double) director_count/member_number_count;
	}

}
