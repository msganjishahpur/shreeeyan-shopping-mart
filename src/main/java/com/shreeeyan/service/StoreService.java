package com.shreeeyan.service;

import com.shreeeyan.model.Product;
import com.shreeeyan.model.User;
import com.shreeeyan.repository.InMemoryRepository;
import java.util.*;

public class StoreService {
    private final InMemoryRepository repo = new InMemoryRepository();

    public Collection<Product> listProducts() { return repo.listProducts(); }
    public Product getProduct(String id) { return repo.findProduct(id); }

    public User registerUser(String name, String phone) {
        String id = "U" + (new Random().nextInt(9000) + 1000);
        User u = new User(id, name, phone, true);
        repo.addUser(u);
        return u;
    }

    public Optional<User> findUserByPhone(String phone) { return repo.findUserByPhone(phone); }

    public Map<String, Double> calculateBill(Map<String, Integer> cart, User userNullable) {
        double subtotal = 0.0;
        for (Map.Entry<String, Integer> e : cart.entrySet()) {
            Product p = repo.findProduct(e.getKey());
            if (p != null) subtotal += p.getPrice() * e.getValue();
        }
        double discount = 0.0;
        if (userNullable != null && userNullable.isMember() && subtotal > 500) {
            discount = subtotal * 0.05;
        }
        double finalTotal = subtotal - discount;
        Map<String, Double> resp = new HashMap<>();
        resp.put("subtotal", subtotal);
        resp.put("discount", discount);
        resp.put("finalTotal", finalTotal);
        return resp;
    }
}
