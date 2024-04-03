package org.minhtc.and103.data.model;

public class ProductDetails {
    private String _id;
    private String color;
    private String size;
    private int stock;
    private double price;

    public ProductDetails(String _id, String color, String size, int stock, double price) {
        this._id = _id;
        this.color = color;
        this.size = size;
        this.stock = stock;
        this.price = price;
    }

    public ProductDetails() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
                "_id='" + _id + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                '}';
    }
}
