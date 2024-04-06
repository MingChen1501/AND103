package org.minhtc.and103.ui.order;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.minhtc.and103.R;
import org.minhtc.and103.data.dto.OrderDetail;

import java.util.List;

public class OrderItemsAdapter extends RecyclerView.Adapter<OrderItemsAdapter.OrderItemsViewHolder> {
    private List<OrderDetail> orderDetails;

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @NonNull
    @Override
    public OrderItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(parent.getContext(), R.layout.order_detail_item, null);
        return new OrderItemsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemsViewHolder holder, int position) {
        OrderDetail currentOrderDetail = orderDetails.get(position);
        holder.textViewOrderItemName.setText(currentOrderDetail.getProductName());
        holder.textViewOrderItemPrice.setText("Giá: " + String.valueOf(currentOrderDetail.getPrice()));
        holder.textViewOrderItemQuantity.setText(String.valueOf(currentOrderDetail.getQuantity()));
        holder.textViewOrderItemAmount.setText("Thành tiền: " + String.valueOf(currentOrderDetail.getTotalPrice()));
        holder.textViewOrderItemSize.setText(currentOrderDetail.getSize());
        holder.textViewOrderItemColor.setText(currentOrderDetail.getColor());
    }

    @Override
    public int getItemCount() {
        return orderDetails == null ? 0 : orderDetails.size();
    }

    public static class OrderItemsViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewOrderItemName;
        private final TextView textViewOrderItemPrice;
        private final TextView textViewOrderItemQuantity;
        private final TextView textViewOrderItemAmount;
        private final TextView textViewOrderItemSize;
        private final TextView textViewOrderItemColor;
        public OrderItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewOrderItemName = itemView.findViewById(R.id.fragment_create_order_text_view_order_item_name);
            textViewOrderItemPrice = itemView.findViewById(R.id.fragment_create_order_text_view_order_item_price);
            textViewOrderItemQuantity = itemView.findViewById(R.id.fragment_create_order_text_view_order_item_quantity);
            textViewOrderItemAmount = itemView.findViewById(R.id.fragment_create_order_text_view_order_item_amount);
            textViewOrderItemSize = itemView.findViewById(R.id.fragment_create_order_text_view_order_item_size);
            textViewOrderItemColor = itemView.findViewById(R.id.fragment_create_order_text_view_order_item_color);
        }
    }
}
