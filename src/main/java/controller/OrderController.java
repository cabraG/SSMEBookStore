package controller;

import Utils.CommonUtils;
import Utils.PageBean;
import model.CartItem;
import model.Order;
import model.OrderItem;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.CartItemService;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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


    @Autowired
    private CartItemService cartItemService;

    //我的订单
    @RequestMapping(value = "/myOrder")
    public String myOrder(Map<String, Object> map,@RequestParam(name = "pc",required=false)String pc){
       User user=(User)request.getSession().getAttribute("sessionUser");
        PageBean<Order> pb=orderService.myOrder(user.getUid(),pc);
        pb.setUrl(getUrl(request));
       map.put("pb",pb);


        return "jsps/order/list";

    }

//订单创建添加
    @RequestMapping(value = "/OrderInit")
    public String OrderInit(@RequestParam(name = "cartItemIds")String cartItemIds,@RequestParam(name = "songaddress")String songaddress,Map<String, Object> map){

      List<CartItem> cartItemList= cartItemService.loadCartItems(cartItemIds);
        if(cartItemList.size() == 0) {
            map.put("code", "error");
            map.put("msg", "您没有选择要购买的图书，不能下单！");
            return "/jsps/msg";
        }

        /* 2. 创建Order
                */
        Order order = new Order();
        order.setOid(CommonUtils.uuid());//设置主键
        order.setOrdertime(String.format("%tF %<tT", new Date()));//下单时间
        order.setStatus(1);//设置状态，1表示未付款
        order.setAddress(songaddress);//设置收货地址
        User owner = (User)request.getSession().getAttribute("sessionUser");
        order.setOwner(owner);//设置订单所有者

        BigDecimal total = new BigDecimal("0");
        for(CartItem cartItem : cartItemList) {
            total = total.add(new BigDecimal(cartItem.getSubtotal() + ""));
        }
        order.setTotal(total.doubleValue());//设置总计

        /*
         * 3. 创建List<OrderItem>
         * 一个CartItem对应一个OrderItem
         */
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        for(CartItem cartItem : cartItemList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderItemId(CommonUtils.uuid());//设置主键
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setSubtotal(cartItem.getSubtotal());
            orderItem.setBook(cartItem.getBook());
            orderItem.setOrder(order);
            orderItemList.add(orderItem);
        }
        order.setOrderItemList(orderItemList);

        /*
         * 4. 调用service完成添加
         */
        orderService.createOrder(order);


        /*
         * 5. 从购物车删除购物项
         */
        cartItemService.batchDelete(cartItemIds);
        map.put("order",order);

        return "/jsps/order/ordersucc";
    }


    //订单查看取消确认收货等操作界面跳转
    @RequestMapping(value = "loadOrder")
    public String loadOrder(@RequestParam(name = "oid")String oid,@RequestParam(name = "btn",required=false)String btn,Map<String, Object> map) {

            map.put("order", orderService.loadOrder(oid));
            map.put("btn",btn);


            return "/jsps/order/desc";





    }
//暂时的支付跳转-既改变订单状态
    @RequestMapping(value = "paymentPre")
    public String paymentPre(@RequestParam(name = "oid")String oid){
            orderService.paymentPre(oid);

        return "forward:/myOrder";
    }



    @RequestMapping(value = "cancelorder")
    public String cancelorder(@RequestParam(name = "oid")String oid,Map<String, Object> map){
        Order order= orderService.loadOrder(oid);
        if(order.getStatus()!= 1) {
            map.put("code", "error");
            map.put("msg", "状态不对，不能取消！");
            return "/jsps/msg";
        }
        orderService.updateStatus(oid, 5);//设置状态为取消！
        map.put("code", "success");
        map.put("msg", "您的订单已取消，您不后悔吗！");
        return "/jsps/msg";
    }


    @RequestMapping(value = "confirmorder")
    public String confirmorder(@RequestParam(name = "oid")String oid,Map<String, Object> map) {

        Order order = orderService.loadOrder(oid);
        if (order.getStatus()!=3) {
            map.put("code", "error");
            map.put("msg", "状态不对，不能确认收货！");
            return "/jsps/msg";
        }
        orderService.updateStatus(oid, 4);//设置状态为取消！
        map.put("code", "success");
        map.put("msg", "恭喜，交易成功！");
        return "/jsps/msg";


    }

}
