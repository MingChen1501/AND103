package org.minhtc.and103.ui.cart;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.minhtc.and103.data.dto.OrderDetail;
import org.minhtc.and103.data.model.Cart;

import java.util.List;
public class CartViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public static final String TAG = "CartViewModel";
    private final MutableLiveData<Cart> cartData = new MutableLiveData<>();
    private final MutableLiveData<List<OrderDetail>> orderDetails = new MutableLiveData<>();
    private final MutableLiveData<Double> totalAmount = new MutableLiveData<>(0.0);
    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails.setValue(orderDetails);
    }
    public LiveData<List<OrderDetail>> getOrderDetails() {
        return orderDetails;
    }
    public void setCart(Cart cart) {
        cartData.setValue(cart);
    }
    public LiveData<Cart> getCart() {
        return cartData;
    }
    public LiveData<Double> getTotalAmount() {
        return totalAmount;
    }
    private void setTotalAmount(Double totalAmount) {
        this.totalAmount.setValue(totalAmount);
        Log.d(TAG, "setTotalAmount: " + totalAmount);
    }
    public void increaseTotalAmount(Double totalAmount) {
        setTotalAmount(this.totalAmount.getValue() + totalAmount);
    }

    public void decreaseTotalAmount(Double totalAmount) {
        setTotalAmount(this.totalAmount.getValue() - totalAmount);
    }
    public void clearTotalAmount() {
        setTotalAmount(0.0);
    }
}