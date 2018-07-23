package controller;

import Utils.PageBean;
import model.Order;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class OrderController {


    private String getUrl(HttpServletRequest req) {
        String url = req.getRequestURI() + "?" + req.getQueryString();
        /*
         * 如果url中存在pc参数，截取掉，如果不存在那就不用截取。
         */
        int index = url.lastIndexOf("&pc=");
        if(index != -1) {
            url = url.substring(0, index);
        }
        return url;
    }

    @Autowired
    private OrderService orderService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/myOrder")
    public String myOrder(Map<String, Object> map,@RequestParam(name = "pc",required=false)String pc){
       User user=(User)request.getSession().getAttribute("sessionUser");
        PageBean<Order> pb=orderService.myOrder(user.getUid(),pc);
        pb.setUrl(getUrl(request));
       map.put("pb",pb);


        return "jsps/order/list";

    }
}
