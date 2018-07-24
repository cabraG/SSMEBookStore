package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping()
    public String toadminindex(){
        return "adminjsps/admin/index";
    }
@RequestMapping(value = "/msg")
    public String toadminmsg(){
        return "adminjsps/msg";
}

}
