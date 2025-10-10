package com.shreeeyan.controller;

import com.shreeeyan.model.User;
import com.shreeeyan.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private final StoreService storeService = new StoreService();

    @GetMapping("/register")
    public String registerForm() { return "register"; }

    @PostMapping("/register")
    public String doRegister(@RequestParam String name, @RequestParam String phone, Model model) {
        if (storeService.findUserByPhone(phone).isPresent()) {
            model.addAttribute("message","Phone already registered.");
            return "register";
        }
        User u = storeService.registerUser(name, phone);
        model.addAttribute("message","Registered successfully. Your ID: " + u.getId());
        return "register";
    }

    @GetMapping("/checkout")
    public String checkoutForm(Model model) {
        model.addAttribute("products", storeService.listProducts());
        return "checkout";
    }

    @PostMapping("/checkout")
    public String doCheckout(@RequestParam Map<String,String> params, Model model) {
        Map<String,Integer> cart = new HashMap<>();
        String phone = params.getOrDefault("phone", null);
        for (String key : params.keySet()) {
            if (key.startsWith("product_")) {
                String pid = key.substring("product_".length());
                try {
                    int q = Integer.parseInt(params.get(key));
                    if (q > 0) cart.put(pid, q);
                } catch (NumberFormatException ignored) {}
            }
        }
        User user = null;
        if (phone != null && !phone.isBlank()) {
            user = storeService.findUserByPhone(phone).orElse(null);
        }
        var bill = storeService.calculateBill(cart, user);
        model.addAttribute("bill", bill);
        model.addAttribute("cart", cart);
        model.addAttribute("productsMap", storeService.listProducts());
        model.addAttribute("user", user);
        return "checkout";
    }
}
