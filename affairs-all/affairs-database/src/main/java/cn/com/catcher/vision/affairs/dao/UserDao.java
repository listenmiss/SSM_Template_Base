package cn.com.catcher.vision.affairs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.catcher.vision.affairs.model.User;

public interface UserDao {

    public User findUserById(@Param("id") int id);

    public List<User> findUserList();

    public int addUser(User user);

    public int updateUser(User user);

    public int delUser(@Param("id") int id);
    
    public User findUserByName(@Param("name") String name);
    

}