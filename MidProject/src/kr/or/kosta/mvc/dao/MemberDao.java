package kr.or.kosta.mvc.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.kosta.dto.Login_LogVO;
import kr.or.kosta.dto.MyMemberVO;


@Repository
public class MemberDao {
	@Autowired
	private SqlSessionTemplate ss;
	
	public void insertmember(MyMemberVO vo) {
		ss.insert("member.join",vo);
	}

	public int IDchk(String id) {	
		return ss.selectOne("member.idChk",id);
	}

	public MyMemberVO loginCheck(MyMemberVO vo) {		
		return ss.selectOne("member.loginchk",vo);
	}

	public void llogging(MyMemberVO vo) {
		Login_LogVO llvo = new Login_LogVO();
		int num = ss.selectOne("member.memnum",vo);
		llvo.setMember_number(num);
		ss.insert("loginlog.login",llvo);
	}

	public void logoutlogging(String uid) {//���ǿ� �ִ� uid���� �޾ƿͼ�
		Login_LogVO llvo = new Login_LogVO();
		MyMemberVO mvo = new MyMemberVO();
		mvo.setMember_id(uid);//mvo�� �������ְ�
		System.out.println("dlrj"+mvo.getMember_id());
		int memnum =ss.selectOne("member.memnum",mvo);//db�� �ִ� �����ȣ�� ȣ���ؿͼ�
		llvo.setMember_number(memnum);//llvo�� �����ѵ�
		int num = ss.selectOne("loginlog.che",llvo);//�׸� ���� ���� �ֱ��� �α��� ����� �α��� ��ȣ�� �����ͼ�
		llvo.setLoginlog_number(num);
		ss.update("loginlog.logout",llvo);//�α��� ��ȣ�� ������Ʈ
	}

	public int gradechk(MyMemberVO vo) {
		System.out.println(vo.getMember_id());
		return ss.selectOne("member.gradechk",vo);
	}

	public List<Integer> getMemberNumberAll() {
		// TODO Auto-generated method stub
		return ss.selectList("member.membernumberall");
	}
	
	public String checkMember(String member_number) {
		String msg=null;
		try{
			msg=ss.selectOne("member.checkmember",member_number);
			}catch(NullPointerException e) {
				msg=null;
			}
		return msg;
		
		
	}
	
	//�������� > Ż��� ��� ks
		public void outUserAccount(String userId) {
			ss.delete("member.outUserAccount",userId);
	}

}
