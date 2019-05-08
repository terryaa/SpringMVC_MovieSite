package kr.or.kosta.mvc.inter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

//��� �α���DAO �������̽�
public interface LogDao {
	//�α��� ��ü�� ī��Ʈ
	public int getLogCount_All();
	//���� ���� �α��� �� ī��Ʈ
	public int getLogCount_CurrentDate();
	//���� ���� �α��� �� ī��Ʈ
	public int getLogCount_Yesterday();
	//�ֱ� 5�ϰ��� �α׼� �Ϻ� ī��Ʈ
	public List<HashMap<String, BigDecimal>> getLogCount_Days();
	//�ֱ� 5�ް��� �α׼� �Ϻ� ī��Ʈ
	public List<HashMap<String, BigDecimal>> getLogCount_Months();
	//�ֱ� 5�Ⱓ�� �α׼� �Ϻ� ī��Ʈ
	public List<HashMap<String, BigDecimal>> getLogCount_Years();
}
