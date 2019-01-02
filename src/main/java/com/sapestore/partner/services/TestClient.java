package com.sapestore.partner.services;

import java.util.List;

public class TestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SSPartnerWebService service  = new SSPartnerWebService();
		SSPartnerWebServicePortType client = service.getSSPartnerWebServiceHttpSoap11Endpoint();
		List<SSPartnerBooksListBean> testList = client.getBookList("1");
		
	}

}
