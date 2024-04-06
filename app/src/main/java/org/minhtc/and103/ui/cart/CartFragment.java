package org.minhtc.and103.ui.cart;

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
import android.widget.TextView;

import org.minhtc.and103.R;
import org.minhtc.and103.data.model.Cart;
import org.minhtc.and103.data.repository.CartRepository;
import org.minhtc.and103.databinding.FragmentCartBinding;
import org.minhtc.and103.ui.cart.detail.CartDetailViewModel;

public class CartFragment extends Fragment implements OnCartClickListener {
    private static final String TAG = "CartFragment";
    private FragmentCartBinding binding;
    private SharedPreferences sharedPreferences;
    private CartAdapter cartAdapter;
    private CartViewModel cartViewModel;
    private CartDetailViewModel cartDetailViewModel;
    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        String userId = sharedPreferences.getString("userId", "");
        Log.d(TAG, "onCreateView: " + userId);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        cartViewModel = new ViewModelProvider(requireActivity())
                .get(CartViewModel.class);
        cartDetailViewModel = new ViewModelProvider(requireActivity())
                .get(CartDetailViewModel.class);
        final View root = binding.getRoot();
        final RecyclerView recyclerView = binding.cartRecyclerView;
        final TextView textView = binding.textView;
        CartRepository cartRepository = new CartRepository();
        cartAdapter = new CartAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(cartAdapter);
        cartRepository.getCarts(userId).observe(getViewLifecycleOwner(), carts -> {
            Log.d(TAG, "onCreateView: " + carts);
            cartAdapter.setCarts(carts);
        });
        if (!isLoggedIn) {
            textView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        } else {
            textView.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);

        }
        return root;
    }

    @Override
    public void onCartClick(Cart cart) {
        cartViewModel.setCart(cart);
        Navigation.findNavController(requireView())
                .navigate(R.id.action_navigation_cart_to_navigation_cart_detail);
    }

    @Override
    public void onDeleteCart(Cart cart) {
        Log.d(TAG, "onDeleteCart: " + cart);
    }
}