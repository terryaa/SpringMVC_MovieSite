package kr.or.kosta.mvc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import kr.or.kosta.dto.TagVO;
import kr.or.kosta.dto.UserTagWeightedList;

public class UserTagAverageDistanceCal {
	
	public List<UserTagWeightedList> getUserTagWeigthedList_List(List<UserTagWeightedList> list,UserTagAverageAlgo utaa) {
		List<UserTagWeightedList> return_list=new ArrayList<>();
		for(UserTagWeightedList item : list) {
			item.setAverge_distance_list(calUserTagAverageDistance(item, utaa));
			return_list.add(item);
		}
		return return_list;
	}
	public List<Double> calUserTagAverageDistance(UserTagWeightedList utwl,UserTagAverageAlgo utaa) {
		//전체영화랭크카운트아직안함
		utaa.calculateTagAverage();
		List<TagVO> utaa_tag_sorted_list=utaa.getTagSortedAverageList();
		List<TagVO> utwl_tag_sorted_list=utwl.getTagSortedList();
		List<Double> average_distance_list=new ArrayList<>(34);
		
		for(TagVO item: utwl_tag_sorted_list) {
			int index=utwl_tag_sorted_list.indexOf(item);
			double temp1=utaa_tag_sorted_list.get(index).getWeighted_sum_average();
			double temp2=utwl_tag_sorted_list.get(index).getSum_div_total();
			int utaa_tag_count=utaa_tag_sorted_list.get(index).getCount();
			int utwl_tag_count=utwl_tag_sorted_list.get(index).getCount();
			System.out.println(temp1);
			System.out.println(temp2);
			System.out.println(utaa_tag_count);
			System.out.println(utwl_tag_count);
			
			average_distance_list.
			add(index,utaa_tag_sorted_list.get(index).getWeighted_sum_average()-utwl_tag_sorted_list.get(index).getSum_div_total());
		}
//		for(int i=0;i<31;i++) {
//			average_distance_list.add(utaa_tag_sorted_list.get(i).getWeighted_sum_average()-utwl_tag_sorted_list.get(i).getSum_div_total());
//		}
		average_distance_list.add(31,utaa.getrank_div_mylist_moviecount_average()-utwl.getRank_div_mylist_moviecount());
		average_distance_list.add(32,utaa.getCategoryCountDivTotal(2)-utwl.getCategoryCount(2));
		average_distance_list.add(33,utaa.getCategoryCountDivTotal(3)-utwl.getCategoryCount(3));
		return average_distance_list;
	}

}
