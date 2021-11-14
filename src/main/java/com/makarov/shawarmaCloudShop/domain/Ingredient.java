package com.makarov.shawarmaCloudShop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Table(name="ingredients")
public class Ingredient {

	@Id
	@Column(name="ingredients_id")
	private final long id;
	private final String name;
@Enumerated(EnumType.STRING)
	private final Type type;
	
	public static enum Type{
		meat, vegetable, sauce
	}
	
	
}
