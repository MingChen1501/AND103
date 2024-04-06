package org.minhtc.and103.data.dto;

import java.util.Objects;

public class OrderDetail {
    private String productId;
    private String productDetailId;
    private String cartItemId;
    private String productName;
    private String color;
    private String size;
    private int quantity;
    private double price;
    private double totalPrice;

    public OrderDetail() {
    }

    public OrderDetail(String productId, String productDetailId, String cartItemId, String productName, String color, String size, int quantity, double price, double totalPrice) {
        this.productId = productId;
        this.productDetailId = productDetailId;
        this.cartItemId = cartItemId;
        this.productName = productName;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "productId='" + productId + '\'' +
                ", productDetailId='" + productDetailId + '\'' +
                ", productName='" + productName + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return Objects.equals(productId, that.productId) && Objects.equals(productDetailId, that.productDetailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productDetailId);
    }
}
