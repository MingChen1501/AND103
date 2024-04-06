package org.minhtc.and103.ui.home.productDetail;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.minhtc.and103.R;
import org.minhtc.and103.data.model.CartItem;
import org.minhtc.and103.data.model.Product;
import org.minhtc.and103.data.model.ProductDetails;
import org.minhtc.and103.databinding.FragmentProductDetailBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductDetailFragment extends Fragment implements Button.OnClickListener {
    private static final String TAG = "ProductDetailFragment";
    private FragmentProductDetailBinding binding;
    private CartItem cartItem;

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
        final RadioGroup radioGroupColor = binding.radioGroupColor;
        final RadioGroup radioGroupSize = binding.radioGroupSize;
        final TextView txtStock = binding.textProductStock;
        final EditText edtQuantity = binding.editTextQuantity;
        final Button btnAddToCart = binding.buttonAddToCart;
        cartItem = new CartItem();
        btnAddToCart.setOnClickListener(v -> {
            Log.d(TAG, "onCreateView: " + cartItem.toString());
            RadioButton checkedColor = radioGroupColor.findViewById(radioGroupColor.getCheckedRadioButtonId());
            RadioButton checkedSize = radioGroupSize.findViewById(radioGroupSize.getCheckedRadioButtonId());

            if (checkedColor == null || checkedSize == null) {
                Log.d(TAG, "onCreateView: " + "Please select color and size");
                return;
            }
            if (!checkedSize.isEnabled()) {
                Log.d(TAG, "onCreateView: " + "Please select a valid size");
                return;
            }
            if (!checkedColor.isEnabled()) {
                Log.d(TAG, "onCreateView: " + "Please select a valid color");
                return;
            }
            int stock = (Integer)txtStock.getTag();
            if (cartItem.getQuantity() > stock) {
                Log.d(TAG, "onCreateView: " + "Please select a valid quantity");
                return;
            } else {
                cartItem.setQuantity(Integer.parseInt(edtQuantity.getText().toString()));
            }
            Navigation.findNavController(requireView()).navigate(R.id.action_navigation_product_detail_to_navigation_cart);
            Log.d(TAG, "onCreateView: " + checkedColor.getText().toString() + " " + checkedSize.getText().toString());
        });
        productDetailViewModel.getProduct().observe(getViewLifecycleOwner(), product -> {
            final String[] checkedColor = new String[2];
            txtName.setText(product.getName());
            txtPrice.setText(String.valueOf(product.getPrice()));
            txtDescription.setText(product.getDescription());

            List<String> colors = new ArrayList<>(getColors(product));;

            List<String> sizes = new ArrayList<>(getSizes(product));
            for (String color : colors) {
                RadioButton colorButton = new RadioButton(getActivity());
                colorButton.setText(color);
                colorButton.setTag(color);
                radioGroupColor.addView(colorButton);
            }
            for (String size : sizes) {
                RadioButton sizeButton = new RadioButton(getActivity());
                sizeButton.setText(size);
                sizeButton.setTag(size);
                sizeButton.setEnabled(false);
                radioGroupSize.addView(sizeButton);
            }
            radioGroupColor.setOnCheckedChangeListener((group, checkedId) -> {
                RadioButton checkedRadioButton = group.findViewById(checkedId);
                checkedColor[0] = checkedRadioButton.getText().toString();
                // When a color is selected
                for (int i = 0; i < radioGroupSize.getChildCount(); i++) {
                    RadioButton sizeButton = (RadioButton) radioGroupSize.getChildAt(i);
                    // Check if the size is available in the product detail and enable/disable accordingly
                    if (isSizeAvailable(product, sizeButton.getText().toString(), checkedRadioButton.getText().toString())) {
                        sizeButton.setEnabled(true);
                    } else {
                        sizeButton.setEnabled(false);
                    }
                }
            });
            radioGroupSize.setOnCheckedChangeListener((group, checkedId) -> {
                RadioButton checkedRadioButton = group.findViewById(checkedId);
                checkedColor[1] = checkedRadioButton.getText().toString();
                product.getDetails().stream()
                        .filter(productDetails -> productDetails.getColor().equals(checkedColor[0]) && productDetails.getSize().equals(checkedColor[1]))
                        .findFirst().ifPresent(productDetails -> {
                            txtPrice.setText(String.valueOf(productDetails.getPrice()) + " VND");
                            txtStock.setText(String.valueOf("Còn lại " + productDetails.getStock() + " sản phẩm"));
                            txtStock.setTag(productDetails.getStock());
                            InputFilter[] filterArray = new InputFilter[1];
                            filterArray[0] = new InputFilter.LengthFilter(2);
                            edtQuantity.setFilters(filterArray);
                            cartItem.setProductId(product.get_id());
                            cartItem.setProductDetailId(productDetails.get_id());
                        });
                Log.d(TAG, "onCreateView: " + checkedColor[0] + " " + checkedColor[1]);
            });
        });

        View root = binding.getRoot();
        return root;
    }

    private boolean isSizeAvailable(Product product, String size, String color) {
        List<ProductDetails> details = product.getDetails();
        for (ProductDetails detail : details) {
          if (detail.getColor().equals(color) && detail.getSize().equals(size) && detail.getStock() > 0) {
                return true;
            }
        }
        return false;
    }


    private Set<String> getSizes(Product product) {
        List<ProductDetails> details = product.getDetails();
        Set<String> sizes = new HashSet<>();
        details.forEach(productDetails -> {
            sizes.add(productDetails.getSize());
        });
        return sizes;
    }

    private Set<String> getColors(Product product) {
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
        cartItem = null;
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: ");
    }
}