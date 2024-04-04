package org.minhtc.and103.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.minhtc.and103.R;
import org.minhtc.and103.data.model.Product;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> products = new ArrayList<>();
    private OnProductClickListener listener;

    public ProductAdapter(OnProductClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product currentProduct = products.get(position);
        holder.textViewName.setText(currentProduct.getName());
        holder.textViewPrice
                .setText(MessageFormat.format("{0}VND", String.valueOf(currentProduct.getPrice())));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }
    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewName;
        private TextView textViewPrice;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.txt_product_name);
            textViewPrice = itemView.findViewById(R.id.txt_product_price);
            itemView.setOnClickListener(this::onClick);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && listener != null) {
                Product product = products.get(position);
                listener.onProductClick(product);
            }
        }
    }
}
