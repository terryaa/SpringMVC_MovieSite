package kr.or.kosta.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.or.kosta.dto.MemberVO;
import kr.or.kosta.mvc.dao.Admin_MemberDao;

@Controller
//@RequestMapping("/member")
public class Admin_MemberController {

	@Autowired
	private Admin_MemberDao admin_MemberDao;

	// ������ ȸ�� ���� ������
	@GetMapping("/admin_MemberInfo")
	public String showMemberTable() {
		return "admin_MemberInfo";
	}

	// ȸ�� ����Ʈ �˻� (���̵�, �̸����� �˻� ����)
	@PostMapping("/admin_SearchMember")
	public ModelAndView searchMemberTable(String searchType, String searchName) {
		ModelAndView mav = new ModelAndView("admin_SearchMember");
		Map<String, String> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("searchName", searchName);
		System.out.println(map);
		List<MemberVO> list = admin_MemberDao.getMemberList(map);
		mav.addObject("mlist", list);
		System.out.println(list);
		System.out.println("------------------------");
		return mav;
	}

	// �ش� ȸ�� ��ȣ�� ȸ�� �� ���� ������ ���
	@PostMapping("/admin_MemberDetail")
	public ModelAndView getMemberDetail(int number) {
		ModelAndView mav = new ModelAndView("admin_MemberDetail");
		MemberVO v = admin_MemberDao.getMemberDetail(number);
		System.out.println("ȸ�� ��ȣ : " + number);
		mav.addObject("member", v);
		
		System.out.println("���� : "+v.getMember_picture());
		System.out.println("�� ���� : " + v.getMovie_sold_log().get(0).getPrice().getMovie_real_price());
		System.out.println("�ֱ� �α��� �ð� : " + v.getLogin_log().get(0).getLoginlog_logout());
		System.out.println("�� �湮Ƚ�� : " + v.getLogin_log().get(0).getLoginlog_login());
		System.out.println("�� ��ûȽ�� : " + v.getWatch_log().get(0).getWatchlog_date());
		System.out.println("------------------------------");
		return mav;
	}

	// ȸ�� ���� ����
	@PostMapping("/admin_updateInfo")
	public ModelAndView updateMember(MemberVO vo, HttpServletRequest request, MultipartFile mfile) {
		ModelAndView mav = new ModelAndView("redirect:admin_MemberInfo");
		
		// �̹��� ���ε带 ���� path ����
		String img_path = "img";
		String r_path = request.getRealPath("/");
		String oriFn = mfile.getOriginalFilename();
		
		StringBuffer path = new StringBuffer();
		path.append(r_path).append(img_path).append("\\");
		path.append(oriFn);
		System.out.println(path+"\n");
		File f = new File(path.toString());
		
		// �̹��� ���ε�
		try {
			mfile.transferTo(f);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		vo.setMember_picture(oriFn);
		admin_MemberDao.updateMember(vo);

		System.out.println("ȸ�� ��ȣ : " + vo.getMember_number());
		System.out.println("���� : "+vo.getMember_picture());
		System.out.println("�̸� : " + vo.getMember_name());
		System.out.println("��й�ȣ : " + vo.getMember_password());
		System.out.println("���� : " + vo.getMember_birth());
		System.out.println("��� : " + vo.getMember_grade());
		System.out.println("----------------------------");
		return mav;
	}

}
