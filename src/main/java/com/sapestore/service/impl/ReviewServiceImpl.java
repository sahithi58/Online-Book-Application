package com.sapestore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.ReviewDao;
import com.sapestore.hibernate.entity.BookRatingComments;
import com.sapestore.service.ReviewService;
import com.sapestore.vo.BookRatingCommentsVO;


@Service("ReviewService")
@Transactional
public class ReviewServiceImpl implements ReviewService {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(ReviewServiceImpl.class.getName());
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Override
	public Integer addReview(BookRatingCommentsVO bookRatingComments) {
		
		System.out.println("adding reviews");
		return reviewDao.addReview(bookRatingComments);
	}
	public  List<BookRatingCommentsVO> getReviewsbyid(int id){
		System.out.println("getting reviews by id");
		return reviewDao.getReviewsbyid(id);
	}

}