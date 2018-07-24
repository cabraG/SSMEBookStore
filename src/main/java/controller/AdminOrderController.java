package controller;

import Utils.PageBean;
import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {

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
    private HttpServletRequest request;

    @RequestMapping()
    public String toadminorderindex(Map<String, Object> map,@RequestParam(name = "pc",required=false)String pc){
       PageBean<Order> orderPageBean= orderService.findall(pc);
        orderPageBean.setUrl(getUrl(request));
        map.put("pb",orderPageBean);
        return "adminjsps/admin/order/list";
    }
@RequestMapping(value = "/msg")
    public String toadminmsg(){
        return "adminjsps/msg";
}

}
