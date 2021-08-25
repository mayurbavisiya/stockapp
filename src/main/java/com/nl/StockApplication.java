package com.nl;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nl.entity.Stock;
import com.nl.repository.StockRepository;

@SpringBootApplication
public class StockApplication {

	@Autowired
	private StockRepository stockRepository;

	public static void main(String[] args) {

		SpringApplication.run(StockApplication.class, args);
	}

	@PostConstruct
	private void initDb() {

		Stock stock1 = new Stock();
		stock1.setStockName("TCS");
		stock1.setStockDesc("Tata Consultancy Services Limited");
		stock1.setLastUpdatedTime(new Date());
		stock1.setCurrentPrice("3456.05");

		Stock stock2 = new Stock();
		stock2.setStockName("LT");
		stock2.setStockDesc("Larsen & Toubro Limited");
		stock2.setLastUpdatedTime(new Date());
		stock2.setCurrentPrice("1665.05");

		Stock stock3 = new Stock();
		stock3.setStockName("HCLTECH");
		stock3.setStockDesc("HCL Technologies Limited");
		stock3.setLastUpdatedTime(new Date());
		stock3.setCurrentPrice("1123.95");

		Stock stock4 = new Stock();
		stock4.setStockName("TATASTEEL");
		stock4.setStockDesc("TATA Steel Limited");
		stock4.setLastUpdatedTime(new Date());
		stock4.setCurrentPrice("1467.25");

		Stock stock5 = new Stock();
		stock5.setStockName("BHARTIARTL");
		stock5.setStockDesc("Bharti Airtel Limited");
		stock5.setLastUpdatedTime(new Date());
		stock5.setCurrentPrice("636.2");

		Stock stock6 = new Stock();
		stock6.setStockName("WIPRO");
		stock6.setStockDesc("Wipro Limited");
		stock6.setLastUpdatedTime(new Date());
		stock6.setCurrentPrice("616");

		Stock stock7 = new Stock();
		stock7.setStockName("RELIANCE");
		stock7.setStockDesc("Reliance Industries Limited");
		stock7.setLastUpdatedTime(new Date());
		stock7.setCurrentPrice("2143");

		Stock stock8 = new Stock();
		stock8.setStockName("BAJAJAUTO");
		stock8.setStockDesc("Bajaj Auto Limited");
		stock8.setLastUpdatedTime(new Date());
		stock8.setCurrentPrice("3826");

		Stock stock9 = new Stock();
		stock9.setStockName("HDFCBANK");
		stock9.setStockDesc("HDFC Bank Limited");
		stock9.setLastUpdatedTime(new Date());
		stock9.setCurrentPrice("1523.6");

	
		stockRepository.save(stock1);
		stockRepository.save(stock2);
		stockRepository.save(stock3);
		stockRepository.save(stock4);
		stockRepository.save(stock5);
		stockRepository.save(stock6);
		stockRepository.save(stock7);
		stockRepository.save(stock8);
		stockRepository.save(stock9);

	}

}
