package br.edu.ufcg.cccpharma.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@PostMapping
	public Product save(@RequestBody Product product) {
		return this.productService.save(product);
	}
	
	@GetMapping
	public Page<Product> findAll(Pageable pageable) {
		return this.productService.findAll(pageable);
	}
	
	@GetMapping("/{code}")
	public Product findByCode(@PathVariable String code) {
		return this.productService.findByCode(code);
	}
	
	@GetMapping("/name/{name}")
	public Product findByName(@PathVariable String name) {
		return this.productService.findByName(name);
	}
	
	@DeleteMapping("/{code}")
	public void deleteByCode(@PathVariable String code) {
		this.productService.deleteByCode(code);
	}
	
	@DeleteMapping
	public void deleteAll() {
		this.productService.deleteAll();
	}
	
}