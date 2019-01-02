package com.sapestore.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.hibernate.entity.BookRatingComments;
import com.sapestore.vo.BookRatingCommentsVO;

@Repository
public class ReviewDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;



	private final static SapeStoreLogger logger = SapeStoreLogger.getLogger(ReviewDao.class.getName());
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	

	public Integer addReview(BookRatingCommentsVO bookRatingCommentsvo) {
		BookRatingComments bookRatingComments=new BookRatingComments();
		bookRatingComments.setBookRating(bookRatingCommentsvo.getBookRating());
		bookRatingComments.setBookComments(bookRatingCommentsvo.getBookComments());
		bookRatingComments.setUserId(bookRatingCommentsvo.getUserId());
		bookRatingComments.setIsbn(bookRatingCommentsvo.getIsbn());
		logger.info("Adding review into Book_Rating_Comments Table");
		Date commentDate = new Date();
		System.out.println(sdf.format(commentDate));
		bookRatingComments.setBookCommentDate(commentDate);
		Integer id = (Integer) hibernateTemplate.save(bookRatingComments);
		System.out.println("ID " + id);
		return id;

	}

	public List<BookRatingCommentsVO> getReviewsbyid(int id) {
		logger.info("Getting review from Book_Rating_Comments Table");
		List<BookRatingCommentsVO> brc1 = new ArrayList();
		List<BookRatingComments> brc = (List<BookRatingComments>) hibernateTemplate.findByNamedQueryAndNamedParam("BookRatingComments.findByCommentId", "commentId", id);
		for (BookRatingComments bc : brc) {
			BookRatingCommentsVO bookratcom = new BookRatingCommentsVO();
			bookratcom.setBookCommentDate(bc.getBookCommentDate());
			bookratcom.setBookComments(bc.getBookComments());
			bookratcom.setBookRating(bc.getBookRating());
			bookratcom.setIsbn(bc.getIsbn());
			bookratcom.setUserId(bc.getUserId());
			bookratcom.setCommentId(bc.getCommentId());
			brc1.add(bookratcom);
		}
		return brc1;
	}

}