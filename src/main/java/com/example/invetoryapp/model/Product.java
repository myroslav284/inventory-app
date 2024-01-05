package com.example.invetoryapp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 200, nullable = false, unique = true)
    private String name;
    private float price;
    @ManyToOne
    @JoinColumn(name = "category_id" )
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductDetails> details = new ArrayList<>();

    public Product() {
    }

    public Product(Integer id, String name, float price, Category category, List<ProductDetails> details) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductDetails> getDetails() {
        return details;
    }
    public void setDetails(List<ProductDetails> details) {
         this.details = details;
    }

    public void addDetail(String name, String value) {
        this.details.add(new ProductDetails(name, value, this));
    }
    public void setDetail(Integer id, String name, String value) {
        this.details.add(new ProductDetails(id, name, value, this));
    }
}
