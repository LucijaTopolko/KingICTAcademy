package com.academy.kingictacademy.product.service;

import com.academy.kingictacademy.product.entity.Product;
import com.academy.kingictacademy.product.entity.ProductDTO;
import com.academy.kingictacademy.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product:products) {
            productDTOS.add(new ProductDTO(product.getThumbnail(),product.getTitle(),product.getPrice(),product.getDescription()));
        }
        return productDTOS;
    }
}
