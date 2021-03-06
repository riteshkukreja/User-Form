/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mettl.learning.userform.services;

import com.mettl.learning.userform.dao.DataAccessInterface;
import com.mettl.learning.userform.models.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author admin
 */

@Component("userService")
public class UserService implements UserServiceInterface {
    
    @Autowired
    private DataAccessInterface dataAccessInterface;
    
    
    @Override
    public User get(int userId) throws Exception {
        return (User) dataAccessInterface.get(userId);
    }
    
    @Override
    public int create(User user) throws Exception {
        int id = dataAccessInterface.add(user);
        user.setID(id);
        return id;
    }
    
    @Override
    public void update(int userId, User user) throws Exception {
        user.setID(userId);
        dataAccessInterface.update(userId, user);
    }
    
    @Override
    public void delete(int userId) throws Exception {
        dataAccessInterface.delete(userId);
    }

    @Override
    public List<User> getAll() {
        return dataAccessInterface.getAll();
    }

    @Override
    public void upload(List<User> users) throws Exception {
        dataAccessInterface.addAll(users);
    }
    
}
