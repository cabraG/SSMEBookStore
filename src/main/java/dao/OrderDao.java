package dao;

import model.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {
    int getmyOrderCount(@Param("uid")String uid);

    List<Order> myOrder(@Param("uid")String uid, @Param("pc")int pc, @Param("ps")int ps);
}

