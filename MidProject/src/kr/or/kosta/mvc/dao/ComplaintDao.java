package kr.or.kosta.mvc.dao;

import org.mybatis.spring.SqlSessionTemplate;
import kr.or.kosta.dto.MinwonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ComplaintDao {

	@Autowired
	private SqlSessionTemplate ss;
	
	public String insertComplaint(int member_number,int admin_number,int category_number) {
		MinwonVO minvo=new MinwonVO();
		minvo.setCounsel_category_number(category_number);
		minvo.setMember_number(member_number);
		minvo.setMinwon_admin_member(admin_number);
		ss.insert("complaint.insertcomplaint",minvo);
		return "success";
	}
}
