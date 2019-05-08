package kr.or.kosta.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kosta.dto.DistanceVO;
import kr.or.kosta.dto.MyMemberVO;
import kr.or.kosta.mvc.dao.RecommendDao;
import kr.or.kosta.mvc.service.PoiTest;

@RestController
@RequestMapping("/rating")
public class Rating_DistanceController {
	@Autowired
	private  RecommendDao rdao;
	
	@RequestMapping("/user")
	public List<List<String>> likelist(String uid){
		List<List<String>> list = new ArrayList<>();
		MyMemberVO mvo = new MyMemberVO();
		PoiTest poi = new PoiTest();
		List<Double> likelist = new ArrayList<>();
		List<Double> distencelist = new ArrayList<>();
		mvo.setMember_id(uid);
		int mymemnum = rdao.selectmynum(mvo);//자신의 회원번호 받아옴
		int mygroup = rdao.group(mvo);//자신의 군집번호 받아옴
		int myindex = 0;//나의 인덱스를 찾기위한 변수
		int minindex = 0;
		double distence =0;
		DistanceVO dvo = new DistanceVO();
		mvo.setMember_community_number(mygroup);
		List<MyMemberVO> memlist = rdao.member(mvo);//군집의 멤버를 리스트로 받아옴
		List<DistanceVO> dlist;
		List<DistanceVO> setList = new ArrayList<>();
		for(MyMemberVO mem : memlist){
			if(mem.getMember_number() == mymemnum){
				break;
			}else{
				myindex++;//인덱스찾기
			} 
		}
		try {
			dlist = poi.Distance();
			for(MyMemberVO mem : memlist){
				for(int i=0;i<memlist.size();i++){
					if(dlist.get(i).getID() == (mem.getMember_id())){
						for(int j=0; j<dlist.size();j++) {
						likelist.add(dlist.get(i).getList().get(j));//라이크리스트에 같은 군집의 멤버정보를 모두 넣는다
						}
					}	
				}
				dvo.setList(likelist);
				setList.add(dvo);
			 distence = poi.distence(setList,myindex);
			 distencelist.add(distence);
			}
			double minval = Collections.min(distencelist);//리스트에서 가장 작은값을 출력 이 인자와 동일한 값의 인덱스를 가져온다.
			for(int i=0; i<distencelist.size();i++){
				if(minval == distencelist.get(i)){
					minindex = i;
				}
			}
			//인덱스를 가져와서 memlist에 인덱스를 대입 아이디를 가져온다
			String ID = dlist.get(minindex).getID();
			mvo.setMember_id(ID);
			list.add(rdao.getgsb(mvo));//해당아이디의 금은동 리스트를 가져옴
			list.add(rdao.getlikeList(mvo));//해당 아이디의 좋아요리스트를 2개 가져옴
			//아이디를 통해 아이디가 설정한 금은동 리스트와 추천영화 상위 2개를 가져온다.
		} catch (IOException e) {
			e.printStackTrace();
		}//거리를 구하기 위해 엑셀에 저장된 회원의 정보들을 가져옴
		
		return list;
	}
}
