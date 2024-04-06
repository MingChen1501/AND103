package org.minhtc.and103.data.model;
import java.util.List;
public class Cart {
    private String _id;
    private String cartName;
    private String userId;
    private List<CartItem> items;

    public Cart() {
    }

    public Cart(String _id, String cartName, String userId, List<CartItem> items) {
        this._id = _id;
        this.cartName = cartName;
        this.userId = userId;
        this.items = items;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
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
                ", cartName='" + cartName + '\'' +
                ", userId='" + userId + '\'' +
                ", items=" + items +
                '}';
    }
}
