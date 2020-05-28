package com.amith.arsbilling.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amith.arsbilling.model.ProductsEntity;
 
@Repository
public interface ProductsRepository
        extends JpaRepository<ProductsEntity, Long> {
 
	Optional<ProductsEntity> findByName(String name);
	
	Optional<List<ProductsEntity>> findByCount(int count);
}
