package com.sapestore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.dao.TransactionHistoryDao;
import com.sapestore.service.TransactionHistoryService;
import com.sapestore.vo.TransactionHistoryVO;

@Transactional
@Service("transactionService")
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

	@Autowired
	private TransactionHistoryDao transactionHistoryDao;

	@Override
	public List<TransactionHistoryVO> getTransactionHistory(String userId) throws Exception {
		List<TransactionHistoryVO> transactionList = null;
		transactionList = transactionHistoryDao.getTransactionHistory(userId);
		return transactionList;
	}

}
