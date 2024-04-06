package org.minhtc.and103.ui.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.minhtc.and103.data.model.Cart;
import org.minhtc.and103.infrastructure.service.CartService;

public class CartViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public static final String TAG = "CartViewModel";
    private MutableLiveData<Cart> cartData = new MutableLiveData<>();
    public void setCart(Cart cart) {
        cartData.setValue(cart);
    }
    public LiveData<Cart> getCart() {
        return cartData;
    }
}