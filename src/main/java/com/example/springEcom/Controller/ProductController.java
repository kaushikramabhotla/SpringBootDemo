package com.example.springEcom.Controller;


import com.example.springEcom.Model.Product;
import com.example.springEcom.Service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> results;
        results = productService.getAllProducts();

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product product = productService.getProductById(id);
        if(product != null){
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/addProduct")
    public String addProduct(){
        Product p = new Product(6, "kau", "kau", "kau", new BigDecimal(2.55), "kau", new Date(2000, 12, 12), true, 5);

        productService.addProduct(p);
        return "Success";
    }
    @GetMapping("/hello")
    public String getHello(){
        return "helloo";
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id){
        Product updatedProduct = productService.getProductById(id);
        if(updatedProduct != null){
            productService.deleteProduct(id);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping ("/product/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
        List<Product> products = productService.searchProducts(keyword);

        return new ResponseEntity<>(products, HttpStatus.OK);

    }
}
