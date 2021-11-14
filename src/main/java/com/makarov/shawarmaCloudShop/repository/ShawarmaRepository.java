package com.makarov.shawarmaCloudShop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.makarov.shawarmaCloudShop.domain.Shawarma;

@Repository
public interface ShawarmaRepository extends CrudRepository<Shawarma, Long>{
	
	
}
