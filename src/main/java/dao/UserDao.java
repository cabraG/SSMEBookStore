package dao;

import model.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    User userlogin(@Param("loginname")String loginname, @Param("loginpass")String loginpass);
}
