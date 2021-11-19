package com.makarov.shawarmaCloudShop.repository;

import org.springframework.data.repository.CrudRepository;

import com.makarov.shawarmaCloudShop.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
