package com.sapestore.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.dao.BookDetailsDao;
import com.sapestore.service.BookDetailsService;
import com.sapestore.vo.BookRatingCommentsVO;
import com.sapestore.vo.BookVO;

@Transactional
@Service
public class BookDetailsServiceImpl implements BookDetailsService {
	
@Autowired
private BookDetailsDao bookDetailsDao;
	@Override
	public String getUserName(String userId) {
		
		return bookDetailsDao.getUserName(userId);
	}

	@Override
	public BookVO getBookDetails(String isbn) {
		
		return bookDetailsDao.getBookDetails(isbn);
	}

	@Override
	public List<BookRatingCommentsVO> getBookComments(String isbn) {
		
		return bookDetailsDao.getBookComments(isbn);
	}

	@Override
	public int getTotalRatings(String isbn) {
		
		return bookDetailsDao.getTotalRatings(isbn);
	}

	@Override
	public BigDecimal findAverageRating(String isbn) {
		
		return bookDetailsDao.findAverageRating(isbn);
	}



}
