package org.minhtc.and103.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.minhtc.and103.data.repository.ProductRepository;
import org.minhtc.and103.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ProductAdapter productAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final RecyclerView recyclerView = binding.recyclerViewHome;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        productAdapter = new ProductAdapter();
        recyclerView.setAdapter(productAdapter);
        ProductRepository productRepository = new ProductRepository();
        productRepository.getProducts().observe(getViewLifecycleOwner(),
                products -> productAdapter.setProducts(products));
        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}