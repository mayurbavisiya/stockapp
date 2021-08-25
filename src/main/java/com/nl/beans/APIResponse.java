package com.nl.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class APIResponse {

	private String responseCode;
	private String responseMsg;

	@JsonInclude(Include.NON_NULL)
	private StockOutputDTO stock;

	@JsonInclude(Include.NON_NULL)
	private List<StockOutputDTO> stocks;

	public APIResponse(String responseCode, String responseMsg) {
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public StockOutputDTO getStock() {
		return stock;
	}

	public void setStock(StockOutputDTO stock) {
		this.stock = stock;
	}

	public List<StockOutputDTO> getStocks() {
		return stocks;
	}

	public void setStocks(List<StockOutputDTO> stocks) {
		this.stocks = stocks;
	}

}
