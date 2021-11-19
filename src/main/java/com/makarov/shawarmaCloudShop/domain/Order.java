package com.makarov.shawarmaCloudShop.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
@Entity
@Table(name="shawarma_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="deliveryname")
	@Size(min=5, max = 50, message = "Name should be between 5 and 50 characters")
	private String name;
	
	@Column(name="deliverycity")
	@Size(min=5, max = 50, message = "City name should be between 5 and 50 characters")
	private String city;
	
	@Column(name="deliveryzip")
	@Size(min=0, max = 10, message = "Zip code should be consist max 10 digits")
	private String zip;
	
	@Column(name="ccnumber")
	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;
	
	@Column(name="ccexpiration")
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
	private String ccExpiration;
	
	@Column(name="cccvv")
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
	

	@Column(name="placedat")
	private Date placedAt;
	
	@ManyToMany
	@JoinTable(
			name = "shawarma_order_shawarmas",
			joinColumns = @JoinColumn(name="shawarmaorder"),
			inverseJoinColumns = @JoinColumn(name="shawarma")
			)
	private List<Shawarma> shawarmasOrdered = new ArrayList<>();
	
	
	@PrePersist
	void placedAt() {
		this.placedAt = new Date();
	}
	
	
}
