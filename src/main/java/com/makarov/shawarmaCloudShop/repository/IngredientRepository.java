package com.makarov.shawarmaCloudShop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.makarov.shawarmaCloudShop.domain.Ingredient;
import com.makarov.shawarmaCloudShop.domain.Ingredient.Type;

public interface IngredientRepository extends CrudRepository<Ingredient, Long>{
	
	List<Ingredient> findByTypeIs(Type type);
}
