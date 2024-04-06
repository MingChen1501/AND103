package org.minhtc.and103.data.payload.cart;
import org.minhtc.and103.data.model.CartItem;

import java.util.List;

public class CartResponse {
    private String _id;
    private String userId;
    private List<CartItem> items;

    public CartResponse() {
    }

    public CartResponse(String _id, String userId, List<CartItem> cartItems) {
        this._id = _id;
        this.userId = userId;
        this.items = cartItems;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "_id='" + _id + '\'' +
                ", userId='" + userId + '\'' +
                ", cartItems=" + items +
                '}';
    }
}
