package kr.co.bteam.mvc.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.bteam.mvc.dao.ReviewDao;
import kr.co.bteam.mvc.dao.ReviewDaoInter;
import kr.co.bteam.mvc.vo.ReviewsVO;
@Service
public class ReviewsService{
	@Autowired
	private ReviewDao reviewDao;

	public List<ReviewsVO> getReviews(int i_no) {
		return reviewDao.getReview(i_no);
	}
	


	
	
}
