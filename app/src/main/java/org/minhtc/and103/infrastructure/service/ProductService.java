package org.minhtc.and103.infrastructure.service;

import org.minhtc.and103.data.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {
    @GET("products")
    Call<List<Product>> getProducts();
}
