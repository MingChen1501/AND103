package org.minhtc.and103.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.minhtc.and103.R;
import org.minhtc.and103.data.model.Product;
import org.minhtc.and103.data.repository.ProductRepository;
import org.minhtc.and103.databinding.FragmentHomeBinding;
import org.minhtc.and103.ui.home.productDetail.ProductDetailViewModel;

public class HomeFragment extends Fragment implements OnProductClickListener {


    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding binding;
    private ProductDetailViewModel productDetailViewModel;
    private ProductAdapter productAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final RecyclerView recyclerView = binding.recyclerViewHome;
        setupProductRecycleView(recyclerView);
//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private void setupProductRecycleView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        productAdapter = new ProductAdapter(this);
        recyclerView.setAdapter(productAdapter);
        ProductRepository productRepository = new ProductRepository();
        productDetailViewModel = new ViewModelProvider(requireActivity()).get(ProductDetailViewModel.class);
        productRepository.getProducts().observe(getViewLifecycleOwner(),
                products -> productAdapter.setProducts(products));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //set null to avoid memory leak
        binding = null;
        productDetailViewModel = null;
        productAdapter = null;
    }

    @Override
    public void onProductClick(Product product) {
        //cannot find action
        productDetailViewModel.setProduct(product);
        Navigation.findNavController(requireView()).navigate(R.id.action_navigation_home_to_navigation_product_detail);
//        Toast.makeText(getContext(), product.getName(), Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onProductClick: " + product.getName() + " clicked!" );
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_cart) {
            // Navigate to the Cart Fragment
            Navigation.findNavController(requireView()).navigate(R.id.action_navigation_home_to_navigation_cart);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}