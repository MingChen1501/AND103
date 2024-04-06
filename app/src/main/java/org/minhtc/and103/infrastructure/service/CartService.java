package org.minhtc.and103.infrastructure.service;

import org.minhtc.and103.data.model.Cart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CartService {
    @GET("carts")
    Call<List<Cart>> getCart(@Query("userId") String userId);
}
