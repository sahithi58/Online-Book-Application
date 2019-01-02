package com.sapestore.vo;

import java.util.List;

public class BookDetailsNComments {
	private BookVO bookDetails;
	private int ratings;
	private List<BookRatingCommentsVO> bookComments;
	/*private double averageRating;*/
	
	public BookVO getBookDetails() {
		return bookDetails;
	}
	public void setBookDetails(BookVO bookDetails) {
		this.bookDetails = bookDetails;
	}
	public List<BookRatingCommentsVO> getBookComments() {
		return bookComments;
	}
	public void setBookComments(List<BookRatingCommentsVO> bookComments) {
		this.bookComments = bookComments;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	/*public double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}*/
	
}
