package kr.co.bteam.mvc.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bteam.mvc.vo.OrdersVO;
import kr.co.bteam.mvc.vo.ReviewsVO;

@Repository("review")
public class ReviewDao implements ReviewDaoInter{

	@Autowired
	private SqlSessionTemplate ss;
	
	
	@Override
	public void addReview(ReviewsVO vo) {
		ss.insert("cmr_review.add", vo);
	}
	@Override
	public int chkReviews(ReviewsVO vo) {
		
		return ss.selectOne("cmr_review.chkreview",vo);
	}
	@Override
	public List<ReviewsVO> getReview(int i_no) {
		List<ReviewsVO> vo = ss.selectList("cmr_review.reviewlist",i_no);
		return vo;
	}
	@Override
	public int chkStatus(OrdersVO vo) {
		return ss.selectOne("cmr_review.chkstatus",vo);
	}
	
	
	
	
//	@Override
//	public int chkReviews(ReviewsVO vo) {
//		
//		return ss.selectOne("cmr_review.chkreview",vo);
//	}
	
	//구매목록 -> 구매한 상품의 리뷰작성폼
	

	

}
