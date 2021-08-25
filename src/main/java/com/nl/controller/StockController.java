package com.nl.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nl.beans.APIResponse;
import com.nl.beans.StockInputDTO;
import com.nl.exception.ValidationException;
import com.nl.service.StockService;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

	@Autowired
	private StockService stockService;

	@GetMapping
	public ResponseEntity<APIResponse> getAllStocks() {
		return ResponseEntity.ok().body(stockService.getAllStocks());
	}

	@GetMapping("/{id}")
	public ResponseEntity<APIResponse> getStockById(@PathVariable(value = "id") Long stockId) {
		return ResponseEntity.ok().body(stockService.getStockById(stockId));
	}

	@PostMapping
	public ResponseEntity<APIResponse> createStock(@Valid @NotNull @RequestBody StockInputDTO stock)
			throws ValidationException {
		return ResponseEntity.ok().body(stockService.createStock(stock));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse> deleteStock(@PathVariable(value = "id") Long stockId) {
		return ResponseEntity.ok().body(stockService.deleteStock(stockId));
	}

	@PutMapping("/{id}")
	public ResponseEntity<APIResponse> updateStock(@PathVariable(value = "id") Long stockId,
			@Valid @NotNull @RequestBody StockInputDTO stock) throws ValidationException {
		return ResponseEntity.ok().body(stockService.updateStock(stockId, stock));
	}

}
