package com.sapestore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.dao.WishListDao;
import com.sapestore.hibernate.entity.WishList;
import com.sapestore.service.WishListService;

@Service("wishListService")
public class WishListServiceImpl implements WishListService {

	@Autowired
	WishListDao wishlist;
	
	
	@Override
	public boolean addWishList(WishList wishList) {

		return wishlist.addWishListDao(wishList);
		
	}


	@Override
	public List wishlistbooks(String userId) {
		// TODO Auto-generated method stub
		return wishlist.wishlistbooks(userId);
	}


	@Override
	public void deleteBook(String isbn) {
		// TODO Auto-generated method stub
		wishlist.deleteBook(isbn);
	}

}
