package kr.or.kosta.dto;

public class Tag_ProfitVO {
	private int movie_count;
	private long investment_sum, investment_avg;
	private String tag_Table_Num, tag_Name;
	
	
	public long getInvestment_avg() {
		return investment_avg;
	}
	public void setInvestment_avg(long investment_avg) {
		this.investment_avg = investment_avg;
	}
	
	public String getTag_Table_Num() {
		return tag_Table_Num;
	}
	public void setTag_Table_Num(String tag_Table_Num) {
		this.tag_Table_Num = tag_Table_Num;
	}
	public String getTag_Name() {
		return tag_Name;
	}
	public void setTag_Name(String tag_Name) {
		this.tag_Name = tag_Name;
	}
	
	public int getMovie_count() {
		return movie_count;
	}
	public void setMovie_count(int movie_count) {
		this.movie_count = movie_count;
	}
	public long getInvestment_sum() {
		return investment_sum;
	}
	public void setInvestment_sum(long investment_sum) {
		this.investment_sum = investment_sum;
	}

}
