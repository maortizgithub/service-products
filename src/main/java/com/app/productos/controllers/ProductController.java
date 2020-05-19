package com.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.commons.models.entity.Product;
import com.app.productos.models.service.IProductService;

@RestController
public class ProductController {

	@Autowired
	private Environment env;

	@Value("${server.port}")
	private Integer port;

	@Autowired
	private IProductService productService;

	@GetMapping("/list")
	public List<Product> list() {
		return productService.findAll().stream().map(product -> {
			// product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			product.setPort(port);
			return product;
		}).collect(Collectors.toList());
	}

	@GetMapping("/view/{id}")
	public Product detail(@PathVariable Long id) {
		Product product = productService.findById(id);
		// product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		product.setPort(port);

		/*
		 * try { Thread.sleep(2000l); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		return product;
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Product create(@RequestBody Product product) {
		return productService.save(product);
	}

	@PutMapping("/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product edit(@RequestBody Product product, @PathVariable Long id) {
		Product productDb = productService.findById(id);
		productDb.setName(product.getName());
		productDb.setPrice(product.getPrice());
		return productService.save(productDb);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		productService.deleteById(id);
	}
}
