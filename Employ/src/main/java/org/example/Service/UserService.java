package org.example.Service;

import org.example.Dao.UserDao;
import org.example.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: He Zhiyang
 * @Date: 2023/04/13/21:02
 * @Description:
 */
@Service
public class UserService {
    private UserDao userDao;
    @Autowired

    public UserService(@Qualifier("userMySQL") UserDao userDao ) {

        this.userDao = userDao;
    }
    public User selename(String username) {

        return userDao.findUserByUserName(username);


    }

    public User selelogin(String username,String password) {

        return userDao.findUserByUserNameAndPwd( username,password);


    }
    public List<User> insertUser(String username,String password) {

        return userDao.insertUser(username, password);


    }

}
