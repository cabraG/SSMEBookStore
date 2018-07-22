package service;

import Utils.CommonUtils;
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

    public void insertUser(User user) {
        user.setUid(CommonUtils.uuid());
        user.setStatus(true);
        user.setActivationCode(CommonUtils.uuid() + CommonUtils.uuid());
        userDao.insertUser(user);
    }

    public boolean selectLoginname(String loginname) {
        if(userDao.selectLoginname(loginname)==null){
            return false;
        }
        else
            return true;

    }

    public boolean selectemail(String email) {
        if(userDao.selectemail(email)==null){
            return false;
        }
        else
            return true;
    }
}
