package com.java.customer;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.java.customer.model.Customer;
import com.java.customer.repository.CustomerRepository;
import com.java.customer.service.RewardsService;
import com.java.rewards.exception.CustomerNotFoundException;


public class CustomerRewardProgramApplicationTests {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private RewardsService customerService;

    private Customer customer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customer = new Customer();
        customer.setId(1);
        customer.setName("Manjunath");
        customer.setTransactions(new ArrayList<>());
    }

    @Test
    public void testGetCustomerById_CustomerExists() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        Customer result = customerService.getCustomerById(1);

        assertNotNull(result);
        assertEquals(1, result.getId().longValue());
    }

    @Test
    public void testGetCustomerById_CustomerNotFound() {
        when(customerRepository.findById(1)).thenReturn(Optional.empty());

        try {
            customerService.getCustomerById(1);
            fail("Expected CustomerNotFoundException to be thrown");
        } catch (CustomerNotFoundException e) {
        	assertEquals("No Customer found with id-1", e.getMessage());
        }

    }
}
