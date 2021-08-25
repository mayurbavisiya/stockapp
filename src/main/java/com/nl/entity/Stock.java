package com.nl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "STOCK")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String stockName;

	@Column(name = "Description")
	private String stockDesc;

	@Column(name = "CURRENT_PRICE")
	private String currentPrice;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATE")
	private Date lastUpdatedTime;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", columnDefinition = "datetime default sysdate")
	@CreationTimestamp
	private Date createdTime;

	public Stock() {

	}

	public Stock(String stockName, String stockDesc, String currentPrice) {
		this.stockName = stockName;
		this.stockDesc = stockDesc;
		this.currentPrice = currentPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getStockDesc() {
		return stockDesc;
	}

	public void setStockDesc(String stockDesc) {
		this.stockDesc = stockDesc;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}
