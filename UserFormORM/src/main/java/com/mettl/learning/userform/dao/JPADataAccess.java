/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mettl.learning.userform.dao;

import com.mettl.learning.userform.models.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Controller;

/**
 *
 * @author admin
 */
@Controller
@Transactional
public class JPADataAccess implements DataAccessInterface{

    @PersistenceContext
    EntityManager em;
    
    @Override
    public User get(int id) throws Exception {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void update(int id, User data) throws Exception {
        data.setID(id);
        em.merge(data);
    }

    @Override
    public int add(User data) throws Exception {
        em.persist(data);
        return data.getID();
    }

    @Override
    public void delete(int id) throws Exception {
        User user = em.find(User.class, id);
        em.remove(user);
    }
    
}
