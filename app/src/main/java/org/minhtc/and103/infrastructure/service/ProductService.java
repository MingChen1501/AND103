package org.minhtc.and103.infrastructure.service;

import org.minhtc.and103.data.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductService {
    @GET("products")
    Call<List<Product>> getProducts();
    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") String productId);
}
