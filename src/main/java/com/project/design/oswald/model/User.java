package com.project.design.oswald.model;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String product_id;
    private String child_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getProduct_id()
    {
        return product_id;
    }
    public void setProduct_id(String product_id)
    {
        this.product_id = product_id;
    }
    public String getChild_name()
    {
        return child_name;
    }
    public void setChild_name(String child_name)
    {
        this.child_name = child_name;
    }
}