package com.nl.test;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nl.entity.Stock;
import com.nl.repository.StockRepository;

@SpringBootTest
public class AppH2Test {

	@Autowired
	private StockRepository stockRepository;

	@Test
	@DisplayName("Create Stock Test ")
	void createUserTest() {

		Stock created = stockRepository.save(new Stock("Test", "Test Desc", "3033.45"));
		if (created != null)
			System.out.println("Stock created successfully ! DB is up");
		assertTrue(created != null);
	}
}
