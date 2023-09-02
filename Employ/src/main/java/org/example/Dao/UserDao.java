package org.example.Dao;

import org.example.Entity.User;
import org.example.Entity.UserRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: He Zhiyang
 * @Date: 2023/04/13/20:40
 * @Description:
 */
@Repository("userMySQL")
public class UserDao implements UserData{
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




    @Override
    public User findUserByUserName(String username) {

        try {
            final String sql="select * from user where username=?";
            User name = jdbcTemplate.queryForObject(sql, new UserRowmapper(), username );
            return name;
        }catch (Exception e){
            return  null;
        }


    }



    @Override
    public User findUserByUserNameAndPwd(String username,String password) {
        final String sql="select * from user where username=? and  password=?";
        User users = jdbcTemplate.queryForObject(sql, new UserRowmapper(), username,password );
        return users;
    }

    @Override
    public List<User> insertUser(String username, String password) {
        final String sql = "insert into user(username,password) value (?,?)";
        int result=jdbcTemplate.update(sql,username,password);

        if (result > 0) {
            System.out.println("信息添加成功");
        }else {
            System.out.println("插入失败");
        }
        return null;
    }


}
