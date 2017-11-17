package cn.com.catcher.vision.affairs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.catcher.vision.affairs.controller.UserController;
import cn.com.catcher.vision.affairs.dao.UserDao;
import cn.com.catcher.vision.affairs.model.User;
import cn.com.catcher.vision.affairs.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
    UserDao userDao;
    
    @Override
    public User findUserById(int id) {
    	logger.info("findUserById:" + id);
        return this.userDao.findUserById(id);
    }
    
    @Override
    public List<User> findUserList() {
        return this.userDao.findUserList();
    }

    @Override
    public int addUser(User user) {
        return this.userDao.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return this.userDao.updateUser(user);
    }

    @Override
    public int delUser(int id) {
        // TODO Auto-generated method stub
        return this.userDao.delUser(id);
    }

	@Override
	public User checkLogin(String uname, String pwd) {
		// TODO Auto-generated method stub
		User u = userDao.findUserByName(uname);
		if(u==null)
		{
			return null;
		}
		else if(!pwd.equals(u.getPassword()))
		{
			return null;
		}
		return u;
	}


}