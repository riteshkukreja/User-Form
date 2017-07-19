/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mettl.learning.userform.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author admin
 */
@Component("nonPersistantDataAccess")
public class NonPersistantDataAccess implements DataAccessInterface {
    
    private static Map map = new HashMap();
    private static int ID = 1;

    @Override
    public Object get(int id) throws Exception {
        if(map.containsKey(id)) {
            return map.get(id);
        }
        
        throw new Exception("Requested user doesn't exists");
    }

    @Override
    public List<Object> getAll() {
        return new ArrayList(map.values());
    }

    @Override
    public void update(int id, Object data) throws Exception {
        if(map.containsKey(id)) {
           map.put(id, data);
        } else {
            throw new Exception("Requested user doesn't exists");
        }       
    }

    @Override
    public int add(Object data) throws Exception {
        while(map.containsKey(ID)) ID++;
        System.out.println(ID);
        
        if(!map.containsValue(data)) {
            map.put(ID, data);
            return ID++;
        }
        
        throw new Exception("User already exists");
    }

    @Override
    public void add(int id, Object data) throws Exception {
        map.put(id, data);
    }

    @Override
    public void delete(int id) throws Exception {
        if(map.containsKey(id)) {
           map.remove(id);
        } else {
            throw new Exception("Requested user doesn't exists");
        }
    }
    
}
