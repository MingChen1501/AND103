package org.minhtc.and103.data.model;

import java.util.List;

public class Product {
    private String _id;
    private String name;
    private String description;
    private double price;
    private String brand;
    private List<String> categories;
    private List<String> images;
    private List<ProductDetail> details;

    public Product(String _id,
                   String name,
                   String description,
                   double price,
                   String brand,
                   List<String> categories,
                   List<String> images,
                   List<ProductDetail> details) {
        this._id = _id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.categories = categories;
        this.images = images;
        this.details = details;
    }

    public Product() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<ProductDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ProductDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Product{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", categories=" + categories +
                ", images=" + images +
                ", details=" + details +
                '}';
    }
}
