package org.minhtc.and103.ui.home.productDetail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.minhtc.and103.data.model.Product;
import org.minhtc.and103.data.model.ProductDetails;

public class ProductDetailViewModel extends ViewModel {

    public static final String TAG = "ProductDetailViewModel";
    private MutableLiveData<Product> productData = new MutableLiveData<>();
    private MutableLiveData<ProductDetails> productDetailsData = new MutableLiveData<>();
    public void setProduct(Product product) {
        Log.d(TAG, "setProduct: " + product);
        productData.setValue(product);
    }
    public LiveData<Product> getProduct() {
        Log.d(TAG, "getProduct: " + productData.getValue());
        return productData;
    }
    public void setProductDetails(ProductDetails productDetails) {
        productDetailsData.setValue(productDetails);
    }
    public LiveData<ProductDetails> getProductDetails() {
        return productDetailsData;
    }

}