package org.minhtc.and103.infrastructure.service;

import org.minhtc.and103.data.payload.order.CreateOrderRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrderService {
    @POST("orders")
    Call<Void> createOrder(@Body CreateOrderRequest createOrderRequest);
}
