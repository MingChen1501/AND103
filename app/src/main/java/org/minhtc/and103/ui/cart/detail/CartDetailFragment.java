package org.minhtc.and103.ui.cart.detail;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.minhtc.and103.R;
import org.minhtc.and103.data.dto.OrderDetail;
import org.minhtc.and103.data.model.CartItem;
import org.minhtc.and103.databinding.FragmentCartDetailBinding;
import org.minhtc.and103.ui.cart.CartViewModel;
import org.minhtc.and103.ui.order.CreateOrderViewModel;

import java.util.List;
public class CartDetailFragment extends Fragment implements OnCheckedItem {

    private static final String TAG = "CartDetailFragment";
    private FragmentCartDetailBinding binding;
    private CartViewModel cartViewModel;
    private CreateOrderViewModel createOrderViewModel;

    public static CartDetailFragment newInstance() {
        return new CartDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCartDetailBinding.inflate(inflater, container, false);

        cartViewModel = new ViewModelProvider(requireActivity())
                .get(CartViewModel.class);
        createOrderViewModel = new ViewModelProvider(requireActivity()).get(CreateOrderViewModel.class);
        final Button buttonOrder = binding.buttonOrder;
        final RecyclerView recyclerView = binding.recyclerViewCartItems;
        final TextView textViewCartName = binding.textViewCartName;
        final TextView textViewTotalAmount = binding.textViewTotalAmount;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CartItemsAdapter cartItemsAdapter = new CartItemsAdapter(this);
        recyclerView.setAdapter(cartItemsAdapter);

        cartViewModel.getCart().observe(getViewLifecycleOwner(), cart -> {
            Log.d(TAG, "onCreateView: " + cart);
            if (cart == null) {
                return;
            }
            textViewCartName.setText(cart.getCartName());
            cartItemsAdapter.setCartItems(cart.getItems());
        });
        cartViewModel.getTotalAmount().observe(getViewLifecycleOwner(), totalAmount -> {
            Log.d(TAG, "onCreateView: " + totalAmount);
            if (totalAmount == null) {
                return;
            }
            textViewTotalAmount.setText(totalAmount.toString());
            textViewTotalAmount.setTag(totalAmount);
        });
        buttonOrder.setOnClickListener(v -> {
            Log.d(TAG, "onClickCreateOrder: ");
            Double totalAmount = (Double) cartViewModel.getTotalAmount().getValue();
            List<OrderDetail> orderDetails = cartViewModel.getOrderDetails().getValue();
            Log.d(TAG, "onClickCreateOrder: " + totalAmount + " " + orderDetails);
            createOrderViewModel.setOrderDetails(orderDetails);
            createOrderViewModel.setTotalAmount(totalAmount);
            Navigation.findNavController(requireView())
                    .navigate(R.id.action_navigation_cart_detail_to_navigation_create_order);
        });
        final View root = binding.getRoot();
        return root;
    }

    @Override
    public void onCheckedItem(List<OrderDetail> selectedItems, Double totalAmount) {
        Log.d(TAG, "onClickCreateOrder: " + totalAmount + " " + selectedItems);
//        TODO: increase total amount
        cartViewModel.setOrderDetails(selectedItems);
        cartViewModel.increaseTotalAmount(totalAmount);
    }

    @Override
    public void onUncheckedItem(List<OrderDetail> selectedItems, Double totalAmount) {
        Log.d(TAG, "onClickCreateOrder: " + totalAmount + " " + selectedItems);
//        TODO: decrease total amount
        cartViewModel.setOrderDetails(selectedItems);
        cartViewModel.decreaseTotalAmount(totalAmount);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        cartViewModel.clearTotalAmount();
        cartViewModel = null;
    }

}