package com.rainsoft.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rainsoft.demo.domain.Customer;
import com.rainsoft.demo.domain.Order;
import com.rainsoft.demo.repository.CustomerRepository;
import com.rainsoft.demo.repository.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyJpaDemo1ApplicationTests {

	private final static Logger logger = LoggerFactory.getLogger(MyJpaDemo1ApplicationTests.class);
	
	@Autowired
	CustomerRepository repository; 
	
	@Autowired
	OrderRepository repository2; 
	
	@Test
	public void contextLoads() {
		repository.save(new Customer("name1","email1"));
		repository.save(new Customer("name2","email2"));
		Customer cust = repository.findOne(1L);
		Order order = new Order("myProduct",new BigDecimal(45.5d));
		order.setCustomer(cust);
		repository2.save(order);
		List<Order> orders = new ArrayList<>();
		orders.add(order);
		cust.setOrders(orders);
		repository.save(cust);
		logger.info("Customer : {}", cust);		
	}

}
