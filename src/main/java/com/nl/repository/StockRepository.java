package com.nl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nl.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

	@Query(value = "SELECT * FROM Stock s WHERE upper(s.name)=upper(?1)", nativeQuery = true)
	Stock findStockByName(String stockName);
}
