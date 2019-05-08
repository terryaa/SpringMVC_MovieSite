package kr.or.kosta.mvc.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.kosta.dto.MyListsVO;
import kr.or.kosta.dto.MyMemberVO;
import kr.or.kosta.dto.userChartViewVO;
import kr.or.kosta.mvc.dao.MemberDao;
import kr.or.kosta.mvc.dao.MovieListDao;
import kr.or.kosta.mvc.dao.UserInfoDao;
  
@Controller
public class MyPageController {
  
	@Autowired
	private MovieListDao mlDao;
	
	@Autowired
	private UserInfoDao uiDao;
	
	@Autowired
	private MemberDao memDao;
	
	private Map<String,String> map;
	
	// ���������� ����
	@GetMapping("/invoice")  
	public String moveUserPage(Model model) {
		
		String userId="oracle6@naver.com";//���� ���ǿ� ���̵� ����Ǹ� �����ؾ� �� �κ�.
		map = new HashMap<String,String>();
		
		map.put("m_id",userId);//���� ���ǿ� ���̵� ����Ǹ� �����ؾ� �� �κ�.
		map.put("keyword","day");//day ���� ������ ���� �׽�Ʈ
		
		model.addAttribute("ml_list", mlDao.getMyMovieList(userId));
		model.addAttribute("mvNamelist", mlDao.getMvListNames(userId));
		model.addAttribute("myRankInfo",uiDao.getMyRankInfo(userId));
		model.addAttribute("uAvgSoldList", uiDao.getAvgSoldList(map));
		 
		return "userMyPageMain";
	}
	
	//����������->���� ���ݾ� ��Ʈ ���� ������ ����ϴ� �κ�
	@GetMapping("/chartFileDownload")
	public String fileTest(Model m,String keyword) {
		map = new HashMap<String,String>();
		String path="C:\\Users\\KOSTA\\Downloads\\chartExcel.csv";
		PrintWriter pw = null;
		
		try {
				pw= new PrintWriter(new File(path));
				
				StringBuffer sb = new StringBuffer();
				String cNameList="sold_date,average";
				map.put("m_id","oracle6@naver.com");
				map.put("keyword",keyword);
				//����Ʈ
				sb.append(cNameList);
				sb.append("\n");
				
			
			    List<userChartViewVO> list = uiDao.getAvgSoldList(map);
			  
			    for(userChartViewVO sv : list ) { 
				  sb.append(sv.getSold_date()).append(",");
				  sb.append(sv.getAverage()); sb.append("\n"); 
			    }
			 
			
				pw.write(sb.toString());
				pw.close();
				
				return "success";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "500";
		}
	
	} 
		

	// ���������� ����->������ �߰��ϱ� ��ư ������ �����ϴ�, ������ �߰� �޼��� (insert)
	@GetMapping("/addPlayList")
	public String insertList(Model model,String listName) {
		Map<String, String> map = new HashMap<>();
		
		map.put("listName", listName);
		map.put("userId", "oracle6@naver.com");
		
		model.addAttribute("ml_list", mlDao.getMvListNames("oracle6@naver.com"));
		
		mlDao.insertMyList(map);
		
		return "userPlayListPage";

	} 
	
	//���������� ����-> ������ �߰��ϱ�� ������ ���� �񵿱������ �÷��̸���Ʈ �κи� ���ΰ�ħ�ϱ� ���� �޼���
	@GetMapping("/refresh")
	public String refreshPlayList(Model model) {
		
		model.addAttribute("mvNamelist", mlDao.getMvListNames("oracle6@naver.com"));
		
		return "refreshPlayList";
	}

	// ����������->�����Ͽ��� �׸� �������� �÷��̸���Ʈ �������� �̵� �޼���
	@GetMapping("/userListEditPage")
	public String moveUserListPage(Model model,String listName,String listNum) {

 
		model.addAttribute("listName", listName);
		model.addAttribute("listNum", listNum);
		model.addAttribute("playMvList", mlDao.getPlaylistMv(listNum));
		
		return "userPlayListPage";

	}    
	
	
	//���������� ����->������ Ŭ�� -> �÷��̸���Ʈ ������->��ȭ�߰�-> �˻� ���â���� �˻� ��ư ���� �� �����ϴ� �޼���
	@GetMapping("/searchMvCheck")
	public String searchMvCheck(Model model,String keyword) {
		
		List<MyListsVO> mvNamelist = mlDao.getMvListNames("oracle6@naver.com");
		 
		if(mvNamelist.size() != 0) {
			return "searchMvResultPage";
		} else {
			return "500"; //�� ������ ���������� ���
		}
		
	}
	
	//���������� ����->������ Ŭ�� -> �÷��̸���Ʈ ������->��ȭ�߰�-> �˻� ���â���� �˻� ��ư ���� �� �����ϴ� �޼���
	@GetMapping("/searchMvPrint")
	public String searchMovie(Model model,String keyword) {
		
		model.addAttribute("searchMvList", mlDao.getSearchMvList(keyword));
		
		return "searchMvResultPage";
	}
		
	
	//�÷��̸���Ʈ ����
	@GetMapping("/instMvPlayList")
	public String instMvInPlayList(Model model,String mvNumList,String playListNum) {
		Map<String,String> map = new HashMap<>();
		 
		map.put("mvNumList",mvNumList);
		map.put("playListNum",playListNum);
		map.put("listCategNum","2"); //�� �������� List_category ���̺��� 2�� ���� �������ְ�, ���̸���Ʈ���� �߰��ϹǷ� ���⼭ �̷��� �� �߰� 
		
		
		mlDao.instMvPlayList(map);
		
		return "userPlayListPage";
	}
	
