package kr.or.kosta.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.kosta.dto.MemberVO;

@Repository
public class Admin_MemberDao {

	@Autowired
	private SqlSessionTemplate ss;

	// ȸ�� ���� ����Ʈ ���
	public List<MemberVO> getMemberList(Map<String, String> map) {
		System.out.println(map);
		List<MemberVO> list = ss.selectList("admin_member.showMember", map);
		return list;
	}

	// ȸ�� �� ���� ���
	public MemberVO getMemberDetail(int member_number) {
		return ss.selectOne("admin_member.showMemberDetail", member_number);
	}

	// ȸ�� ���� ����
	public void updateMember(MemberVO vo) {
		ss.update("admin_member.updateMember", vo);
	}
}
