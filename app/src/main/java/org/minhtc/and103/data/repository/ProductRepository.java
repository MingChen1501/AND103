package org.minhtc.and103.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.minhtc.and103.data.model.Product;
import org.minhtc.and103.infrastructure.service.ProductService;
import org.minhtc.and103.infrastructure.service.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {
    private static final String TAG = "ProductRepository";
    private final ProductService productService;

    public ProductRepository() {
        productService = RetrofitClient.getClient().create(ProductService.class);
    }
    public LiveData<List<Product>> getProducts() {
        MutableLiveData<List<Product>> productLiveData = new MutableLiveData<>();

        productService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.d(TAG, "onResponse: " + response.body().size());
                if (response.isSuccessful()) {
                    productLiveData.setValue(response.body());
                } else {
                    productLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                productLiveData.setValue(null);
            }
        });
        return productLiveData;
    }

}
