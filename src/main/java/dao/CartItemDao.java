package dao;

import model.CartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartItemDao {
     List<CartItem> mycart(@Param("uid") String uid);

    void careitemInsert(@Param("cartItem")CartItem cartItem,@Param("bid")String bid);

    List<CartItem> loadCartItems(@Param("cartItemIds")List<String> cartItemIds);

    void updateCart(@Param("quantity")int quantity,@Param("bid")String bid);

    int selectCartbybid(String bid);

    void updateCartbytextbox(@Param("cartItemId")String cartItemId, @Param("quantity")int quantity);

    CartItem updateQuantitySelect(@Param("cartItemId")String cartItemId);
}
