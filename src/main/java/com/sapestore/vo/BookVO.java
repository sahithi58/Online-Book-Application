package com.sapestore.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 * Bean class for adding a book.
 *
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */
public class BookVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3972545417811168092L;

	@NotEmpty
	private String isbn;

	@NotEmpty
	private String publisherName;

	@NotEmpty
	private String categoryId;

	private String categoryName;

	@NotEmpty
	private String bookTitle;

	@NotNull
	private int quantity;

	@NotEmpty
	private String bookAuthor;

	@NotEmpty
	private String bookPrice;

	@NotEmpty
	private String bookFullImage;

	@NotEmpty
	private String bookShortDesc;

	private String bookDetailDesc;

	private String active;

	private MultipartFile thumbImage;

	private MultipartFile fullImage;

	private String thumbPath;

	private String fullPath;

	private Integer rentedQuantity;
	

	public Integer getRentedQuantity() {
		return rentedQuantity;
	}

	public void setRentedQuantity(Integer rentedQuantity) {
		this.rentedQuantity = rentedQuantity;
	}

	@NotEmpty
	private String rentAvailable;

	private String rentPrice;

	private String oldIsbn;

	private BigDecimal averageRating;

	private Integer numberOfComments;

	private String isRented;
	

	public String getIsRented() {
		return isRented;
	}

	public void setIsRented(String isRented) {
		this.isRented = isRented;
	}

	private Integer cartQuantity;

	public Integer getCartQuantity() {
		return cartQuantity;
	}

	public void setCartQuantity(Integer cartQuantity) {
		this.cartQuantity = cartQuantity;
	}

	

	public Integer getNumberOfComments() {
		return numberOfComments;
	}

	public void setNumberOfComments(Integer numberOfComments) {
		this.numberOfComments = numberOfComments;
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn
	 *            the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the publisherName
	 */
	public String getPublisherName() {
		return publisherName;
	}

	/**
	 * @param publisherName
	 *            the publisherName to set
	 */
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the bookTitle
	 */
	public String getBookTitle() {
		return bookTitle;
	}

	/**
	 * @param bookTitle
	 *            the bookTitle to set
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the bookAuthor
	 */
	public String getBookAuthor() {
		return bookAuthor;
	}

	/**
	 * @param bookAuthor
	 *            the bookAuthor to set
	 */
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	/**
	 * @return the bookPrice
	 */
	public String getBookPrice() {
		return bookPrice;
	}

	/**
	 * @param bookPrice
	 *            the bookPrice to set
	 */
	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}

	/**
	 * @return the bookShortDesc
	 */
	public String getBookShortDesc() {
		return bookShortDesc;
	}

	/**
	 * @param bookShortDesc
	 *            the bookShortDesc to set
	 */
	public void setBookShortDesc(String bookShortDesc) {
		this.bookShortDesc = bookShortDesc;
	}

	/**
	 * @return the bookDetailDesc
	 */
	public String getBookDetailDesc() {
		return bookDetailDesc;
	}

	/**
	 * @param bookDetailDesc
	 *            the bookDetailDesc to set
	 */
	public void setBookDetailDesc(String bookDetailDesc) {
		this.bookDetailDesc = bookDetailDesc;
	}

	/**
	 * @return the active
	 */
	public String getActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}

	public String getBookFullImage() {
		return bookFullImage;
	}

	public void setBookFullImage(String bookFullImage) {
		this.bookFullImage = bookFullImage;
	}

	public MultipartFile getThumbImage() {
		return thumbImage;
	}

	public void setThumbImage(MultipartFile thumbImage) {
		this.thumbImage = thumbImage;
	}

	public MultipartFile getFullImage() {
		return fullImage;
	}

	public void setFullImage(MultipartFile fullImage) {
		this.fullImage = fullImage;
	}

	/**
	 * @return the thumbPath
	 */
	public String getThumbPath() {
		return thumbPath;
	}

	/**
	 * @param thumbPath
	 *            the thumbPath to set
	 */
	public void setThumbPath(String thumbPath) {
		this.thumbPath = thumbPath;
	}

	/**
	 * @return the fullPath
	 */
	public String getFullPath() {
		return fullPath;
	}

	/**
	 * @param fullPath
	 *            the fullPath to set
	 */
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	/**
	 * @return the rentAvailable
	 */
	public String getRentAvailable() {
		return rentAvailable;
	}

	/**
	 * @param rentAvailable
	 *            the rentAvailable to set
	 */
	public void setRentAvailable(String rentAvailable) {
		this.rentAvailable = rentAvailable;
	}

	/**
	 * @return the rentPrice
	 */
	public String getRentPrice() {
		return rentPrice;
	}

	/**
	 * @param rentPrice
	 *            the rentPrice to set
	 */
	public void setRentPrice(String rentPrice) {
		this.rentPrice = rentPrice;
	}

	/**
	 * @return the oldIsbn
	 */
	public String getOldIsbn() {
		return oldIsbn;
	}

	/**
	 * @param oldIsbn
	 *            the oldIsbn to set
	 */
	public void setOldIsbn(String oldIsbn) {
		this.oldIsbn = oldIsbn;
	}

	public BigDecimal getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(BigDecimal averageRating) {
		this.averageRating = averageRating;
	}


	

	@Override
	public String toString() {
		return "BookVO [ quantity=" + quantity + ", isRented=" + isRented
				+ ", cartQuantity=" + cartQuantity + "bookTitle=" + bookTitle +" ]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookVO other = (BookVO) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}

	

}
