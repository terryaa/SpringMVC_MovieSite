package kr.or.kosta.mvc.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kosta.mvc.dao.MemberDao;
import kr.or.kosta.mvc.dao.ComplaintDao;

@RestController
@Controller
public class ComplaintController {

	@Autowired
	MemberDao memberDao;
	@Autowired
	ComplaintDao complaintDao;
	
	@GetMapping("/memberCheck")
	public HashMap<String,String> memberCheck(String cmd) {
		String msg=memberDao.checkMember(cmd);
		HashMap<String,String> map=new HashMap<>();
		
		if(msg==null)
			msg="fail";
		else
			msg="success";
		map.put("msg",msg);
		return map;
	}
	@GetMapping("/complaintsRegister")
	public HashMap<String,String> complaintRegister(String cmd,String admin,String complaint) {

		String msg=null;
		msg=complaintDao.insertComplaint(Integer.parseInt(cmd),
				Integer.parseInt(admin),Integer.parseInt(complaint));
		HashMap<String,String> map=new HashMap<>();
		map.put("msg",msg);
			
		return map;
	}
}
