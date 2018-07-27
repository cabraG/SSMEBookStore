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
