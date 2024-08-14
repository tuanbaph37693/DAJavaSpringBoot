package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cart")
public class ShoppingCartController {

    @RequestMapping("view")
    public String list(){
        return "cart/view";
    }

}
