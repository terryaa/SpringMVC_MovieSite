package kr.or.kosta.dto;

//�ش� VO�� db�� �ִ� ���̺��� vo�� �ƴϰ�, ����������-> �� ���ݾ� ��հ� ���� �� ���� ���ϰ� �������� ���� ������. map���� ���Ϲ����ϱ� ����� ����� �ȵǼ� �ϴ� ����
public class userChartViewVO {
	public String sold_date;
	public int average;
	
	public String getSold_date() {
		return sold_date;
	}
	public void setSold_date(String sold_date) {
		this.sold_date = sold_date;
	}
	public int getAverage() {
		return average;
	}
	public void setAverage(int average) {
		this.average = average;
	}
	
	
}
