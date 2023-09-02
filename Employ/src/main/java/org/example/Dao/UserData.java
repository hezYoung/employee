package org.example.Dao;

import org.example.Entity.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: He Zhiyang
 * @Date: 2023/04/13/20:43
 * @Description:
 */

public interface UserData {
  User findUserByUserName(String username);

    User findUserByUserNameAndPwd(String username,String password);
    List<User> insertUser(String username,String password);
//   List<User> selectAllEmployee();
}
