package com.academy.kingictacademy.product.service;

import com.academy.kingictacademy.category.Category;
import com.academy.kingictacademy.category.CategoryRepository;
import com.academy.kingictacademy.exceptions.NoCategoryException;
import com.academy.kingictacademy.exceptions.NoProductException;
import com.academy.kingictacademy.exceptions.PriceException;
import com.academy.kingictacademy.product.entity.Product;
import com.academy.kingictacademy.product.entity.ProductDTO;
import com.academy.kingictacademy.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // pretvara listu proizvoda u listu ProductDTO
    public List<ProductDTO> productToDTO(List<Product> products) {
        return products.stream()
                .map(product -> new ProductDTO(
                        product.getThumbnail(),
                        product.getTitle(),
                        product.getPrice(),
                        product.getDescription()))
                .collect(Collectors.toList());
    }

    // pronalazi podatke o nekom proizvodu
    @CacheEvict(value = "productdetails", allEntries = true)
    public Product getProduct(String title) {
        Product product = productRepository.getProductByTitleIgnoreCase(title);
        if (product==null) {
            throw new NoProductException("Proizvod ovog naziva ne postoji u bazi!");
        }
        return product;
    }

    // pronalazi sve proizvode iz baze
    @CacheEvict(value = "allproducts", allEntries = true)
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()) {
            throw new NoProductException("Baza proizvoda je prazna!");
        }
        return productToDTO(products);
    }

    // pronalazi sve proizvode po nazivu
    @CacheEvict(value = "search", allEntries = true)
    public List<ProductDTO> findByTitle(String title) {
        List<Product> products= productRepository.findByTitleContainingIgnoreCase(title);
        if(products.isEmpty()) {
            throw new NoProductException("Pretraga nije dala rezultate!");
        }
        return productToDTO(products);
    }

    // pronalazi sve proizvode koji zadovoljavaju filter
    @CacheEvict(value = "filter", allEntries = true)
    public List<ProductDTO> filter(String category, Double minPrice, Double maxPrice) {
        if (category!=null) {
            Optional<Category> c = categoryRepository.findBySlug(category);
            if (c.isEmpty()) {
                throw new NoCategoryException("Ova kategorija ne postoji!");
            }
        }
        if (minPrice!= null && minPrice<0 || maxPrice!=null && maxPrice<0) {
            throw new PriceException("Cijena ne smije biti negativna!");
        }
        if (minPrice!=null && maxPrice!=null && minPrice>maxPrice) {
            throw new PriceException("Minimalna cijena ne smije biti veća od maksimalne cijene!");
        }
        List<Product> products = productRepository.findByCategoryAndPriceBetween(category, minPrice, maxPrice);
        if(products.isEmpty()) {
            throw new NoProductException("Pretraga nije dala rezultate!");
        }
        return productToDTO(products);
    }
}
