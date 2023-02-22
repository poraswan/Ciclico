package com.ciclico.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ciclico.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	List<Product> findAll();

	List<Product> findProductById(Long id_product);
}
