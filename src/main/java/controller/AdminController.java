package controller;

import model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.AdminService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;


    @Autowired
    HttpServletRequest request;

    @RequestMapping()
    public String toadminindex(){
        return "adminjsps/admin/index";
    }


    @RequestMapping(value = "/login")
    public String adminlogin(Admin admin,Map<String, Object> map){
        Admin sessionadmin=adminService.checkAdminLogin(admin);
       if(sessionadmin!=null){
           request.getSession().setAttribute("admin",sessionadmin);
           return "forward:/admin";
       }
        else{
           map.put("code", "error");
           map.put("msg", "错了朋友");
           return "adminjsps/msg";}

    }
    @RequestMapping(value = "/exit")
    public String exit(){

        Object obj=request.getSession().getAttribute("admin");
        if(obj!=null){
            request.getSession().removeAttribute("admin");
        }

            return "adminjsps/login";
    }

@RequestMapping(value = "/msg")
    public String toadminmsg(){
        return "adminjsps/msg";
}

}
