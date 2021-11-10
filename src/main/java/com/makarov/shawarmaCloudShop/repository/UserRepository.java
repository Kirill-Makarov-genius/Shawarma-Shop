package com.makarov.shawarmaCloudShop.repository;

import org.springframework.data.repository.CrudRepository;

import com.makarov.shawarmaCloudShop.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByFirstName(String firstName);
	
}
