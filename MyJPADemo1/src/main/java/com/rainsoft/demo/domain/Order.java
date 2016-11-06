package com.rainsoft.demo.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ORDER_DETAIL")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3102567162804284240L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id; 
	
	@ManyToOne
	private Customer customer; 
	
	@Column
	private String productName;
	
	@Column(columnDefinition="Decimal")	
	private BigDecimal price; 
	
	public Order() {
	}

	public Order(String productName, BigDecimal price) {
		this.productName = productName;
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [id=");
		builder.append(id);
		builder.append(", productName=");
		builder.append(productName);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	} 
	
	
	
}
