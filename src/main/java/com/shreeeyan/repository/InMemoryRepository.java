package com.shreeeyan.repository;

import com.shreeeyan.model.Product;
import com.shreeeyan.model.User;
import java.util.*;

public class InMemoryRepository {
    private final Map<String, Product> products = new LinkedHashMap<>();
    private final Map<String, User> users = new HashMap<>();

    public InMemoryRepository() {
        addProduct(new Product("P001","Rice 5kg","grocery",250));
        addProduct(new Product("P002","Wheat 5kg","grocery",200));
        addProduct(new Product("P003","Potato 1kg","vegetable",30));
        addProduct(new Product("P004","Onion 1kg","vegetable",40));
        addProduct(new Product("P005","Cooking Oil 1L","daily",150));
        addProduct(new Product("P006","Soap","daily",40));
    }

    public void addProduct(Product p) { products.put(p.getId(), p); }
    public Collection<Product> listProducts() { return products.values(); }
    public Product findProduct(String id) { return products.get(id); }

    public void addUser(User u) { users.put(u.getId(), u); }
    public User findUser(String id) { return users.get(id); }
    public Optional<User> findUserByPhone(String phone) {
        return users.values().stream().filter(u -> phone.equals(u.getPhone())).findFirst();
    }
}
