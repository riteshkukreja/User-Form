/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mettl.learning.userform.dao;

import com.mettl.learning.userform.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */

@Component
public class SQLDataAccess implements DataAccessInterface {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    private final RowMapper userMapper = new RowMapper<User>() {
           @Override
           public User mapRow(ResultSet rs, int rowNum) throws SQLException {
               User user = new User();
               user.setID(rs.getInt(1));
               user.setName(rs.getString(2));
               user.setAge(rs.getInt(3));
               user.setSex(rs.getString(4));
               user.setAddress(rs.getString(5));
               
               return user;
           } 
        };

    @Override
    public User get(int id) throws Exception {
        String GET_URL = "SELECT * FROM users WHERE id=" + id;
        return (User) jdbcTemplate.queryForObject(GET_URL, userMapper);
    }

    @Override
    public List<User> getAll() {
        String GET_URL = "SELECT * FROM users";
        List<Object> ll = jdbcTemplate.query(GET_URL, userMapper);
        
        List<User> users = new ArrayList<>();
        for(Object user: ll) {
            users.add((User) user);
        }
        
        return users;
    }

    @Override
    public void update(int id, User data) throws Exception {
        String UPDATE_URL = "UPDATE users SET name=?, age=?, sex=?, address=? WHERE id=?";
        jdbcTemplate.update(UPDATE_URL, new Object[]{data.getName(), data.getAge(), data.getSex(), data.getAddress(), id});
    }

    @Override
    public int add(final User data) throws Exception {
        final String INSERT_URL = "INSERT INTO users(name, age, sex, address) VALUES(?, ?, ?, ?)";
        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection cnctn) throws SQLException {
                PreparedStatement ps = cnctn.prepareStatement(INSERT_URL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, data.getName());
                ps.setInt(2, data.getAge());
                ps.setString(3, data.getSex());
                ps.setString(4, data.getAddress());
                return ps;
            }
        }, holder);
        
        return holder.getKey().intValue();
    }

    @Override
    public void delete(int id) throws Exception {
        String DELETE_URL = "DELETE FROM users WHERE id=?";
        jdbcTemplate.update(DELETE_URL, new Object[]{id});
    }
    
    @Transactional
    @Override
    public void addAll(List<User> data) throws Exception {
        for(User user: data) {
            if(user.getID() == 0) {
                int id = add(user);
                user.setID(id);
            } else
                update(user.getID(), user);
        }
    }
    
}
