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

    public Product getProduct(String title) {
        return productRepository.getProductByTitle(title);
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product:products) {
            productDTOS.add(new ProductDTO(product.getThumbnail(),product.getTitle(),product.getPrice(),product.getDescription()));
        }
        return productDTOS;
    }

    public List<ProductDTO> findByTitle(String title) {
        List<Product> products= productRepository.findByTitleContainingIgnoreCase(title);

        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product:products) {
            productDTOS.add(new ProductDTO(product.getThumbnail(),product.getTitle(),product.getPrice(),product.getDescription()));
        }
        return productDTOS;
    }

    public List<ProductDTO> filter(String category, Double minPrice, Double maxPrice) {
        List<Product> products = productRepository.findByCategoryAndPriceBetween(category, minPrice, maxPrice);

        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product:products) {
            productDTOS.add(new ProductDTO(product.getThumbnail(),product.getTitle(),product.getPrice(),product.getDescription()));
        }
        return productDTOS;
    }
}
