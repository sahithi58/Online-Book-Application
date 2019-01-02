package com.sapestore.partner.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SSPartnerWebService {
	
	private final static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(SSPartnerWebService.class.getName());
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@bangvmpllcai02.sapient.com:1521:caidb2";

	// Database credentials
	static final String USER = "BLR_SAPESTORE_BATCH_B";
	static final String PASS = "BLR_SAPESTORE_BATCH_B";

	public List<SSPartnerBooksListBean> getBookList(String catId) {
		List<SSPartnerBooksListBean> finalList = new ArrayList<SSPartnerBooksListBean>();
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement.....");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM SAPESTORE_BOOKS WHERE CATEGORY_ID=" + catId + " AND IS_FROM_PARTNER_STORE='Y'";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				
				SSPartnerBooksListBean transDO = new SSPartnerBooksListBean();
				transDO.setIsbn(rs.getString("ISBN"));
				transDO.setPublisherName(rs.getString("PUBLISHER_NAME"));
				transDO.setCategoryIdpr(rs.getInt("CATEGORY_ID"));
				transDO.setBookTitle(rs.getString("BOOK_TITLE"));
				transDO.setQuantity(rs.getInt("QUANTITY"));
				transDO.setBookAuthor(rs.getString("BOOK_AUTHOR"));
				transDO.setThumbImageUrl(rs.getString("BOOK_THUMB_IMAGE"));
				transDO.setFullImageUrl(rs.getString("BOOK_FULL_IMAGE"));
				transDO.setBookPrice(rs.getInt("BOOK_PRICE"));
				transDO.setBookShortDesc(rs.getString("BOOK_SHORT_DESCRIPTION"));
				transDO.setBookDetailDesc(rs.getString("BOOK_DETAIL_DESCRIPTION"));
				transDO.setActive(rs.getString("IS_ACTIVE"));
				finalList.add(transDO);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			LOGGER.debug("SQLException in partner services is "+se);
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			LOGGER.debug("SQLException in partner services is "+e);
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		return finalList;
	}// end main here
	
	public List<SSPartnerBooksListBean> getSearchBookList(String title,String author,
			String isbn, String publisher, String catId) {
		List<SSPartnerBooksListBean> finalList = new ArrayList<SSPartnerBooksListBean>();
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement.....");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM SAPESTORE_BOOKS WHERE BOOK_TITLE like '%" + title 
					+ "%' AND BOOK_AUTHOR like '%" + author 
					+ "%' AND ISBN like '%" + isbn 
					+ "%' AND PUBLISHER_NAME like '%" + publisher 
					+ "%' AND CATEGORY_ID=" + catId + 
					"IS_FROM_PARTNER_STORE='Y'";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				
				SSPartnerBooksListBean transDO = new SSPartnerBooksListBean();
				transDO.setIsbn(rs.getString("ISBN"));
				transDO.setPublisherName(rs.getString("PUBLISHER_NAME"));
				transDO.setCategoryIdpr(rs.getInt("CATEGORY_ID"));
				transDO.setBookTitle(rs.getString("BOOK_TITLE"));
				transDO.setQuantity(rs.getInt("QUANTITY"));
				transDO.setBookAuthor(rs.getString("BOOK_AUTHOR"));
				transDO.setThumbImageUrl(rs.getString("BOOK_THUMB_IMAGE"));
				transDO.setFullImageUrl(rs.getString("BOOK_FULL_IMAGE"));
				transDO.setBookPrice(rs.getInt("BOOK_PRICE"));
				transDO.setBookShortDesc(rs.getString("BOOK_SHORT_DESCRIPTION"));
				transDO.setBookDetailDesc(rs.getString("BOOK_DETAIL_DESCRIPTION"));
				transDO.setActive(rs.getString("IS_ACTIVE"));
				finalList.add(transDO);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			LOGGER.debug("SQLException in partner services is "+se);
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			LOGGER.debug("SQLException in partner services is "+e);
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		return finalList;
	}// end main here
	
}
