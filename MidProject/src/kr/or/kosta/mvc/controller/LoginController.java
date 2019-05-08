package kr.or.kosta.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.kosta.dto.Login_LogVO;
import kr.or.kosta.dto.MyMemberVO;
import kr.or.kosta.mvc.dao.MemberDao;
//�α��θ��� ���� ��Ʈ�ѷ�
@Controller
public class LoginController {
	@Autowired
	private MemberDao memdao;
	
	@PostMapping("/u_loginProcess")
	public ModelAndView loginProcess(HttpSession session, HttpServletRequest request,MyMemberVO vo) {
		//�����ͺ��̽��� �����ϴ� ���̵� ��й�ȣ�� �Է°��� ������ �� �������� �����̸�  session�� �������ش�.
		System.out.println("id: "+vo.getMember_id());
		System.out.println("pwd: "+vo.getMember_password());
		vo.setMember_grade(memdao.gradechk(vo));
		System.out.println(vo.getMember_grade());
		MyMemberVO map = memdao.loginCheck(vo);
		ModelAndView mav = new ModelAndView();
		if(map == null) {
			mav.setViewName("u_loginerror");
		}else {
				session.setAttribute("uname", map.getMember_name());
				session.setAttribute("uid", vo.getMember_id());
				session.setAttribute("grade", vo.getMember_grade());
				mav.setViewName("redirect:./");
		}
		return mav;
	}
	
	@GetMapping("/u_logout")
	public ModelAndView logoutProcess(HttpSession session, HttpServletRequest request) {
		session.removeAttribute("uname");
		session.removeAttribute("uid");
		session.removeAttribute("grade");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
	}//���ǿ� �ö��ִ� �α����� ȸ���� ������ �����Ѵ�.
	
	@GetMapping("/u_idcheck")
	public String idcheck(Model m,String member_id) {
		System.out.println(member_id);
		int cnt= memdao.IDchk(member_id);
		System.out.println(cnt);
		m.addAttribute("cnt", cnt);
		return "u_idcheck";
	}//ȸ�����Խÿ� �Է��� ���̵� DB�� �ִ��� Ȯ���� �� ������ 1�� ��ȯ�ϰ�, ������ 0�� ��ȯ�Ͽ� �ߺ��� üũ�ϴ� �޼����̴�. 
	@PostMapping("/u_memberjoin")
	public String myMemberJoin(Model m, MyMemberVO vo) {
		memdao.insertmember(vo);
		return "redirect:u_joinsuccess";
	}//ȸ���� ������ �����ͺ��̽��� �����ϴ� �޼���
}
