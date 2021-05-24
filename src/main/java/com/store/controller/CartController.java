package com.store.controller;

import com.store.entity.Product;
import com.store.entity.User;
import com.store.service.ProductService;
import com.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private UserService userService;

    @Autowired
    ProductService productService;

    @GetMapping("profile/cart-product")
    public ModelAndView cartProduct(Principal principal) {
        ModelAndView mv = new ModelAndView("profile/cart-product");
        User user = userService.getUser(principal.getName());
        mv.addObject("user", user);
        int total = findSum(user);
        mv.addObject("total", total);
        return mv;
    }

    private int findSum(User user) {
        List<Product> productList = user.getProductList();
        int sum =0;
        for (Product p : productList) {
            sum += p.getPrice();
        }
        return sum;
    }

    @GetMapping("addToCart/{id}")
    public String addToCart(@PathVariable("id")Long productId, Principal principal) {
        ModelAndView mv = new ModelAndView("profile/cart-product");
        User user = userService.getUser(principal.getName());
        Product product = productService.getById(productId).get();

        List<Product> productList = new ArrayList<Product>();
        productList.add(product);
        user.setProductList(productList);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        product.setUserList(userList);

        userService.update(user);
        productService.addProduct(product);

        int total = findSum(user);
        mv.addObject("total", total);

        mv.addObject("user", user);

        return "redirect:/{id}";
    }
}
