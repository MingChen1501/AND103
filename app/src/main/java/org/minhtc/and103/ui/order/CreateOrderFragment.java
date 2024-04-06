package org.minhtc.and103.ui.order;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.minhtc.and103.R;
import org.minhtc.and103.data.dto.OrderDetail;
import org.minhtc.and103.data.payload.order.CreateOrderRequest;
import org.minhtc.and103.databinding.FragmentCreateOrderBinding;
import org.minhtc.and103.infrastructure.service.OrderService;
import org.minhtc.and103.infrastructure.service.RetrofitClient;
import org.minhtc.and103.ui.cart.CartViewModel;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;

public class CreateOrderFragment extends Fragment {

    private static final String TAG = "CreateOrderFragment";
    private FragmentCreateOrderBinding binding;
    private CreateOrderViewModel mViewModel;

    public static CreateOrderFragment newInstance() {
        return new CreateOrderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCreateOrderBinding.inflate(inflater, container, false);
        final CartViewModel cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        mViewModel = new ViewModelProvider(requireActivity()).get(CreateOrderViewModel.class);
        final Button buttonOrder = binding.fragmentCreateOrderButtonOrder;
        final TextView textViewTotalAmount = binding.fragmentCreateOrderTextViewTotalAmount;
        final RecyclerView recyclerView = binding.fragmentCreateOrderRecyclerViewOrderItems;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        OrderItemsAdapter orderItemsAdapter = new OrderItemsAdapter();
        recyclerView.setAdapter(orderItemsAdapter);
        mViewModel.getOrderDetails().observe(getViewLifecycleOwner(), cartItems -> {
            Log.d(TAG, "onCreateView: " + cartItems);
            if (cartItems == null) {
                return;
            }
            orderItemsAdapter.setOrderDetails(cartItems);
        });
        mViewModel.getTotalAmount().observe(getViewLifecycleOwner(), totalAmount -> {
            Log.d(TAG, "onCreateView: " + totalAmount);
            textViewTotalAmount.setText(String.valueOf(totalAmount));
        });
        buttonOrder.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity());
            String userId = sharedPreferences.getString("userId", "");
            CreateOrderRequest createOrderRequest = new CreateOrderRequest();
            List<OrderDetail> orderDetails = mViewModel.getOrderDetails().getValue();
            double totalAmount = mViewModel.getTotalAmount().getValue();
            if (orderDetails == null  || userId.isEmpty()) {
                return;
            }
            createOrderRequest.setUserId(userId);
//            if (orderDetails.size() > 0) {
//                createOrderRequest.setCart_id(orderDetails.get(0).getCartId());
//            }
            List<String> cartItemIds = orderDetails.stream().map(OrderDetail::getCartItemId).collect(Collectors.toList());
//            Log.d(TAG, "onCreateView: " + cartItemIds);
            String cartId = cartViewModel.getCart().getValue().get_id();
            createOrderRequest.setCart_item_ids(cartItemIds);
            createOrderRequest.setCart_id(cartId);
            createOrderRequest.setPayment_method("COD");
            createOrderRequest.setTotal_amount(totalAmount);
            createOrderRequest.setType("ORDER_FROM_CART");
            Log.d(TAG, "onCreateView: " + createOrderRequest);
            OrderService orderService = RetrofitClient.getClient().create(OrderService.class);
            Call<Void> call = orderService.createOrder(createOrderRequest);
            call.enqueue(new retrofit2.Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                    if (!response.isSuccessful()) {
                        Log.d(TAG, "onResponse: " + response.errorBody());
                        Toast.makeText(requireContext(), "Đơn hàng tạo thất bại", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d(TAG, "onResponse: " + response.body());
                        Toast.makeText(requireContext(), "Đơn hàng được tạo thành công", Toast.LENGTH_LONG).show();
                        Navigation.findNavController(requireView()).navigate(R.id.navigation_home);
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                    Toast.makeText(requireContext(), "Đơn hàng tạo thất bại", Toast.LENGTH_SHORT).show();
                }
            });
//            Log.d(TAG, "onCreateView: " + userId);
//            Log.d(TAG, "onCreateView: " + orderDetails);
//            Log.d(TAG, "onCreateView: " + totalAmount);
        });
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        mViewModel = null;
    }
}