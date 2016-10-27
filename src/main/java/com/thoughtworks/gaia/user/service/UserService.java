package com.thoughtworks.gaia.user.service;

import com.google.common.collect.Lists;
import com.thoughtworks.gaia.common.Loggable;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import com.thoughtworks.gaia.product.ProductMapper;
import com.thoughtworks.gaia.product.entity.Product;
import com.thoughtworks.gaia.product.model.ProductModel;
import com.thoughtworks.gaia.user.UserMapper;
import com.thoughtworks.gaia.user.dao.UserDao;
import com.thoughtworks.gaia.user.entity.User;
import com.thoughtworks.gaia.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class UserService implements Loggable {
    @Autowired
    private UserMapper mapper;

    @Autowired
    private ProductMapper prodmapper;

    @Autowired
    private UserDao userDao;

    public User getUser(Long userId) {
        UserModel userModel = userDao.idEquals(userId).querySingle();
        if (userModel == null) {
            error("User not found with id: " + userId);
            throw new NotFoundException();
        }

        return mapper.map(userModel, User.class);
    }

    public User updateUser(UserModel updateUser) {


        return mapper.map(updateUser, User.class);
    }

    public void deleteUser(Long userId) {
        userDao.remove(userDao.idEquals(userId).querySingle());
    }

    public List<User> getUserList() {

        return Lists.newArrayList();
    }

    public List<User> getUserWithProdList() {

        return Lists.newArrayList();
    }
}
