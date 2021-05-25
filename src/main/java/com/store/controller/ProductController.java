package com.store.controller;

import com.store.entity.Product;
import com.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public String getProduct(@PathVariable("id") Long id, Model model) {
        Optional<Product> productOpt = productService.getById(id);
        if(productOpt.isPresent()) {
            Product product = productOpt.get();
            model.addAttribute("product", product);
            return "product";
        }
        return "redirect:/";
    }
}
