package com.nl.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nl.beans.APIResponse;
import com.nl.beans.StockInputDTO;
import com.nl.exception.ValidationException;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
public interface StockService {

	public abstract APIResponse getAllStocks();

	public abstract APIResponse getStockById(Long id);

	public abstract APIResponse createStock(StockInputDTO stock) throws ValidationException;

	public abstract APIResponse updateStock(Long id, StockInputDTO stock) throws ValidationException;

	public abstract APIResponse deleteStock(Long id);

}
