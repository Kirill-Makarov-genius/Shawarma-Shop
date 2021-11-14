package com.makarov.shawarmaCloudShop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table(name="shawarma")
public class Shawarma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long shawarma_id;
	
	@NotNull
	@Size(min=0, max=50, message="Name should be between 0 and 50 characters")
	private String name;
	
	@Size(min=2, message="You must choose at least 2 ingredients")
	@ManyToMany
	@JoinTable(
			name="shawarma_ingredients",
			joinColumns = @JoinColumn(name="shawarma"),
			inverseJoinColumns = @JoinColumn(name="ingredient"))
	private List<Ingredient> ingredients = new ArrayList<>();
}











