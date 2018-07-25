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


    @RequestMapping(value = "adminloadOrder")
    public String loadOrder(@RequestParam(name = "oid")String oid,@RequestParam(name = "btn",required=false)String btn,Map<String, Object> map) {

        map.put("order", orderService.loadOrder(oid));
        map.put("btn",btn);


        return "adminjsps/admin/order/desc";





    }
    @RequestMapping(value = "findByStatus")
  public String findByStatus(@RequestParam("status")String status,@RequestParam(name = "pc",required=false)String pc,Map<String, Object> map){



        PageBean<Order> orderPageBean= orderService.findByStatus(status,pc);
        orderPageBean.setUrl(getUrl(request));
        map.put("pb",orderPageBean);
        return "adminjsps/admin/order/list";

    }


    @RequestMapping(value = "deliver")
    public String deliver(@RequestParam(name = "oid")String oid,Map<String, Object> map) {

        Order order = orderService.loadOrder(oid);
        if (order.getStatus()!=2) {
            map.put("code", "error");
            map.put("msg", "状态不对，不能发货！");
            return "adminjsps/msg";
        }
        orderService.updateStatus(oid, 3);//设置状态为取消！
        map.put("code", "success");
        map.put("msg", "恭喜，交易成功！");
        return "adminjsps/msg";


    }

    @RequestMapping(value = "admincancel")
    public String admincancel(@RequestParam(name = "oid")String oid,Map<String, Object> map){
        Order order= orderService.loadOrder(oid);
        if(order.getStatus()!= 1) {
            map.put("code", "error");
            map.put("msg", "状态不对，不能取消！");
            return "adminjsps/msg";
        }
        orderService.updateStatus(oid, 5);//设置状态为取消！
        map.put("code", "success");
        map.put("msg", "您的订单已取消，您不后悔吗！");
        return "adminjsps/msg";
    }



}
