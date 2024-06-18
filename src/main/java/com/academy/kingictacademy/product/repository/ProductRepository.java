package com.academy.kingictacademy.product.repository;

import com.academy.kingictacademy.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductByTitle(String title);
    List<Product> findByTitleContainingIgnoreCase(String title);
}
