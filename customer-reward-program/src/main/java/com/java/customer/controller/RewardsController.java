package com.java.customer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.java.customer.model.Customer;
import com.java.customer.service.RewardsService;
import com.java.rewards.exception.CustomerNotFoundException;
import com.java.rewards.exception.ErrorResponse;


/**
 * This is a controller class that implements 
 * getCustomer() method to call service class method getCustomerById and
 * handleCustomerNotFoundException() method to handle exception when customer Not Found  
 *  
 * @author mmasali
 * @version 1.0
 * @since   2014-12-23 
 *
 */
@RestController
public class RewardsController {
	
	private static final Logger logger = LoggerFactory.getLogger(RewardsController.class);
	
	@Autowired
	private RewardsService rewardsService;

	/**
	 * 
	 * @param id
	 * @return Customer
	 */
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Integer id) {
		logger.info("Info level - Inside getCustomer");
		Customer customer = rewardsService.getCustomerById(id);

		logger.info("Info level - Inside getCustomer");
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		
	}
	
	/**
	 * 
	 * @param ex
	 * @return ErrorResponse with valid error code and message
	 */
	@ExceptionHandler(value = CustomerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleCustomerNotFoundException(CustomerNotFoundException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}
	    		

}
