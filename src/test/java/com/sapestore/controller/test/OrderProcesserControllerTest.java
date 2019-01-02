package com.sapestore.controller.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



public class OrderProcesserControllerTest {
	 private MockMvc mockMvc;

	    @Autowired
	    private WebApplicationContext wac;
	
	    @Test
	    public void testDisplayShippingAddress() {
	           try {
	                     System.out.println("entered testDisplayShippingAddress test");
	                     mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	                     mockMvc.perform(get("/myorder/displayShippingAddressForm"))
	                     .andExpect(status().isOk());
	                     System.out.println("exited testDisplayShippingAddress test");
	              } catch (Exception e) {
	                     e.printStackTrace();
	              }             
	    }

	@Test
	public void testSaveShippingAddressDetails() {
		 try {
             System.out.println("entered testSaveShippingAddress test");
             mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
             mockMvc.perform(get("/myorder/saveShippingAddress"))
             .andExpect(status().isOk());
             System.out.println("exited testSaveShippingAddress test");
      } catch (Exception e) {
             e.printStackTrace();
      }             
}
	

	@Test
	public void testSendOrderConfirmation() {
		try {
            System.out.println("entered testSendOrderConfirmation test");
            mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
            mockMvc.perform(get("/myorder/sendOrderSummaryMail"))
            .andExpect(status().isOk());
            System.out.println("exited testSendOrderConfirmation test");
     } catch (Exception e) {
            e.printStackTrace();
     }             
	}

}
