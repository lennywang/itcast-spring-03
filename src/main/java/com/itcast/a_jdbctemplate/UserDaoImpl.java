package com.itcast.a_jdbctemplate;

import com.itcast.bean.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.test.context.jdbc.Sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 **/
//extends JdbcDaoSupport
public class UserDaoImpl  implements UserDao {

    private JdbcTemplate jt;

    public void save(User user) {
        String sql = "INSERT INTO t_user VALUES(NULL,?)";
        jt.update(sql, user.getName());
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM t_user WHERE id=?";
        jt.update(sql, id);
    }

    public void update(User user) {
        String sql = "UPDATE t_user SET name=? WHERE id=?";
        jt.update(sql, user.getName(), user.getId());
    }

    public User find(Integer id) {
        String sql = "SELECT * FROM t_user WHERE id =?";
        return jt.queryForObject(sql, new RowMapper<User>() {
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User u = new User();
                u.setId(resultSet.getInt("id"));
                u.setName(resultSet.getString("name"));
                return u;
            }
        }, id);
    }

    public int getTota() {
        String sql = "SELECT count(*) FROM t_user";
        Integer integer = jt.queryForObject(sql, Integer.class);
        return integer;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM t_user";
        List<User> list = jt.query(sql, new RowMapper<User>() {

            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User u = new User();
                u.setId(resultSet.getInt("id"));
                u.setName(resultSet.getString("name"));
                return u;
            }
        });
        return list;
    }

    public void setJt(JdbcTemplate jt) {
        this.jt = jt;
    }
}
