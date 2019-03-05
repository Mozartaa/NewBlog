package com.itlike.service.impl;

import com.itlike.dao.UserDao;
import com.itlike.domain.User;
import com.itlike.service.LoginService;
import net.bytebuddy.dynamic.loading.ClassInjector;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class LoginServiceImpl implements LoginService {
    //×¢Èëdao²ã
    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(User user) {
//        System.out.println("LoginServiceImpl"+user);
        User resUser=userDao.getUser(user.getUsername(),user.getPassword());
        return resUser;
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list=userDao.getAllUsers();
        return list;

    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }


}
