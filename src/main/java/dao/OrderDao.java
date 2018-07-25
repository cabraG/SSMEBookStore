package dao;

import model.Order;
import model.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {
    int getmyOrderCount(@Param("uid")String uid);

    List<Order> myOrder(@Param("uid")String uid, @Param("pc")int pc, @Param("ps")int ps);

    void createOrder(@Param("order")Order order);

    void setOrderItem(@Param("orderItemList")List<OrderItem> orderItemList);

    Order loadOrder(@Param("oid")String oid);

    void paymentPre(@Param("oid")String oid);

    void updateStatus(@Param("oid")String oid, @Param("status")int status);

    int findallcount();

    List<Order> findall(@Param("pc")int pc, @Param("ps")int ps);

    List<Order> findByStatus(@Param("status")String status,@Param("pc")int pc, @Param("ps")int ps);
}

