package kr.or.kosta.mvc.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.kosta.dto.MovieVO;
import kr.or.kosta.dto.SearchVO;

@Repository
public class MovieDao {
	@Autowired
	private SqlSessionTemplate ss;

	//������ �Ǳǰ���
	public int getMoviePurchaseCount_pastMonth() {
		try {
		return ss.selectOne("movie.moviepurchase_pastmonth");
		}catch(Exception e) {
			return 0;
		}
	}

	public int getMoviePurchaseCount_thisMonth() {
		try {
		return ss.selectOne("movie.moviepurchase_thismonth");
		}catch(Exception e) {
			return 0;
		}
		
	}
	//�ǱǱ��ž�
	public int getMovieCopyRightCost_pastMonth() {
		try {
		return ss.selectOne("movie.moviecopyrightcost_pastmonth");
		}catch(Exception e) {
			return 0;
		}
	}
	public int getMovieCopyRightCost_thisMonth() {
		try {
		return ss.selectOne("movie.moviecopyrightcost_thismonth");
		}catch(Exception e) {
			return 0;
		}
	}
	//�Ǹŷ�
	public int getMovieInvestment_pastMonth() {
		try {
		return ss.selectOne("movie.movieinvestment_pastmonth");
		}catch(Exception e) {
			return 0;
		}
	}
	public int getMovieInvestment_thisMonth() {
		try {
		return ss.selectOne("movie.movieinvestment_thismonth");
		}catch(Exception e) {
			return 0;
		}
	}
	public List<MovieVO> moviesearch(SearchVO sevo) {
		return ss.selectList("mymovie.search",sevo);
	}
}
