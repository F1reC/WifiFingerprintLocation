package cn.shoppingguide.service;


import cn.shoppingguide.dao.UserDao;
import cn.shoppingguide.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User selectUser(String username, String password){

        User user = userDao.findByUsernameAndPassword(username, password);

        return user;
    }

    public User isexist(String username){

        User user = userDao.isexist(username);

        return user;
    }

    public boolean updatePassword(String username,
                               String newPassword){
        boolean b = userDao.updatePassword(username, newPassword);
        return b;
    }

    public boolean insertUser(String username,
                              String password){
        boolean b = userDao.insertUser(username, password);
        return b;
    }

    public boolean deleteUser(String username){
        boolean b = userDao.deleteUser(username);
        return b;
    }
}
