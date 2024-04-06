package org.minhtc.and103.data.model;

public class CartItem {
    private String _id;
    private String productId;
    private String productDetailId;
    private int quantity;

    public CartItem() {
    }

    public CartItem(String _id, String productId, String productDetailId, int quantity) {
        this._id = _id;
        this.productId = productId;
        this.productDetailId = productDetailId;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(String productDetailId) {
        this.productDetailId = productDetailId;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "_id='" + _id + '\'' +
                ", productId='" + productId + '\'' +
                ", productDetailId='" + productDetailId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
