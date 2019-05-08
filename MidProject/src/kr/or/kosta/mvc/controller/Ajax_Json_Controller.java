package kr.or.kosta.mvc.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.or.kosta.dto.GM_Movie_tagVO;
import kr.or.kosta.mvc.dao.AlgorithmDao;


@RestController
@RequestMapping("/rest")
public class Ajax_Json_Controller {
	

	@Autowired
	AlgorithmDao adao;
	
	
	@RequestMapping("/getdata")
	public List<List<Integer>> getdata(String tags){
		System.out.println(tags);
		StringTokenizer stz = new StringTokenizer(tags,",");
		List<String> tag_list = new ArrayList<String>();
		while(stz.hasMoreTokens()) {
			tag_list.add(stz.nextToken().trim());
		}
		System.out.println(tag_list);
		
		List<List<Set<Integer>>> T_list = test(tag_list);
		
		List<List<Integer>> list = price_list(T_list);
		
		return list;
	}
	
	public List<List<Set<Integer>>> test(List<String> list) {
		// ��ȭ�� �ش�Ǵ� �±׵� ���� map
		// movie_tags_map = key : ��ȭ��ȣ value : �±׹�ȣs
		Map<String, List<String>> movie_tags_map = new HashMap<String, List<String>>();

		// ���� ������ (�±׵�)
//		List<String> list = new ArrayList<String>();
//		list.add("C0000004"); // ���/�θǽ�
//		list.add("C0000048"); // �ڹ̵�
//		list.add("10028667"); // �ں���
		int tags_size = list.size();

		// Movie_Tag ���̺��� ���� �±׵��� ������ ��ȭ��ȣ, �±׹�ȣ�� ����Ʈ�� DB��ȸ
		List<GM_Movie_tagVO> mtvo_in_tag_list = adao.getMovie_number_InTags(list);

		// ���� DB �����͸� ���� row�� �и�
		for (GM_Movie_tagVO vo : mtvo_in_tag_list) {
			// map�� Ű �� �߿� �ش� ��ȭ��ȣ�� ������
			if (movie_tags_map.containsKey(vo.getMovie_number())) {
				// value�� ����Ʈ�� ������ �±׸� add(�߰�) �Ѵ�.
				movie_tags_map.get(vo.getMovie_number()).add(vo.getTag_Table_num());
				// map�� Ű �� �߿� �ش� ��ȭ��ȣ�� ���ٸ�
			} else {
				// tag_list�� ����� �ش� �±׸� add(�߰�) �Ѵ�.
				List<String> tag_list = new ArrayList<String>();
				tag_list.add(vo.getTag_Table_num());
				movie_tags_map.put(vo.getMovie_number(), tag_list);
			}
		}

		// tag_matching_list = [1����ġ�ϴ� ��ȭ(list),2����ġ�ϴ� ��ȭ(list) ,
		// ...,x�� ��ġ�ϴ� ��ȭ(list)] => index = 0 ~ x-1
		List<List<String>> tag_matching_list = new ArrayList<List<String>>();

		// movie_tags_map = key : ��ȭ��ȣ value : �±׹�ȣs
		//
		for (int i = 1; i <= tags_size; i++) {
			// ���� ������ �±װ� ��ġ�ϴ� ��ȭ��ȣ ����Ʈ
			List<String> m_number_list = new ArrayList<String>();
			// �ش� map�� Ű���� Set���� �޾ƿ´�.
			Set<String> keys = movie_tags_map.keySet();
			// Set�� Ű���� ���� iterator
			Iterator<String> it = keys.iterator();
			// ���� Ű���� �ִٸ�...
			while (it.hasNext()) {
				// Ű���� String ������ ���� | ��ȭ��ȣ
				String m_number = it.next();
				// Ű���� ���� �±׵��� ����ִ� list�� �޾ƿ´�.
				List<String> tags = movie_tags_map.get(m_number);
				// ���� �±׿� �ش� ��ȭ �±׵��� ��ġ�ϴ� ������ (1, 2, 3, ..., x)
				if (tags.size() == i) {
					// �ش� ����Ʈ�� ��ȭ ��ȣ �߰�
					m_number_list.add(m_number);

				}
			}
			
			tag_matching_list.add(m_number_list);
		}
		// ����
//		System.out.println(tot_list.get(0).size());
//		System.out.println(tot_list.get(1).size());
//		System.out.println(tot_list.get(2).size());
		
		
		// pay�� �迭�� ��Ƶ�
		int[] pay = { 5, 4, 3, 2 };
		
		// ****************			 ����  (��ġ ���� * �ݾ�)����Ʈ 			****************
		List<List<Set<Integer>>> T_list = new ArrayList<List<Set<Integer>>>();
		
		
		for (int i = tags_size - 1; i >= 0; i--) {
			// ���� ��ġ�ϴ� list => ... => 1�� ��ġ�ϴ� list
			List<String> m_number_list = tag_matching_list.get(i);
			// �ߺ� ���Ÿ� ���� Set
			Set<String> movie_num_set = new HashSet<String>();
			// list�� ��ȭ��ȣ �ߺ��� Set�� �־� �ߺ� ����
			for (String m_number : m_number_list) {
				movie_num_set.add(m_number);
			}
			
			// Set�� ���� ������ iterator | ��ȭ��ȣ
			Iterator<String> it = movie_num_set.iterator();
			
			// �Ʒ� �ݾ׺� ����Ʈ�� �ѹ��� ������ �߰� ����Ʈ
			List<Set<Integer>> M_list = new ArrayList<Set<Integer>>();
			
			// �ش� ��ȭ�� 10000���� ������ ȸ��Set , set���� ���� ���� : �ߺ��� ����
			Set<Integer> list_10000 = new HashSet<Integer>();
			Set<Integer> list_5000 = new HashSet<Integer>();
			Set<Integer> list_2500 = new HashSet<Integer>();
			Set<Integer> list_1000 = new HashSet<Integer>();
			// movie_num_set���� ��ȭ��ȣ�� �ִٸ�
			while (it.hasNext()) {
				// ��ȭ��ȣ�� ������ ������ ����
				String movie_number = it.next();
				// �ش� ��ȭ�� �󸶿� �����ߴ��� ��ȸ
				for (Integer p : pay) {
					// ���� ���� ��ȸ�� ���� map
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("movie_number", movie_number);
					map.put("movie_price", p);
					
					// A��ȭ�� a���ݿ� ������ �����  | ȸ����ȣ ����Ʈ
					List<String> member_number_list = adao.getMember_of_sold_log(map);
					
					// ���� | ��ȭ��ȣ�� 4���� ��� => 10000��, 5000��, 2500��, 1000��
//					System.out.println(movie_number);
//					System.out.println(member_number_list);
					
					// 10000������ �����ߴٸ�
					if (p == 5) {
						for (String m_n : member_number_list) {
							// �ش� ����Ʈ�� �߰�
							list_10000.add(Integer.parseInt(m_n));
						}
					// 5000������ �����ߴٸ�
					} else if (p == 4) {
						for (String m_n : member_number_list) {
							// 10000�� ����Ʈ�� ���Ե��� x => �߰�
							if (!list_10000.contains(m_n)) {
								list_5000.add(Integer.parseInt(m_n));
							}
						}
					// 2500������ �����ߴٸ�
					} else if (p == 3) {
						for (String m_n : member_number_list) {
							// 10000�� ����Ʈ�� ���Ե��� x and 5000�� ����Ʈ�� ���Ե��� x => �߰�
							if (!list_10000.contains(m_n) && !list_5000.contains(m_n)) {
								list_2500.add(Integer.parseInt(m_n));
							}
						}
					// 1000������ �����ߴٸ�
					} else if (p == 2) {
						for (String m_n : member_number_list) {
							// 10000�� ����Ʈ�� ���Ե��� x and 5000�� ����Ʈ�� ���Ե��� x and 2500�� ����Ʈ�� ���Ե��� x => �߰�
							if (!list_10000.contains(m_n) && !list_5000.contains(m_n) && !list_2500.contains(m_n)) {
								list_1000.add(Integer.parseInt(m_n));
							}
						}
					}
				}
				// �ѹ��� �����ϱ� ���� �߰� ����Ʈ�� �߰�
				M_list.add(list_10000);
				M_list.add(list_5000);
				M_list.add(list_2500);
				M_list.add(list_1000);
			}
			// ���� ����Ʈ�� �߰� | ���� ��ġ�ϴ� ����Ʈ ���� �߰��� : ������ for����  tags_size - 1 �� ����  0 ���� �ݺ�
			T_list.add(M_list);
		}
		
		return T_list;
	}
	
	
	public List<List<Integer>> price_list(List<List<Set<Integer>>> T_list){
		
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		
		int t_size = T_list.size();
		for(int i=0; i<t_size; i++) {
			List<Integer> list_inner = new ArrayList<Integer>();
			for(int j=0; j<4; j++) {
				
				int p=0;
				if(j==0) {
					p = 10000;
				}else if(j==1) {
					p = 5000;
				}else if(j==2) {
					p = 2500;
				}else if(j==3) {
					p = 1000;
				}
				BigDecimal a = new BigDecimal(T_list.get(i).get(j).size() * p);
				BigDecimal b = new BigDecimal((t_size-i));
				BigDecimal c = new BigDecimal(t_size);
				BigDecimal price = a.multiply(b).divide(c,0,BigDecimal.ROUND_FLOOR).setScale(-3,BigDecimal.ROUND_DOWN);
				list_inner.add(price.intValue());
			}
			list.add(list_inner);
		}
		
		return list;
	}
	}

	
	
