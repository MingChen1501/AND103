package org.minhtc.and103.ui.productDetail;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.minhtc.and103.R;
import org.minhtc.and103.databinding.FragmentProductDetailBinding;

public class ProductDetailFragment extends Fragment {
    private static final String TAG = "ProductDetailFragment";
    private FragmentProductDetailBinding binding;

    public static ProductDetailFragment newInstance() {
        return new ProductDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        final ProductDetailViewModel productDetailViewModel = new ViewModelProvider(requireActivity()).get(ProductDetailViewModel.class);

        final TextView txtName = binding.textProductName;
        final TextView txtPrice = binding.textProductPrice;
        final TextView txtDescription = binding.textProductDescription;
        productDetailViewModel.getProduct().observe(getViewLifecycleOwner(), product -> {
            txtName.setText(product.getName());
            txtPrice.setText(String.valueOf(product.getPrice()));
            txtDescription.setText(product.getDescription());
        });

        View root = binding.getRoot();
        return root;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(ProductDetailViewModel.class);
//        // TODO: Use the ViewModel
//    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}