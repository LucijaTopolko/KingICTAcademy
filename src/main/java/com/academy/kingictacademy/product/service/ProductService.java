package com.academy.kingictacademy.product.service;

import com.academy.kingictacademy.product.entity.Product;
import com.academy.kingictacademy.product.entity.ProductDTO;
import com.academy.kingictacademy.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private List<ProductDTO> productToDTO(List<Product> products) {
        return products.stream()
                .map(product -> new ProductDTO(
                        product.getThumbnail(),
                        product.getTitle(),
                        product.getPrice(),
                        product.getDescription()))
                .collect(Collectors.toList());
    }


    public Product getProduct(String title) {
        return productRepository.getProductByTitleIgnoreCase(title);
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return productToDTO(products);
    }

    public List<ProductDTO> findByTitle(String title) {
        List<Product> products= productRepository.findByTitleContainingIgnoreCase(title);

        return productToDTO(products);
    }

    public List<ProductDTO> filter(String category, Double minPrice, Double maxPrice) {
        List<Product> products = productRepository.findByCategoryAndPriceBetween(category, minPrice, maxPrice);

        return productToDTO(products);
    }
}
