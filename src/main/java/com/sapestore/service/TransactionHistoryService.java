package com.sapestore.service;

import java.util.List;

import com.sapestore.vo.TransactionHistoryVO;

public interface TransactionHistoryService {

	public List<TransactionHistoryVO> getTransactionHistory(String userId) throws Exception;

}
