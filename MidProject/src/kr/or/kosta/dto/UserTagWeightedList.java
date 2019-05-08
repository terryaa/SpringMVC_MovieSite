package kr.or.kosta.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/*
 * 유저의 MyList들 속에 있는 영화들이 가지고잇는 태그들과 태그들의 가중치를 계산한 것을 저장할 클래스
 * */
public class UserTagWeightedList {
		int member_number;
		int movie_count,movie_inrank_count;
		double rank_div_mylist_moviecount;
		List<TagVO> list=new ArrayList<>(20);
		List<Double> averge_distance_list;
		
		
		
		public double getRank_div_mylist_moviecount() {
			return rank_div_mylist_moviecount;
		}
		public void setRank_div_mylist_moviecount(double rank_div_mylist_moviecount) {
			this.rank_div_mylist_moviecount = rank_div_mylist_moviecount;
		}
		public List<Double> getAverge_distance_list() {
			return averge_distance_list;
		}
		public void setAverge_distance_list(List<Double> averge_distance_list) {
			this.averge_distance_list = averge_distance_list;
		}
		public int getMovie_count() {
			return movie_count;
		}
		public void setMovie_count(int movie_count) {
			this.movie_count = movie_count;
		}

		public int getMember_number() {
			return member_number;
		}
		public void setMember_number(int member_number) {
			this.member_number = member_number;
		}
		public List<TagVO> getList() {
			return list;
		}
		public void setList(List<TagVO> list) {
			this.list = list;
		}
		public void setGenreList(List<TagVO> genre_list) {
			for(TagVO item: genre_list) {
				if(!list.contains(item)) {
					list.add(genre_list.indexOf(item),item);
				}
			}
//			for(int i=0;i<21;i++) {
//				if(! list.get(i).getTag_table_num().equals(genre_list.get(i)) ) {
//					list.add(i,genre_list.get(i));
//				}
//			}
		}
		
		public List<TagVO> getTopFiveTag(int category_num,List<TagVO> list){
			List<TagVO> top_five_list=new ArrayList<>(5);
			List<TagVO> copy_list=list;
			for(int i=0;i<5;i++) {
				Double max=0.0;
				TagVO temp_max=new TagVO();
				for(TagVO item : copy_list) {
					if(max<item.getSum_div_total()
							&& item.getTag_category_num()==category_num) {
						max=item.getSum_div_total();
						temp_max=item;
					}
				}
				top_five_list.add(i,temp_max);
				copy_list.remove(temp_max);
			}
			return top_five_list;
			
		}
		public List<TagVO> getTagSortedList(){
			List<TagVO> return_list=new ArrayList<>();
			for(int i=0;i<21;i++) {
				return_list.add(list.get(i));
			}
			return_list.addAll(getTopFiveTag(2, list));
			return_list.addAll(getTopFiveTag(3, list));
			
			return return_list;
		}
		public int getCategoryCount(int category) {
			int count=0;
			for(TagVO item : list) {
				if(category==item.getTag_category_num())
					count+=item.getCount();
			}
			return count;
		}
		
	
}
