package controller;

import Utils.PageBean;
import model.CartItem;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CartItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private  HttpServletRequest request;

//购物车添加
    @RequestMapping(value = "/cartiteminit")
    public String cartiteminit(CartItem cartItem,@RequestParam(name = "bid")String bid){
        User user=(User)request.getSession().getAttribute("sessionUser");
        cartItem.setUser(user);

        cartItemService.cartiteminit(cartItem,bid);


        return "forward:/mycart";
    }

    //我的购物车
@RequestMapping(value = "/mycart")
 public String mycart(Map<String, Object> map){
    User user= (User)request.getSession().getAttribute("sessionUser");
     map.put("cartItemList",cartItemService.mycart(user.getUid()));


        return "jsps/cart/list";
 }

 //订单准备
 @RequestMapping(value = "loadCartItems")
    public String loadCartItems(@RequestParam(name = "cartItemIds")String cartItemIds,@RequestParam(name = "total")String total,Map<String, Object> map){
        map.put("total",total);
        map.put("cartItemIds",cartItemIds);
        map.put("cartItemList",cartItemService.loadCartItems(cartItemIds));


        return "jsps/cart/showitem";
 }

//购物车页面内修改购买数量
    @RequestMapping(value = "updateQuantity", method = RequestMethod.POST)
    @ResponseBody
    public String updateQuantity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cartItemId = request.getParameter("mcartItemId");
        int quantity = Integer.valueOf(request.getParameter("quantity"));
        CartItem cartItem= cartItemService.updateQuantity(cartItemId, quantity);

        StringBuilder sb = new StringBuilder("{");
        sb.append("\"quantity\"").append(":").append(cartItem.getQuantity());
        sb.append(",");
        sb.append("\"subtotal\"").append(":").append(cartItem.getSubtotal());
        sb.append("}");

        response.getWriter().print(sb);
        return null;
    }

    //删除购物车项
@RequestMapping(value = "batchDelete")
    public String batchDelete(@RequestParam(name = "cartItemIds")String cartItemIds){
        cartItemService.batchDelete(cartItemIds);

        return "forward:/mycart";
}

}