	//�÷��̸���Ʈ ����
	@GetMapping("/delPlayList")
	public String delPlayList(Model model,String listNum) {
		Map<String,String> map = new HashMap<>();
		 
		map.put("listNum",listNum);
		
		mlDao.delPlayList(map);
		 
		return "userPlayListPage";
	}
	
	//�÷��̸���Ʈ�� ����� ���� ����
	@GetMapping("/delMvPlayList")
	public String delMvPlayList(Model model,String listNum,String movie_number) {
		Map<String,String> map = new HashMap<>();
		  
		map.put("listNum",listNum);
		map.put("movie_number",movie_number);
		
		mlDao.delMvPlayList(map);
		 
		
		return "userPlayListPage";
	}
	
	//�÷��̸���Ʈ�� �˻��� ���� ���� ���� �񵿱������ ���ΰ�ħ �ϱ� ���� �޼���
	@GetMapping("/refreshPlayList")
	public String refreshMvPlayList(Model model,String listNum) {
		
		model.addAttribute("playMvList", mlDao.getPlaylistMv(listNum));
		return "refreshPlayListMv";
	}

	 
	
	//����������->��õ��ȭ ���͸� ���� �������� �� �����Ҷ� ���Ǵ� �޼���. ���͸� �׸��� �ʱⰪ�� ���� ������ update �������� ������ ¥�°���
	//������ ���� ������ �־� �ϴ� delete�� �ѹ� �ؼ� ������ �ִ� ���� �����ϰ� ���� ���õ� ���� insert �ϴ� ������� �����ϵ��� ��
	//���ǿ� �������̵� ����Ǿ� �ִٴ� �����Ͽ� �ӽõ����ͷ� ����. ���� ����
	@GetMapping("/updateUserMvFileter") //delete,insert
	public String updateMvFilter(Model model,String filterList) {
		map.put("userId","oracle6@naver.com");   //�̺κ�. ���� �����ʿ�
		map.put("filterList",filterList);
		uiDao.instUserMvFilter(map);
		return "userMvFilterPage";
	}     
	 
	//����������->��õ��ȭ ���͸� ���� �������� ���� �� ������ ���� �Ǿ��ִ� ���� �����ϱ� ���� �޼��� 
	@ResponseBody
	@GetMapping("/userMvFilterData")
	public List<String> moveMvFilterPage() {
		String userId = "oracle6@naver.com"; //���� �� �߰�. �����ʿ�
		return uiDao.getUserFilterList(userId);
	}
	//����������->��õ��ȭ ���͸� ���� �������� ���� �� ���
	@GetMapping("/userMvFilterPage")
	public String moveMvFilterPage(Model model) {
		String userId = "oracle6@naver.com"; //���� �� �߰�. �����ʿ�
		model.addAttribute("filterList",uiDao.getUserFilterList(userId));
		return "userMvFilterPage";
	}
//	 
	
	//����������->�������� ���� �޼��� (update)
	@PostMapping("/updateInfo")
	public String userUpdate(MultipartFile mfile, Model m, HttpServletRequest request,MyMemberVO memvo) throws UnsupportedEncodingException {
		String img_path = "img//movie";
		String r_path = request.getRealPath("/");
		String oriFn = mfile.getOriginalFilename();

		StringBuffer path = new StringBuffer();
		
		path.append(r_path).append(img_path).append("\\");
		path.append(oriFn);
		
		File f = new File(path.toString());
		  
		//������ ����Ŭ�� ���ε��ϴ� ����� �ƴ϶� �ϴ� ���� �̸��� ����ǵ��� ��
		memvo.setMember_picture(oriFn);
		memvo.setMember_id("oracle6@naver.com"); //���߿� ���ǿ� ���̵����� ����
		System.out.println("---getMember_birth----"+memvo.getMember_birth());
		System.out.println("---getMember_password----"+memvo.getMember_password());
		
		// ���� ���ε� ���� 
		try {
			if(oriFn != null && oriFn != "") {
				mfile.transferTo(f);
			}
			uiDao.updateUserInfo(memvo);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}   //�������� ����Ǵ� ������������ �����ؾ���qq
		return "userMyPageMain"; 
	}   
	
	//���������� ����->���� ���� ��ư Ŭ���� ���� �Ǿ��ִ� ������ �����͸� �����ϱ� ���� ���� ���� �ҷ����� �κ�
	@GetMapping("/userInfoPage")
	public String moveUserInfoPage(Model model) {
		 
		String userId="oracle6@naver.com"; //�ӽ� ���� ���̵� ����κ�. ���� ���ǰ����� ����
		
		MyMemberVO mvo = uiDao.getMemberInfo(userId);
		model.addAttribute("userInfo",uiDao.getMemberInfo(userId));
		
		return "userInfoUpdate";
	}
	 
	//����������->���� ����->���� Ż��
	@GetMapping("/outUserAccount")
	public String outUserAccount() {
		 
		String userId="oracle6@naver.com"; // ���ǿ� ����� ���� ������ ���̵� ��� �׽�Ʈ�� �ӽð�
		memDao.outUserAccount(userId);
		return "index";
	} 
	
	


	
	

}
