package service;

import Utils.PageBean;
import dao.OrderDao;
import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("orderService")
public class OrderService {


    @Autowired
    private OrderDao orderDao;
    public PageBean<Order> myOrder(String uid,String pc) {


        int thispc =0;
        if(pc==null){
            thispc =1;
        }
        else{
            thispc=Integer.valueOf(pc).intValue();
        }
        PageBean<Order> orderPageBean=new PageBean<Order>();
        orderPageBean.setPc(thispc);
        orderPageBean.setPs(8);
        orderPageBean.setTr(orderDao.getmyOrderCount(uid));
        orderPageBean.setBeanList(orderDao.myOrder(uid,(thispc-1)*8,8));


        return orderPageBean;
    }
}
