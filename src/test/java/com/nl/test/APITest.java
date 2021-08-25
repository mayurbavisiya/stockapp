package com.nl.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nl.entity.Stock;
import com.nl.repository.StockRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class APITest {
	@Autowired
	private StockRepository stockRepository;

	@Test
	public void getALLStock() {
		stockRepository.findAll();
		Assert.assertEquals(stockRepository.findAll().size(), 10);
	}

	@Test
	public void createSTock() {

		Stock stock1 = new Stock();
		stock1.setStockName("TCS");
		stock1.setStockDesc("Tata Consultancy Services Limited");
		stock1.setLastUpdatedTime(new Date());
		stock1.setCurrentPrice("3456.05");
		stockRepository.save(stock1);
		// It will be 11 we are already saving 10 stocks on start up
		Assert.assertEquals(stock1.getId(), Long.valueOf("11"));
	}

	@Test
	public void updateStock() {
		Stock stock1 = new Stock();
		stock1.setId((long) 1);
		stock1.setStockName("TCS");
		stock1.setStockDesc("Tata Consultancy Update");
		stock1.setLastUpdatedTime(new Date());
		stock1.setCurrentPrice("100");
		stockRepository.save(stock1);
		Assert.assertEquals(stock1.getId(), Long.valueOf("1"));
		Assert.assertEquals(stockRepository.findAll().size(), 10);
	}
}