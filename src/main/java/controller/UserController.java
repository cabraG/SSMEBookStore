package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;
//基本登录逻辑
    @RequestMapping(value = "/userlogin")
    public String userlogin(Map<String, Object> map, User user){

        User fromuser =userService.userlogin(user.getLoginname(),user.getLoginpass());
        if(fromuser==null){
            map.put("msg","用户名或密码错误");
            map.put("user",user);
            return "jsps/user/login";
        }
else{
            request.getSession().setAttribute("sessionUser",fromuser);
            return "redirect:/index.jsp";
        }


    }
//ajax验证验证码是否正确
    @RequestMapping(value = "ajaxValidateVerifyCode", method = RequestMethod.POST)
    @ResponseBody
    public boolean ajaxValidateVerifyCode(HttpServletRequest request, HttpServletResponse response){
        String verifyCode = request.getParameter("verifyCode");
        String vcode = (String) request.getSession().getAttribute("vCode");
        return verifyCode.equalsIgnoreCase(vcode);

    }
//注册用户
    @RequestMapping(value = "/insertUser")
    public String insertUser(User user){
        userService.insertUser(user);

        return "redirect:/jsps/user/login.jsp";

    }
//退出登录
    @RequestMapping(value = "/quituser")
    public String quituser(){
        request.getSession().removeAttribute("sessionUser");
        return "redirect:/index.jsp";

    }

//ajax验证用户名是否注册
    @RequestMapping(value = "ajaxValidateLoginname", method = RequestMethod.POST)
    @ResponseBody
    public boolean ajaxValidateLoginname(HttpServletRequest request, HttpServletResponse response){
        String loginname = request.getParameter("loginname");

        return userService.selectLoginname(loginname);

    }

    //ajax验证邮箱是否注册

    @RequestMapping(value = "validateEmail", method = RequestMethod.POST)
    @ResponseBody
    public boolean validateEmail(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        return userService.selectemail(email);
    }




}
