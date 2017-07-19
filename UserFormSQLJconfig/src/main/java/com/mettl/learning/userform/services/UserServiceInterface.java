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

/**
 *
 * @author admin
 */
public interface UserServiceInterface {
    
    public User get(int userId) throws Exception;    
    
    public List<User> getAll();
    
    public int create(User user) throws Exception;
    
    public void upload(List<User> users) throws Exception;
    
    public void update(int userId, User user) throws Exception;
    
    public void delete(int userId) throws Exception;
}
