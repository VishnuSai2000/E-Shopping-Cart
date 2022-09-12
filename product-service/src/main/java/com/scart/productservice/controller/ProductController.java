package com.scart.productservice.controller;

import com.scart.productservice.model.Product;
import com.scart.productservice.service.ProductService;
import com.scart.productservice.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public Product addProducts(@RequestBody Product product) {
        return productService.addProducts(product);

    }

    @GetMapping("/view")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/update/{id}")
    public Product updateProducts(@RequestBody Product product, @PathVariable("id") int productId) {
        return productService.updateProducts(product, productId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductsById(@PathVariable("id") int productId) {
        productService.deleteProductsById(productId);
    }
    @GetMapping("/getbyproductname")
    public O

}
