package service;

import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userService")
public class UserService
{


    @Autowired
    UserDao userDao;

    public User userlogin(String loginname, String loginpass) {
        return userDao.userlogin(loginname,loginpass);
    }
}
