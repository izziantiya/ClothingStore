package com.store.controller;

import com.store.entity.Product;
import com.store.entity.ProductType;
import com.store.service.ProductService;
import com.store.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class DefaultController {

    @Autowired
    ProductTypeService productTypeService;

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<ProductType> types = productTypeService.getAll();
        Map<ProductType, List<Product>> map = new LinkedHashMap<>();
        types.forEach(type -> map.put(type, productService.getType(type)));
        model.addAttribute("map", map);
        return "index";
    }
}
