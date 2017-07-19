/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mettl.learning.userform.dao;

import com.mettl.learning.userform.models.User;
import java.util.List;

/**
 *
 * @author admin
 */
public interface DataAccessInterface {
    
    public User get(int id) throws Exception;
    public List<User> getAll();
    public void update(int id, User data) throws Exception;
    public int add(User data) throws Exception;
    public void delete(int id) throws Exception;
    
}
