package com.sapestore.vo;

import java.util.Date;



public class BookRatingCommentsVO {
	
    private Integer commentId;	
	private String isbn;	
	private String userId;
	private String bookComments;
	private Date bookCommentDate;	
	private Integer bookRating;
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBookComments() {
		return bookComments;
	}
	public void setBookComments(String bookComments) {
		this.bookComments = bookComments;
	}
	public Date getBookCommentDate() {
		return bookCommentDate;
	}
	public void setBookCommentDate(Date bookCommentDate) {
		this.bookCommentDate = bookCommentDate;
	}
	public Integer getBookRating() {
		return bookRating;
	}
	public void setBookRating(Integer bookRating) {
		this.bookRating = bookRating;
	}
	@Override
	public String toString() {
		return "BookRatingCommentsVO [commentId=" + commentId + ", isbn=" + isbn + ", userId=" + userId
				+ ", bookComments=" + bookComments + ", bookCommentDate=" + bookCommentDate + ", bookRating="
				+ bookRating + "]";
	}
	
	
	
}
