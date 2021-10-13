package com.example.hibernatejava.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.hibernatejava.entity.Product;
import com.example.hibernatejava.entity.Response;
import com.example.hibernatejava.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javassist.NotFoundException;

@Transactional
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Response> getProductById(@PathVariable("id") int id) throws Exception {

        Product foundProduct = productService.findProductById(id);

        if (foundProduct == null) {
            throw new NotFoundException("Product with id: " + id + " not found");
        }

        Response res = new Response("SUCCESS", foundProduct);
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity<Response> getAllProducts(@RequestParam(required = false, defaultValue = "") String search)
            throws Exception {

        List<Product> products = new ArrayList<>();
        products = productService.findAllProducts(search);

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Response res = new Response("SUCCESS", products);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Response> createProduct(@RequestBody Product product) throws Exception {

        productService.newProduct(product);
        URI location = URI.create(String.format("/product/%s", product.getName()));

        Response res = new Response("SUCCESS", product);
        return ResponseEntity.created(location).body(res);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") int id) throws Exception {

        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/categories")
    public ResponseEntity<Response> getProductCategories() throws Exception {

        List<String> categories = productService.findProductCategoriesNames();

        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Response res = new Response("SUCCESS", categories);
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/categories/{cat}")
    public ResponseEntity<Response> getProductByCategory(@PathVariable("cat") String cat) throws Exception {

        List<Product> products = productService.findProductsByCategory(cat);

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Response res = new Response("SUCCESS", products);
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/categories/{cat}/{index}")
    public ResponseEntity<Response> getProductFromCategory(@PathVariable("cat") String cat,
            @PathVariable("index") int index) throws Exception {

        List<Product> products = productService.findProductFromCategory(cat, index);

        if (products.isEmpty() || products.size() <= index) {
            return ResponseEntity.noContent().build();
        }

        Response res = new Response("SUCCESS", products.get(index));
        return ResponseEntity.ok(res);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteAllProducts() throws Exception {

        productService.deleteAllProducts();

        return ResponseEntity.noContent().build();
    }

    @ResponseBody
    @PutMapping(path = "/{id}")
    public ResponseEntity<Response> updateProduct(@PathVariable("id") int id, @RequestBody Product productToMerge)
            throws Exception {
        Product updatedProduct = productService.updateProduct(id, productToMerge);

        Response res = new Response("SUCCESS", updatedProduct);

        return ResponseEntity.ok(res);

    }

}
