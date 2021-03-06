package com.thoughtworks.gaia.product.model;

import com.thoughtworks.gaia.common.jpa.IdBaseModel;
import com.thoughtworks.gaia.user.entity.User;
import com.thoughtworks.gaia.user.model.UserModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PRODUCT")
public class ProductModel extends IdBaseModel {
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "time_created", nullable = false, updatable = false)
    private Date timeCreated;

    @ManyToOne(fetch=FetchType.LAZY)
    private UserModel user;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }
}
