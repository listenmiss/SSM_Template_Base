package cn.com.catcher.vision.affairs.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.catcher.vision.affairs.model.User;

public interface UserService {

    public User findUserById(int id);

    public List<User> findUserList();

    public int addUser(User user);

    public int updateUser(User user);

    public int delUser(@Param("id") int id);
    
    public User checkLogin(String uname,String pwd);
}