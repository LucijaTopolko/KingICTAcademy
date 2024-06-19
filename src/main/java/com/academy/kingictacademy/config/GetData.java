package com.academy.kingictacademy.config;

import com.academy.kingictacademy.category.Category;
import com.academy.kingictacademy.category.CategoryRepository;
import com.academy.kingictacademy.product.entity.Product;
import com.academy.kingictacademy.product.entity.ProductResponse;
import com.academy.kingictacademy.product.repository.ProductRepository;
import com.academy.kingictacademy.user.entity.LoginData;
import com.academy.kingictacademy.user.entity.User;
import com.academy.kingictacademy.user.entity.UserResponse;
import com.academy.kingictacademy.user.repository.LoginRepository;
import com.academy.kingictacademy.user.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class GetData {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

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

        for (User user : users) {
            Optional<User> existingUser = userRepository.findUserByUsernameIgnoreCase(user.getUsername());
            if (existingUser.isEmpty()) {
                loginRepository.save(new LoginData(user.getUsername(), passwordEncoder.encode(user.getPassword()), user.getRole()));
                userRepository.save(user);
            }
        }

    }

    public void getProducts() throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(productsUrl, String.class);

        ProductResponse productResponse = objectMapper.readValue(json, ProductResponse.class);

        List<Product> products = productResponse.getProducts();

        for (Product product : products) {
            Optional<Product> existingProduct = productRepository.findByTitleIgnoreCase(product.getTitle());
            if (existingProduct.isEmpty()) {
                productRepository.save(product);
            }
        }
    }

    public void getCategories() throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(categoriesUrl, String.class);

        Category[] categories = objectMapper.readValue(json, Category[].class);

        for (Category category: categories) {
            Optional<Category> existingCategory = categoryRepository.findBySlug(category.getSlug());
            if (existingCategory.isEmpty()) {
                categoryRepository.save(category);
            }
        }
    }

}
