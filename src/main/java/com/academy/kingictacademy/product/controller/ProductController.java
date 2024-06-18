package com.academy.kingictacademy.product.controller;

import com.academy.kingictacademy.product.entity.Product;
import com.academy.kingictacademy.product.entity.ProductDTO;
import com.academy.kingictacademy.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAll(){
        List<ProductDTO> productDTOS = productService.getAllProducts();
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping()
    public ResponseEntity<Product> getProduct(@RequestParam String title){
        Product product = productService.getProduct(title);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchByTitle(@RequestParam String title) {
        List<ProductDTO> productDTOS = productService.findByTitle(title);
        return ResponseEntity.ok(productDTOS);
    }
}
