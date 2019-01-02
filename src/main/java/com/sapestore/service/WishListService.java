package com.sapestore.service;

import java.util.List;

import com.sapestore.hibernate.entity.WishList;

public interface WishListService {
	
	 public boolean addWishList(WishList wishList);
	 public List wishlistbooks(String userId);
	 public void deleteBook(String isbn);
	 
}
