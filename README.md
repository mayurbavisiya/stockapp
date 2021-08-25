# Stock


Application Requirements


	Create a JVM based backend application using REST. That contains the following
  
	
endpoints:


	GET /api/stocks (get a list of stocks)
	POST /api/stocks (create a stock)
	GET /api/stocks/1 (get one stock from the list)
	PUT /api/stocks/1 (update the price of a single stock)
  

The list of stocks should be created in-memory on application start-up.


The stock object contains at least the following fields:

	• id (Number);
  
	• name (String);
  
	• currentPrice (Amount);
  
	• lastUpdate (Timestamp).
  
  
	
Use any frameworks that you see fit to build and test this application.


Nice to have

We would also like you to create a front-end which shows the stock list


#####################################################################################

Technologies


 Backend


	Java 8 + Spring boot (V2.2.2)+ JPA + H2


 Front End


	thymeleaf + Bootstrap + CSS + JS + Jquery + Ajax
	


Features


       REST APIs + OpenAPI Sepcification Swagger + Logging + Error Management


How to run

   Import this project as maven in any IDE and perform below step
   
   	1) mvn clean install
	2) run StockApplication.java

  Or
  
   You can simply run the jar file with the below command
      
      	1)  java -jar StockApp-0.0.1-SNAPSHOT.jar  
  


DB URL


	http://localhost:8080/stock/h2


Swagger URL


	http://localhost:8080/stock/swagger-ui.html


Application URL(Stock Dashboard)


	http://localhost:8080/stock/


	
What can be improved


	1) Spring Security can be added
  
	2) New features can be addedd, Such as buy and sell stock.
	

	
