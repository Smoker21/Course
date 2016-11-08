package com.rainsoft.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.rainsoft.demo.domain.Customer;
import com.rainsoft.demo.domain.Order;
import com.rainsoft.demo.repository.CustomerRepository;
import com.rainsoft.demo.repository.OrderRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class MyJpaDemo1Application {
	private static final Logger logger = LoggerFactory.getLogger(MyJpaDemo1Application.class);
	public static void main(String[] args) {
		SpringApplication.run(MyJpaDemo1Application.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(CustomerRepository repository, OrderRepository repository2) {
		return (args) -> {
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
		};
	}
}
