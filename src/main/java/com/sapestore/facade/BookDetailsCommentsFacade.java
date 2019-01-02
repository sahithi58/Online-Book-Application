package com.sapestore.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.service.BookDetailsService;
import com.sapestore.vo.BookDetailsNComments;
import com.sapestore.vo.BookRatingCommentsVO;
import com.sapestore.vo.BookVO;
@Service
public class BookDetailsCommentsFacade {
@Autowired
private BookDetailsService bookDetailsService;

public BookDetailsService getBookDetailsService() {
	return bookDetailsService;
}

public void setBookDetailsService(BookDetailsService bookDetailsService) {
	this.bookDetailsService = bookDetailsService;
}

public BookDetailsNComments getBookDetailsComments(String isbn){
	BookVO bookDetails=null;
	List<BookRatingCommentsVO> bookComments=null;
	int ratings;
	
	bookDetails= bookDetailsService.getBookDetails(isbn);
	bookComments=bookDetailsService.getBookComments(isbn);
	ratings=bookDetailsService.getTotalRatings(isbn);
	/*averageRating=bookDetailsService.findAverageRating(isbn);*/
	BookDetailsNComments bookDetailsNComments=new BookDetailsNComments();
	bookDetailsNComments.setBookComments(bookComments);
	bookDetailsNComments.setBookDetails(bookDetails);
	bookDetailsNComments.setRatings(ratings);
	/*bookDetailsNComments.setAverageRating(averageRating);*/
	return bookDetailsNComments;
}
}
