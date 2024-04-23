package com.caloriemate.application.view.model;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.caloriemate.application.R;
import com.caloriemate.application.database.entities.FoodItem;
import com.caloriemate.application.databinding.MenuFragmentBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MenuFragment extends Fragment {

    private MenuViewModel mViewModel;
    private MenuFragmentBinding binding;
    private MenuAdapter adapter;
    private List<FoodItem> allFoodItems;

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MenuFragmentBinding.inflate(inflater, container, false);
        NavController navController = NavHostFragment.findNavController(this);
        binding.ivReturn.setOnClickListener(v -> {
            navController.navigate(R.id.action_menuFragment_to_loginFragment);
        });
        setupRecyclerView();
        setupSearchView();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(MenuViewModel.class);
        mViewModel.getAllFoodItems().observe(getViewLifecycleOwner(), foodItems -> {
            allFoodItems = new ArrayList<>(foodItems);
            updateAdapterItems(foodItems);
        });

        setupNextButton();
    }

    private void updateAdapterItems(List<FoodItem> foodItems) {
        List<FoodItem> items = new ArrayList<>();
        for (FoodItem item : foodItems) {
            items.add(item);
        }
        adapter.updateList(items);
    }

    private void setupNextButton() {
        NavController navController = NavHostFragment.findNavController(this);
        binding.btnMenuNext.setOnClickListener(v -> {
            List<FoodItem> checkedItems = allFoodItems.stream()
                    .filter(item -> adapter.getCheckedItems().contains(allFoodItems.indexOf(item)))
                    .collect(Collectors.toList());

            if (!checkedItems.isEmpty()) {
                FoodItem[] checkedItemsArray = checkedItems.toArray(new FoodItem[0]);
                MenuFragmentDirections.ActionMenuFragmentToCalculatorFragment action =
                        MenuFragmentDirections.actionMenuFragmentToCalculatorFragment(checkedItemsArray);
                navController.navigate(action);
            } else {
                // Consider showing a Toast or Snackbar message if no items are checked.
                Toast.makeText(getContext(), "No items selected", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setupRecyclerView() {
        adapter = new MenuAdapter(new ArrayList<>(), new ItemClickListener() {
            @Override
            public void onItemClick(String itemDetail) {

            }

            @Override
            public void onItemClick(FoodItem item) {
                navigateToItemDetails(item);
            }

            @Override
            public void onCheckboxClick(int position, boolean isChecked) {
                // Handle checkbox logic here, if needed
                // Example: toggle selection state in your data model or view model
            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
    }


    private void setupSearchView() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }
    private void filter(String text) {
        if (allFoodItems == null) return;

        List<FoodItem> filteredList = allFoodItems.stream()
                .filter(item -> item.Name.toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
        updateAdapterItems(filteredList);
    }

    private void navigateToItemDetails(FoodItem foodItem) {
        NavController navController = NavHostFragment.findNavController(this);
        MenuFragmentDirections.ActionMenuFragmentToItemDetailsFragment action =
                MenuFragmentDirections.actionMenuFragmentToItemDetailsFragment(foodItem);
        navController.navigate(action);
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            BottomNavigationView bottomAppBar = activity.findViewById(R.id.bottomNavigationView);
            if (bottomAppBar != null) {
                bottomAppBar.setVisibility(View.VISIBLE);
            }
        }
    }

}