package com.academy.kingictacademy;

import com.academy.kingictacademy.exceptions.NoProductException;
import com.academy.kingictacademy.product.controller.ProductController;
import com.academy.kingictacademy.product.entity.ProductDTO;
import com.academy.kingictacademy.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductController.class)
class KingIctAcademyApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	private ProductDTO product1 = new ProductDTO();
	private ProductDTO product2 = new ProductDTO();

	@BeforeEach
	public void setup() {
		product1.setTitle("Red Lipstick");
		product1.setPrice(12.99);
		product1.setDescription("The Red Lipstick is a classic and bold choice for adding a pop of color to your lips. With a creamy ...");
		product1.setThumbnail("https://cdn.dummyjson.com/products/images/beauty/Red%20Lipstick/thumbnail.png");

		product2.setTitle("Red Nail Polish");
		product2.setPrice(8.99);
		product2.setDescription("The Red Nail Polish offers a rich and glossy red hue for vibrant and polished nails. With a quick-dr...");
		product2.setThumbnail("https://cdn.dummyjson.com/products/images/beauty/Red%20Nail%20Polish/thumbnail.png");
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void unauthorized() throws Exception {
		List<ProductDTO> allProducts = Arrays.asList(product1, product2);

		when(productService.getAllProducts()).thenReturn(
				allProducts
		);

		mockMvc.perform(get("/product/all"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void allProducts() throws Exception {
		List<ProductDTO> allProducts = Arrays.asList(product1, product2);

		when(productService.getAllProducts()).thenReturn(
				allProducts
		);

		mockMvc.perform(get("/product/all")
						.with(user("admin").roles("ADMIN")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].title").value(product1.getTitle()))
				.andExpect(jsonPath("$[0].price").value(product1.getPrice()))
				.andExpect(jsonPath("$[0].description").value(product1.getDescription()))
				.andExpect(jsonPath("$[0].thumbnail").value(product1.getThumbnail()))
				.andExpect(jsonPath("$[1].title").value(product2.getTitle()))
				.andExpect(jsonPath("$[1].price").value(product2.getPrice()))
				.andExpect(jsonPath("$[1].description").value(product2.getDescription()))
				.andExpect(jsonPath("$[1].thumbnail").value(product2.getThumbnail()));
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void allProducts_empty() throws Exception {
		List<ProductDTO> allProducts = new ArrayList<>();

		when(productService.getAllProducts()).thenThrow(new NoProductException("Baza proizvoda je prazna!"));

		mockMvc.perform(get("/product/all")
						.with(user("admin").roles("ADMIN")))
				.andExpect(status().isNotFound())
				.andExpect(content().string("Baza proizvoda je prazna!"));
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void searchByTitle() throws Exception {
		List<ProductDTO> productDTOs = Arrays.asList(product1, product2);

		when(productService.findByTitle("red")).thenReturn(productDTOs);

		mockMvc.perform(get("/product/search")
						.with(user("admin").roles("ADMIN"))
						.param("title", "red")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].title").value(product1.getTitle()))
				.andExpect(jsonPath("$[0].price").value(product1.getPrice()))
				.andExpect(jsonPath("$[0].description").value(product1.getDescription()))
				.andExpect(jsonPath("$[0].thumbnail").value(product1.getThumbnail()))
				.andExpect(jsonPath("$[1].title").value(product2.getTitle()))
				.andExpect(jsonPath("$[1].price").value(product2.getPrice()))
				.andExpect(jsonPath("$[1].description").value(product2.getDescription()))
				.andExpect(jsonPath("$[1].thumbnail").value(product2.getThumbnail()));
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void filter() throws Exception {

		List<ProductDTO> productDTOs = Arrays.asList(product1, product2);

		when(productService.filter("beauty", 5.0, 20.0)).thenReturn(productDTOs);

		mockMvc.perform(get("/product/filter")
						.with(user("admin").roles("ADMIN"))
						.param("category", "beauty")
						.param("minPrice", String.valueOf(5.0))
						.param("maxPrice", String.valueOf(20.0))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].title").value(product1.getTitle()))
				.andExpect(jsonPath("$[0].price").value(product1.getPrice()))
				.andExpect(jsonPath("$[0].description").value(product1.getDescription()))
				.andExpect(jsonPath("$[0].thumbnail").value(product1.getThumbnail()))
				.andExpect(jsonPath("$[1].title").value(product2.getTitle()))
				.andExpect(jsonPath("$[1].price").value(product2.getPrice()))
				.andExpect(jsonPath("$[1].description").value(product2.getDescription()))
				.andExpect(jsonPath("$[1].thumbnail").value(product2.getThumbnail()));
	}



}
