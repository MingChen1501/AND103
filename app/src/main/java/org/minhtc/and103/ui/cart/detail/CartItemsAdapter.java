package org.minhtc.and103.ui.cart.detail;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.minhtc.and103.R;
import org.minhtc.and103.data.dto.OrderDetail;
import org.minhtc.and103.data.model.CartItem;
import org.minhtc.and103.data.model.Product;
import org.minhtc.and103.data.model.ProductDetail;
import org.minhtc.and103.infrastructure.service.ProductService;
import org.minhtc.and103.infrastructure.service.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.CartItemViewHolder>{
    private final OnCheckedItem onCheckedItem;
    private static final String TAG = "CartItemsAdapter";
    private List<CartItem> cartItems;
    private final List<OrderDetail> selectedItems;
    private ProductService productService;

    public CartItemsAdapter(OnCheckedItem onCheckedItem) {
        this.onCheckedItem = onCheckedItem;
        selectedItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_detail_item, parent, false);
        productService = RetrofitClient.getClient().create(ProductService.class);
        return new CartItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        CartItem currentItem = cartItems.get(position);
        productService.getProduct(currentItem.getProductId()).enqueue(new retrofit2.Callback<Product>() {
            @Override
            public void onResponse(retrofit2.Call<Product> call, retrofit2.Response<Product> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: body: " + response.body());
                    Product product = response.body();
                    ProductDetail productDetail = product
                            .getDetails()
                            .stream()
                            .filter(detail -> detail
                                    .get_id()
                                    .equals(currentItem.getProductDetailId()))
                            .findFirst()
                            .get();
                    Log.d(TAG, "onResponse: detail: " + productDetail);
                    holder.textViewProductName.setText(product.getName());
                    holder.textViewProductPrice.setText(String.valueOf(productDetail.getPrice()));
                    holder.textViewProductPrice.setTag(productDetail.getPrice());
                    holder.editTextProductQuantity.setText(String.valueOf(currentItem.getQuantity()));
                    holder.textViewProductTotalPrice.setText(String.valueOf(productDetail.getPrice() * currentItem.getQuantity()));
                    holder.textViewProductTotalPrice.setTag(productDetail.getPrice() * currentItem.getQuantity());
                    holder.textViewProductColor.setText(productDetail.getColor());
                    holder.textViewProductSize.setText(productDetail.getSize());
                    holder.textViewProductStock.setText(new StringBuilder().append("Tồn kho: ").append(productDetail.getStock()).toString());
                    holder.textViewProductStock.setTag(productDetail.getStock());
                    if (currentItem.getQuantity() > productDetail.getStock()) {
                        holder.editTextProductQuantity.setError("Số lượng vượt quá tồn kho");
                        holder.checkBoxOrderItem.setEnabled(false);
                        holder.checkBoxOrderItem.setChecked(false);
                    }
                    holder.editTextProductQuantity.addTextChangedListener(new android.text.TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void afterTextChanged(android.text.Editable s) {
                            int quantity = Integer.parseInt(s == null || s.toString().isEmpty() ? "0" : s.toString());
                            Double price = (Double) holder.textViewProductPrice.getTag();
                            holder.textViewProductTotalPrice.setText(String.valueOf(quantity * price));
                            holder.textViewProductTotalPrice.setTag(quantity * price);
                            if (quantity > (int) holder.textViewProductStock.getTag()) {
                                holder.editTextProductQuantity.setError("Số lượng vượt quá tồn kho");
                                holder.checkBoxOrderItem.setEnabled(false);
                                holder.checkBoxOrderItem.setChecked(false);
                            } else {
                                holder.editTextProductQuantity.setError(null);
                                holder.checkBoxOrderItem.setEnabled(true);
                            }
                        }
                    });
                } else {
                    Log.d(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Product> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
        holder.checkBoxOrderItem.setOnCheckedChangeListener((buttonView, isChecked) -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setCartItemId(currentItem.get_id());
            orderDetail.setProductId(currentItem.getProductId());
            orderDetail.setProductDetailId(currentItem.getProductDetailId());
            orderDetail.setProductName(holder.textViewProductName.getText().toString());
            orderDetail.setColor(holder.textViewProductColor.getText().toString());
            orderDetail.setSize(holder.textViewProductSize.getText().toString());
            orderDetail.setQuantity(Integer.parseInt(holder.editTextProductQuantity.getText().toString()));
            orderDetail.setPrice(Double.parseDouble(holder.textViewProductPrice.getText().toString()));
            orderDetail.setTotalPrice(Double.parseDouble(holder.textViewProductTotalPrice.getText().toString()));
            if (isChecked) {
                selectedItems.add(orderDetail);
                Log.d(TAG, "onBindViewHolder: checked " + selectedItems);
                double totalAmount = holder.textViewProductTotalPrice.getTag() == null ? 0 : (double) holder.textViewProductTotalPrice.getTag();
                onCheckedItem.onCheckedItem(selectedItems, totalAmount);
            } else {
                selectedItems.remove(orderDetail);
                Log.d(TAG, "onBindViewHolder: not checked" + selectedItems);
                double totalAmount = holder.textViewProductTotalPrice.getTag() == null ? 0 : (double) holder.textViewProductTotalPrice.getTag();
                onCheckedItem.onUncheckedItem(selectedItems, totalAmount);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems == null ? 0 : cartItems.size();
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public static class CartItemViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "CartItemViewHolder";
        private final CheckBox checkBoxOrderItem;
        private final TextView textViewProductName;
        private final TextView textViewProductPrice;
        private final EditText editTextProductQuantity;
        private final TextView textViewProductTotalPrice;
        private final TextView textViewProductColor;
        private final TextView textViewProductSize;
        private final TextView textViewProductStock;
        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBoxOrderItem = itemView.findViewById(R.id.checkBox_cart_item);
            textViewProductName = itemView.findViewById(R.id.textView_cart_item_name);
            textViewProductPrice = itemView.findViewById(R.id.textView_cart_item_price);
            editTextProductQuantity = itemView.findViewById(R.id.edit_text_quantity);
            textViewProductTotalPrice = itemView.findViewById(R.id.textView_cart_item_total_price);
            textViewProductColor = itemView.findViewById(R.id.textView_cart_item_color);
            textViewProductSize = itemView.findViewById(R.id.textView_cart_item_size);
            textViewProductStock = itemView.findViewById(R.id.textView_product_stock);
        }
    }
}
