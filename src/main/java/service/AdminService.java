package service;

import dao.AdminDao;
import model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("adminService")
public class AdminService {

    @Autowired
    AdminDao adminDao;


    public Admin checkAdminLogin(Admin admin) {
        return adminDao.checkAdminLogin(admin);
    }
}
