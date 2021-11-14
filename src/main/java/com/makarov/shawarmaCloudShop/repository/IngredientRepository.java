package com.makarov.shawarmaCloudShop.repository;

import org.springframework.data.repository.CrudRepository;

import com.makarov.shawarmaCloudShop.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long>{

}
