package controller;

import Utils.VerifyCode;
import model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@Controller
public class UtilController {

    @Autowired
    private VerifyCode verifyCode;

    @Autowired
    private AdminService adminService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping("/VerifyCodeCreate")
    public void VerifyCodeCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedImage image = verifyCode.getImage();
        VerifyCode.output(image, response.getOutputStream());
        request.getSession().setAttribute("vCode", verifyCode.getText());

    }

    @RequestMapping(value = "/adminlogin")
    public String adminlogin(Admin admin, Map<String, Object> map){
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


}
