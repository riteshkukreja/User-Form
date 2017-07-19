/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mettl.learning.userform.dao;

import java.util.List;

/**
 *
 * @author admin
 */
public interface DataAccessInterface {
    
    public Object get(int id) throws Exception;
    public List<Object> getAll();
    public void update(int id, Object data) throws Exception;
    public int add(Object data) throws Exception;
    public void add(int id, Object data) throws Exception;
    public void delete(int id) throws Exception;
    
}
