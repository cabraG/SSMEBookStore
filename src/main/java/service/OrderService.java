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

    public void createOrder(Order order) {

         orderDao.createOrder(order);
         orderDao.setOrderItem(order.getOrderItemList());
    }

    public Order loadOrder(String oid) {
        return orderDao.loadOrder(oid);
    }

    public void paymentPre(String oid) {
        orderDao.paymentPre(oid);

    }

    public void updateStatus(String oid, int i) {
        orderDao.updateStatus(oid,i);
    }

    public PageBean<Order> findall(String pc) {

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
        orderPageBean.setTr(orderDao.findallcount());
        orderPageBean.setBeanList(orderDao.findall((thispc-1)*8,8));

        return orderPageBean;

    }

    public PageBean<Order> findByStatus(String status,String pc) {


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
        orderPageBean.setTr(orderDao.findallcount());
        orderPageBean.setBeanList(orderDao.findByStatus(status,(thispc-1)*8,8));

        return orderPageBean;
    }
}
