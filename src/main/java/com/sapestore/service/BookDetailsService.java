package com.sapestore.service;

import java.math.BigDecimal;
import java.util.List;

import com.sapestore.vo.BookRatingCommentsVO;
import com.sapestore.vo.BookVO;



public interface BookDetailsService {
	String getUserName(String userId);
	BookVO getBookDetails(String isbn);
	List<BookRatingCommentsVO> getBookComments(String isbn);
	int getTotalRatings(String isbn);
	BigDecimal findAverageRating(String isbn);
}
