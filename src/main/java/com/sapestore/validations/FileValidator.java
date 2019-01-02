package com.sapestore.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sapestore.vo.BookVO;

public class FileValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {


		  BookVO addBooks = (BookVO) arg0;

		  if (addBooks.getThumbImage().getSize()==0) {
			  arg1.rejectValue("thumbImage", "addBooks.thumbImage",
		     "Please provide book thumbnail image.");
		  }
		  
		  if (addBooks.getFullImage().getSize()==0) {
			  arg1.rejectValue("fullImage", "addBooks.fullImage",
		     "Please provide book detail image.");
		  }
		  
		  if(addBooks.getRentAvailable()!=null && addBooks.getRentAvailable().equalsIgnoreCase("Y") && (addBooks.getRentPrice()==null || (addBooks.getRentPrice()!=null && addBooks.getRentPrice().trim().isEmpty())))
		  {
			  arg1.rejectValue("rentPrice", "addBooks.rentPrice",
		     "Please provide book rent price.");
		  }
		 
		
	}

}
