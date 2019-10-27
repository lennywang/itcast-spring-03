package com.itcast.a_jdbctemplate;

import com.itcast.bean.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.beans.PropertyVetoException;

/**
 *
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Demo {

    @Resource(name = "userDao")
    private UserDao userDao;

    @Test
    public void fun1() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("");

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(comboPooledDataSource);

        String sql ="insert into t_user values(null,'rose')";
        jdbcTemplate.update(sql);
    }

    @Test
    public void fun2(){
        User user = new User();
        user.setName("tom");
        userDao.save(user);
    }

    @Test
    public void fun3(){
        User user = new User();
        user.setId(2);
        user.setName("jack");
        userDao.update(user);
    }

    @Test
    public void fun4()
    {
        userDao.delete(2);
    }

    @Test
    public void fun5(){
        System.out.println(userDao.getTota());
    }

    @Test
    public void fun6(){
        System.out.println( userDao.find(1));
    }

    @Test
    public void fun7()
    {
        System.out.println(userDao.findAll());
    }

}
