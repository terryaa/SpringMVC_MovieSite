package kr.or.kosta.mvc.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.or.kosta.dto.MovieVO;
import kr.or.kosta.dto.UserTagWeightedList;
import kr.or.kosta.dto.TagVO;
import kr.or.kosta.mvc.dao.MemberDao;
import kr.or.kosta.mvc.dao.MovieDao;
import kr.or.kosta.mvc.dao.RecommendDao;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
@Service
public class RecommendLogicService {

	@Autowired
	RecommendDao recommendDao;
	@Autowired
	MemberDao memberDao;

	public String getUserTagWeightedList() {


		List<Integer> member_numbers=memberDao.getMemberNumberAll();
		List<TagVO> genre_list=recommendDao.getGenreTagList();
		List<UserTagWeightedList> utwl_list=new ArrayList<>();

		//�±׺� ����ġ�� �����Ͽ� ����� ���ϴµ� ���̴� Ŭ������ü.
		UserTagAverageAlgo utaa=new UserTagAverageAlgo();
		utaa.initialize(genre_list);
		
		
				
				//���� ��� ����� ������ �ڵ�
				//******���� �ð��ϰɸ�!!********
////		for(String member_number: member_numbers) {
////		int member_number_int=Integer.parseInt(member_number);
		for(Integer member_number: member_numbers) {
			int member_number_int=member_number;
			System.out.println(member_number_int);
			//�±׺� ����ġ ��հ� ������ ����ġ���� ���ϱ����� ����Ʈ�� ������ �±׺� ����ġ������ ����
			UserTagWeightedList utwl = new UserTagWeightedList();
			utwl.setRank_div_mylist_moviecount(recommendDao.getRankDivMyListMovieCount(member_number_int));
			// ��� ����� ���� ����Ʈ ���ϴ� �ڵ� �߰��ؾ���.
			// �� ������ �������ִ� ���̸���Ʈ���� ��� ��ȭ������ �±׵�� �� �±׵��� ����ġ�� �ҷ���
			utwl.setMember_number(member_number_int);
			utwl = recommendDao.getMyList(utwl);
			//21���� �帣�� ������� ����. 
			utwl.setGenreList(genre_list);
			//20%�ȿ����� ��ȭ���� mylist���ִ� ��ȭ ��  / ��ü mylist���� ��ȭ��

			utwl_list.add(utwl);
			//������ ����ġ �˰��� ���� (��ô)
			utaa.accumulateWeightedSum(utwl);
		}
		
				
				
				
		//�±׺� ����ġ ��հ� ������ ����ġ���� ���ϱ����� ����Ʈ�� ������ �±׺� ����ġ������ ����
//		UserTagWeightedList utwl = new UserTagWeightedList();
//
//		//20%�ȿ� ��� ��ȭ�� /mylist���ִ� ��ü��ȭ���� �ҷ���
//		utwl.setRank_div_mylist_moviecount(recommendDao.getRankDivMyListMovieCount(11));
//		// �� ������ �������ִ� ���̸���Ʈ���� ��� ��ȭ������ �±׵�� �� �±׵��� ����ġ�� �ҷ���
//		utwl.setMember_number(11);
//		utwl = recommendDao.getMyList(utwl);
//		//21���� �帣�� ������� ����. 
//		utwl.setGenreList(genre_list);
//		//20%�ȿ����� ��ȭ���� mylist���ִ� ��ȭ ��  / ��ü mylist���� ��ȭ��
//
//		utwl_list.add(utwl);
//		//������ ����ġ �˰��� ���� (��ô)
//		utaa.accumulateWeightedSum(utwl);
//		
//		
//		
//		
//		utwl=new UserTagWeightedList();
//		utwl.setMember_number(12);
//		utwl = recommendDao.getMyList(utwl);
//		//������ �±� ����Ʈ�� �帣�� ������ �߰�. 
//		utwl.setGenreList(genre_list);
//		//20%�ȿ����� ��ȭ���� mylist���ִ� ��ȭ ��  / ��ü mylist���� ��ȭ��
//		utwl.setRank_div_mylist_moviecount(recommendDao.getRankDivMyListMovieCount(12));
//		//�������� ����Ʈ ����
//		utwl_list.add(utwl);
//		//������ ����ġ �˰��� ���� (��ô)
//		utaa.accumulateWeightedSum(utwl);
		
		UserTagAverageDistanceCal utadc=new UserTagAverageDistanceCal();
		
		//�������� ��ü �±� ��հ� �ڱⰡ �������ִ� �±׸���Ʈ�� �±׺� ���� ����Ͽ� �ٽ� ����Ʈ�� ����.
		utwl_list=utadc.getUserTagWeigthedList_List(utwl_list, utaa);
		
		
		// ���� ������ ���-���� ����ġ �����
		// ������ List<double> ���·� �����Եȴ�. 
		// List<double> : 0~20 - �帣 ����ġ ����
		//                21~25 - ��� ����ġ ����
		//                26~30 - ���� ����ġ ����
		//                31 - ���߼�( ���ȸ���� 20%���ǿ�ȭ����/mylist��ü���� �� ���� ��� ���ڷ� ������  - �� ȸ���� 20%���ǿ�ȭ����/mylist��ü����) ����
		//				  32 - ��� ī��Ʈ ����  ( ��� �±� ��ü ��/ ��ü �����  - �� ȸ���� ��� �±� ��ü��)
		//                33 - ���� ī��Ʈ ����  ( ���� �±� ��ü ��/ ��ü �����  - �� ȸ���� ���� �±� ��ü��)
		System.out.println(utwl_list.get(0).getAverge_distance_list());
		System.out.println(utwl_list.get(1).getAverge_distance_list());
		
		
		//��� ������ �ݺ��ϵ��� �ڵ� �����ؾ���. ������ �Ѹ����� �ڵ�.
		//�±װ� ����ִ� ����Ʈ�� iterate�ϸ� �±׺� ����ġ �ջ�� ī�װ��� ����ġ �ջ��� �����Ѵ�.
		

		/*
		 * utwl�� ����ִ� �����ȣ, ����� ���� ��� ����Ʈ������ �±׿� �±��� ����ġ�� ������ ����Ǿ�����. ��������!!!
		 */

//		List<UserTagWeightedList> user_list = null;
//
		String path = "C:\\bigdataStudy\\SpringProject\\SpringProject\\MidProject\\WebContent\\resources\\data\\";
		
		StringBuffer svp = new StringBuffer();
		svp.append(path).append("utwl_list").append(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).append(".csv");
		PrintWriter pw = null;

		try {

			pw = new PrintWriter(new File(svp.toString()));
			StringBuffer sb = new StringBuffer();

			int size = utwl_list.size();
				for (int i = 0; i < size; i++) {
					System.out.println("������ȣ"+utwl_list.get(i).getMember_number());
					sb.append(utwl_list.get(i).getMember_number()).append(",");
					System.out.println("������"+utwl_list.get(i).getAverge_distance_list().size());
					int size_list = utwl_list.get(i).getAverge_distance_list().size();
					for (int j = 0; j <size_list; j++) {
						System.out.println("��"+j+utwl_list.get(i).getAverge_distance_list().get(j));
						sb.append(utwl_list.get(i).getAverge_distance_list().get(j)).append(",");
					}
					sb.append("\n");
				}
				
				pw.write(sb.toString());
				pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("������");

		return "success";
	}
	
	
}
