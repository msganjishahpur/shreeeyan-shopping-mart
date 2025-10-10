package com.shreeeyan.controller;

import com.shreeeyan.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final StoreService storeService = new StoreService();

    @GetMapping({"/","/home"})
    public String index(Model model) {
        model.addAttribute("products", storeService.listProducts());
        return "index";
    }
}
