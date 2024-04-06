package org.minhtc.and103.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.minhtc.and103.data.model.Cart;
import org.minhtc.and103.infrastructure.service.CartService;
import org.minhtc.and103.infrastructure.service.RetrofitClient;
import java.util.List;
public class CartRepository {
    private static final String TAG = "CartRepository";
    private final CartService cartService;
    private final MutableLiveData<List<Cart>> cartLiveData = new MutableLiveData<>();
    public CartRepository() {
        cartService = RetrofitClient.getClient().create(CartService.class);
    }
    public LiveData<List<Cart>> getCarts(String userId) {
        cartService.getCart(userId).enqueue(new retrofit2.Callback<List<Cart>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Cart>> call, retrofit2.Response<List<Cart>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().toString());
                    cartLiveData.setValue(response.body());
                } else {
                    cartLiveData.setValue(null);
                }
            }
            @Override
            public void onFailure(retrofit2.Call<List<Cart>> call, Throwable t) {
                cartLiveData.setValue(null);
            }
        });
        return cartLiveData;
    }
}
