package org.minhtc.and103.ui.order;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.minhtc.and103.data.dto.OrderDetail;

import java.util.ArrayList;
import java.util.List;
public class CreateOrderViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<OrderDetail>> orderDetails = new MutableLiveData<>();
    private MutableLiveData<Double> totalAmount = new MutableLiveData<>(0.0);
    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails.setValue(orderDetails);
    }
    public MutableLiveData<List<OrderDetail>> getOrderDetails() {
        return orderDetails;
    }
    public MutableLiveData<Double> getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount.setValue(totalAmount);
    }
    public void clearTotalAmount() {
        this.totalAmount.setValue(0.0);
    }
    public void clearOrderDetails() {
        this.orderDetails.getValue().clear();
    }
}