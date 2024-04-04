package org.minhtc.and103.ui.productDetail;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.minhtc.and103.R;
import org.minhtc.and103.data.model.Product;
import org.minhtc.and103.data.model.ProductDetails;
import org.minhtc.and103.databinding.FragmentProductDetailBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        final RadioGroup colorRadioGroup = binding.radioGroupColor;
        final RadioGroup SizeRadioGroup = binding.radioGroupSize;
        final Button btnAddToCart = binding.buttonAddToCart;
        productDetailViewModel.getProduct().observe(getViewLifecycleOwner(), product -> {
            txtName.setText(product.getName());
            txtPrice.setText(String.valueOf(product.getPrice()));
            txtDescription.setText(product.getDescription());

            List<String> colors = new ArrayList<>(renderColorRadioGroup(product));;

            List<String> sizes = new ArrayList<>(renderSizeRadioGroup(product));
            Log.d(TAG, "onCreateView: " + colors.size() + " " + sizes.size());
            for (String color : colors) {
                RadioButton colorButton = new RadioButton(getActivity());
                colorButton.setText(color);
                colorRadioGroup.addView(colorButton);
            }
            for (String size : sizes) {
                RadioButton sizeButton = new RadioButton(getActivity());
                sizeButton.setText(size);
                SizeRadioGroup.addView(sizeButton);
            }
        });

        View root = binding.getRoot();
        return root;
    }

    private Set<String> renderSizeRadioGroup(Product product) {
        List<ProductDetails> details = product.getDetails();
        Set<String> sizes = new HashSet<>();
        details.forEach(productDetails -> {
            sizes.add(productDetails.getSize());
        });
        return sizes;
    }

    private Set<String> renderColorRadioGroup(Product product) {
        List<ProductDetails> details = product.getDetails();
        Set<String> colors = new HashSet<>();
        details.forEach(productDetails -> {
            colors.add(productDetails.getColor());
        });
        return colors;
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