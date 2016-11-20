package com.rainsoft.demo;

import java.io.Serializable;
/**
 * An poor object for demo purpose. We all love pokemon.
 * @author Lance
 *
 */
public class Pokemon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3521867302367525675L;
	private String id; 
	private String name; 
	private Integer cp; 
	private Integer hp; 
	private Integer dust; 
	private Boolean power;
		
	public Pokemon() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCp() {
		return cp;
	}

	public void setCp(Integer cp) {
		this.cp = cp;
	}

	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}

	public Integer getDust() {
		return dust;
	}

	public void setDust(Integer dust) {
		this.dust = dust;
	}

	public Boolean getPower() {
		return power;
	}

	public void setPower(Boolean power) {
		this.power = power;
	}	
	
	
	
}
