package service;

import Utils.CommonUtils;
import dao.CartItemDao;
import model.CartItem;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
@Transactional
@Service("cartItemService")
public class CartItemService {

    @Autowired
    CartItemDao cartItemDao;

    public List<CartItem> mycart(String uid) {


        return cartItemDao.mycart(uid);

    }

    public void cartiteminit(CartItem cartItem,String bid) {
        cartItem.setCartItemId(CommonUtils.uuid());
        if(cartItemDao.selectCartbybid(bid)==0)
        cartItemDao.careitemInsert(cartItem,bid);
        else
            cartItemDao.updateCart(cartItem.getQuantity(),bid);
    }

    public List<CartItem> loadCartItems(String cartItemIds) {

         String[] cartItemIdArray = cartItemIds.split(",");

        return cartItemDao.loadCartItems(Arrays.asList(cartItemIdArray));
    }

    public CartItem updateQuantity(String cartItemId, int quantity) {

            cartItemDao.updateCartbytextbox(cartItemId,quantity);

           return cartItemDao.updateQuantitySelect(cartItemId);

    }
}
