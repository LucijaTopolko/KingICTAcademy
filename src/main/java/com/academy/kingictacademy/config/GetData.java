package com.academy.kingictacademy.config;

import com.academy.kingictacademy.category.Category;
import com.academy.kingictacademy.category.CategoryRepository;
import com.academy.kingictacademy.product.entity.Product;
import com.academy.kingictacademy.product.entity.ProductResponse;
import com.academy.kingictacademy.product.repository.ProductRepository;
import com.academy.kingictacademy.user.entity.User;
import com.academy.kingictacademy.user.entity.UserResponse;
import com.academy.kingictacademy.user.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class GetData {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Value("${users.api.url}")
    private String usersUrl;

    @Value("${products.api.url}")
    private String productsUrl;

    @Value("${categories.api.url}")
    private String categoriesUrl;

    ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() throws JsonProcessingException {
        getUsers();
        getProducts();
        getCategories();
    }

    public void getUsers() throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(usersUrl, String.class);

        UserResponse userResponse = objectMapper.readValue(json, UserResponse.class);

        List<User> users = userResponse.getUsers();

        userRepository.saveAll(users);
    }

    public void getProducts() throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(productsUrl, String.class);

        ProductResponse productResponse = objectMapper.readValue(json, ProductResponse.class);

        List<Product> products = productResponse.getProducts();

        productRepository.saveAll(products);
    }

    public void getCategories() throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(categoriesUrl, String.class);

        Category[] categories = objectMapper.readValue(json, Category[].class);

        for (Category category: categories) {
            Category c = categoryRepository.findBySlug(category.getSlug());
            if (c==null) {
                categoryRepository.save(category);
            }
        }
    }

}
