package com.shreeeyan.model;

public class User {
    private String id;
    private String name;
    private String phone;
    private boolean member;

    public User() {}

    public User(String id, String name, String phone, boolean member) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.member = member;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public boolean isMember() { return member; }
    public void setMember(boolean member) { this.member = member; }
}
