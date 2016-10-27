package com.thoughtworks.gaia.user.model;

import com.thoughtworks.gaia.common.jpa.IdBaseModel;
import com.thoughtworks.gaia.product.entity.Product;
import com.thoughtworks.gaia.product.model.ProductModel;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USER")
public class UserModel extends IdBaseModel {
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @OneToMany
    private List<ProductModel> products;

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
