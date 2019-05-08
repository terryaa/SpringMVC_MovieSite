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
		int mymemnum = rdao.selectmynum(mvo);//�ڽ��� ȸ����ȣ �޾ƿ�
		int mygroup = rdao.group(mvo);//�ڽ��� ������ȣ �޾ƿ�
		int myindex = 0;//���� �ε����� ã������ ����
		int minindex = 0;
		double distence =0;
		DistanceVO dvo = new DistanceVO();
		mvo.setMember_community_number(mygroup);
		List<MyMemberVO> memlist = rdao.member(mvo);//������ ����� ����Ʈ�� �޾ƿ�
		List<DistanceVO> dlist;
		List<DistanceVO> setList = new ArrayList<>();
		for(MyMemberVO mem : memlist){
			if(mem.getMember_number() == mymemnum){
				break;
			}else{
				myindex++;//�ε���ã��
			} 
		}
		try {
			dlist = poi.Distance();
			for(MyMemberVO mem : memlist){
				for(int i=0;i<memlist.size();i++){
					if(dlist.get(i).getID() == (mem.getMember_id())){
						for(int j=0; j<dlist.size();j++) {
						likelist.add(dlist.get(i).getList().get(j));//����ũ����Ʈ�� ���� ������ ��������� ��� �ִ´�
						}
					}	
				}
				dvo.setList(likelist);
				setList.add(dvo);
			 distence = poi.distence(setList,myindex);
			 distencelist.add(distence);
			}
			double minval = Collections.min(distencelist);//����Ʈ���� ���� �������� ��� �� ���ڿ� ������ ���� �ε����� �����´�.
			for(int i=0; i<distencelist.size();i++){
				if(minval == distencelist.get(i)){
					minindex = i;
				}
			}
			//�ε����� �����ͼ� memlist�� �ε����� ���� ���̵� �����´�
			String ID = dlist.get(minindex).getID();
			mvo.setMember_id(ID);
			list.add(rdao.getgsb(mvo));//�ش���̵��� ������ ����Ʈ�� ������
			list.add(rdao.getlikeList(mvo));//�ش� ���̵��� ���ƿ丮��Ʈ�� 2�� ������
			//���̵� ���� ���̵� ������ ������ ����Ʈ�� ��õ��ȭ ���� 2���� �����´�.
		} catch (IOException e) {
			e.printStackTrace();
		}//�Ÿ��� ���ϱ� ���� ������ ����� ȸ���� �������� ������
		
		return list;
	}
}
