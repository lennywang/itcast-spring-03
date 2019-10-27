package com.itcast.a_jdbctemplate;

import com.itcast.bean.User;

import java.util.List;

/**
 *
 **/
public interface UserDao {
    void save(User user);
    void delete(Integer id);
    void update(User user);
    User find(Integer id);
    int getTota();
    List<User> findAll();
}
