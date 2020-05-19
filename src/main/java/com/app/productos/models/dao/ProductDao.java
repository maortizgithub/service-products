package com.app.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.app.commons.models.entity.Product;

public interface ProductDao extends CrudRepository<Product, Long> {

}
