package com.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {


    @GetMapping("/{id}")
    public String getProduct(@PathVariable("id") int id) {
        return "product";
    }
}
