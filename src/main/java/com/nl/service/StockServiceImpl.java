package com.nl.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nl.beans.APIResponse;
import com.nl.beans.StockInputDTO;
import com.nl.beans.StockOutputDTO;
import com.nl.entity.Stock;
import com.nl.exception.ExceptionUtility;
import com.nl.exception.ValidationException;
import com.nl.repository.StockRepository;
import com.nl.util.Utility;
import com.nl.util.Validation;

@Component
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepository;

	@Override
	public APIResponse createStock(StockInputDTO stock) throws ValidationException {
		// TODO Auto-generated method stub
		// Validating input
		Validation.validateCreateSTockRequest(stock);

		// Check Same name stock is exist or not
		Stock s = stockRepository.findStockByName(stock.getStockName());
		if (s != null) {
			throw new ValidationException("stockNameExistCode", "stockNameExistDesc");
		}

		Stock entity = new Stock();
		BeanUtils.copyProperties(stock, entity);
		entity.setLastUpdatedTime(new Date());
		stockRepository.save(entity);
		StockOutputDTO stockDTO = new StockOutputDTO();
		BeanUtils.copyProperties(entity, stockDTO);
		stockDTO.setLastUpdatedTime(Utility.getStringFromDate(entity.getLastUpdatedTime(), true));
		stockDTO.setCreatedTime(Utility.getStringFromDate(entity.getCreatedTime(), true));

		APIResponse apiResponse = new APIResponse(ExceptionUtility.getCode("successCode"),
				ExceptionUtility.getCode("successMsg"));
		apiResponse.setStock(stockDTO);
		return apiResponse;
	}

	@Override
	public APIResponse updateStock(Long id, StockInputDTO stock) throws ValidationException {
		// TODO Auto-generated method stub
		// Validate input request
		Validation.validateUpdateStockRequest(stock);

		// Check Same name stock is exist or not
		Stock s = stockRepository.findStockByName(stock.getStockName());
		if (s != null && s.getId() != id) {
			throw new ValidationException("stockNameExistCode", "stockNameExistDesc");
		}

		Stock entity = stockRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Stock not available for Id :" + id));

		BeanUtils.copyProperties(stock, entity, Utility.getNullPropertyNames(stock));

		entity.setLastUpdatedTime(new Date());
		stockRepository.save(entity);
		StockOutputDTO stockDTO = new StockOutputDTO();
		BeanUtils.copyProperties(entity, stockDTO);
		stockDTO.setLastUpdatedTime(Utility.getStringFromDate(entity.getLastUpdatedTime(), true));
		stockDTO.setCreatedTime(Utility.getStringFromDate(entity.getCreatedTime(), true));
		APIResponse apiResponse = new APIResponse(ExceptionUtility.getCode("successCode"),
				ExceptionUtility.getCode("successMsg"));
		apiResponse.setStock(stockDTO);

		return apiResponse;
	}

	@Override
	public APIResponse deleteStock(Long id) {
		// TODO Auto-generated method stub
		Stock stock = stockRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Stock not available for Id :" + id));
		stockRepository.delete(stock);
		APIResponse apiResponse = new APIResponse(ExceptionUtility.getCode("successCode"),
				ExceptionUtility.getCode("successMsg"));
		return apiResponse;
	}

	@Override
	public APIResponse getAllStocks() {
		// TODO Auto-generated method stub

		List<Stock> enList = stockRepository.findAll();
		List<StockOutputDTO> list = enList.stream().map(x -> {
			StockOutputDTO dto = new StockOutputDTO();
			dto.setCurrentPrice(x.getCurrentPrice());
			dto.setStockName(x.getStockName());
			dto.setStockDesc(x.getStockDesc());
			dto.setId(x.getId());
			dto.setLastUpdatedTime(Utility.getStringFromDate(x.getLastUpdatedTime(), true));
			dto.setCreatedTime(Utility.getStringFromDate(x.getCreatedTime(), true));
			return dto;
		}).collect(Collectors.toList());

		APIResponse apiResponse = new APIResponse(ExceptionUtility.getCode("successCode"),
				ExceptionUtility.getCode("successMsg"));
		apiResponse.setStocks(list);
		return apiResponse;
	}

	@Override
	public APIResponse getStockById(Long id) {
		// TODO Auto-generated method stub
		Stock stock = stockRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Stock not available for Id :" + id));
		StockOutputDTO stockDTO = new StockOutputDTO();
		BeanUtils.copyProperties(stock, stockDTO);
		stockDTO.setLastUpdatedTime(Utility.getStringFromDate(stock.getLastUpdatedTime(), true));
		stockDTO.setCreatedTime(Utility.getStringFromDate(stock.getCreatedTime(), true));
		APIResponse apiResponse = new APIResponse(ExceptionUtility.getCode("successCode"),
				ExceptionUtility.getCode("successMsg"));
		apiResponse.setStock(stockDTO);
		return apiResponse;
	}

}
