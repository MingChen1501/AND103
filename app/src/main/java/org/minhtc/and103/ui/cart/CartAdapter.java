package org.minhtc.and103.ui.cart;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.minhtc.and103.R;
import org.minhtc.and103.data.model.Cart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private static final String TAG = "CartAdapter";
    private List<Cart> carts;
    private OnCartClickListener listener;

    public CartAdapter(OnCartClickListener listener) {
        this.listener = listener;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
        Log.d(TAG, "setCarts: " + carts);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        Log.d(TAG, "onCreateViewHolder: ");
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart currentCart = carts.get(position);
        Log.d(TAG, "onBindViewHolder: " + currentCart.getUserId());
        holder.textViewName.setText("Giỏ Hàng " + currentCart.getCartName());
        holder.textTotalItem.setText(currentCart.getItems().size() + " sản phẩm");
        holder.buttonDeleteCart.setOnClickListener(view -> {
            listener.onDeleteCart(currentCart);
        });
        holder.itemView.setOnClickListener(view -> {
            listener.onCartClick(currentCart);
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + (carts == null ? 0 : carts.size()));
        return carts == null ? 0 : carts.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textTotalItem;
        private Button buttonDeleteCart;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textView_cart_name);
            textTotalItem = itemView.findViewById(R.id.textView_total_cart_item);
            buttonDeleteCart = itemView.findViewById(R.id.button_delete_cart);
        }
    }
}
