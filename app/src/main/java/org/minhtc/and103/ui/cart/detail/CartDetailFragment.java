package org.minhtc.and103.ui.cart.detail;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.minhtc.and103.R;
import org.minhtc.and103.data.model.Cart;
import org.minhtc.and103.databinding.FragmentCartDetailBinding;
import org.minhtc.and103.ui.cart.CartViewModel;

public class CartDetailFragment extends Fragment {

    private static final String TAG = "CartDetailFragment";
    private FragmentCartDetailBinding binding;
    private CartDetailViewModel mViewModel;
    private CartViewModel cartViewModel;

    public static CartDetailFragment newInstance() {
        return new CartDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCartDetailBinding.inflate(inflater, container, false);
        final View root = binding.getRoot();

        cartViewModel = new ViewModelProvider(requireActivity())
                .get(CartViewModel.class);
        Cart cart = cartViewModel.getCart().getValue();
        if (cart != null) {
            Log.d(TAG, "onCreateView: " + cart);
        }
        return root;
    }
}