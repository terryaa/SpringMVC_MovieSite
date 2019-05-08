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

	//�±� ī�װ��� ����ġ ��
	private HashMap<String,Double> tag_category_weighted_sum;
	private HashMap<String, TagVO> tag_weightedsum_map;
	private List<TagVO> genre_list;
	//ȸ���� ī��Ʈ
	private int member_number_count=0;
	private double rank_div_mylist_moviecount_sum;
	private int actor_count=0,director_count=0;
	

	// �ʿ� �帣�� �±����̺�ѹ��� key�� ���ϰ�, value�� tagvo������ �帣������ �����.
	
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
		// ��� �帣�� �±׳ѹ�,ī�װ��ѹ�, �±��̸��� ������ ��ü�����ϰ� �ʱ�ȭ�������̾���.
		saveGenreList(genre_list);

	}
	public UserTagAverageAlgo() {
		//�±� ī�װ��� ����ġ ��
		
	}
	
	public void accumulateWeightedSum(UserTagWeightedList utwl) {
		List<TagVO> list=utwl.getList();
		for (TagVO item : list) {
			int item_category_num=item.getTag_category_num();
			String item_tag_table_num=item.getTag_table_num();
			//�帣�� �̹� tagvo���� ���������� �����ϴ��� �˻縦 �����ʰ�
			//sum_div_total�� ������ ���� item�� sum_div_total�� ���� ���� �ٽ� �����Ѵ�.
			if (item_category_num == 1) {
				TagVO genre_sum = tag_weightedsum_map.get(item_tag_table_num);
				genre_sum.setSum_div_total(genre_sum.getSum_div_total() + item.getSum_div_total());
				genre_sum.setCount(genre_sum.getCount() + 1);
				tag_weightedsum_map.replace(item_tag_table_num, genre_sum);
			}else  {
				//���,����,����� �ִ��� �˻縦�ϰ� ������ �߰���, ������ �������Ѵ�. 
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
			//ī�װ��� ����ġ ��
			tag_category_weighted_sum.replace(String.valueOf(item_category_num),
			tag_category_weighted_sum.get(String.valueOf(item_category_num))+item.getSum_div_total());
		}
		rank_div_mylist_moviecount_sum+=utwl.getRank_div_mylist_moviecount();
		member_number_count++;
	}
	//�±׺� ī�װ� ��ȣ�� �Է¹޾� �ش� �±��� �ְ� ����ġ�����������մ� 5���� �±׸� �ҷ���. 
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
	// ��� �±��� ����ġ / �±׼���  �� ��հ�������. 
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
