package com.sapestore.service;

import java.util.List;


import com.sapestore.vo.BookRatingCommentsVO;

public interface ReviewService {

	Integer addReview(BookRatingCommentsVO bookRatingComments);
	public  List<BookRatingCommentsVO> getReviewsbyid(int id);
}