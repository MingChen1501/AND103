package org.minhtc.and103.data.payload.order;
import java.util.List;
public class CreateOrderRequest {
    private String userId;
    private String payment_method;
    private String cart_id;
    private List<String> cart_item_ids;
    private String type;
    private Double total_amount;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(String userId, String payment_method, String cart_id, List<String> cart_item_ids, String type, Double total_amount) {
        this.userId = userId;
        this.payment_method = payment_method;
        this.cart_id = cart_id;
        this.cart_item_ids = cart_item_ids;
        this.type = type;
        this.total_amount = total_amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public List<String> getCart_item_ids() {
        return cart_item_ids;
    }

    public void setCart_item_ids(List<String> cart_item_ids) {
        this.cart_item_ids = cart_item_ids;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    @Override
    public String toString() {
        return "CreateOrderRequest{" +
                "userId='" + userId + '\'' +
                ", payment_method='" + payment_method + '\'' +
                ", cart_id='" + cart_id + '\'' +
                ", cart_item_ids=" + cart_item_ids +
                ", type='" + type + '\'' +
                ", total_amount=" + total_amount +
                '}';
    }
}
