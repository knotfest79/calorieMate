package com.caloriemate.application.view.model;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caloriemate.application.R;
import com.caloriemate.application.database.entities.FoodItem;
import com.caloriemate.application.databinding.CalculatorFragmentBinding;

import java.util.Arrays;

public class CalculatorFragment extends Fragment {

    private CalculatorViewModel mViewModel;
    private CalculatorAdapter adapter;
    private CalculatorFragmentBinding binding;
    private Double calculatedCalories = 0.00;
    private Double calculatedCarbs = 0.00;
    private Double calculatedFat = 0.00;
    private Double calculatedProtein = 0.00;

    public static CalculatorFragment newInstance() {
        return new CalculatorFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CalculatorFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);
        NavController navController = NavHostFragment.findNavController(this);
        binding.ibReturn.setOnClickListener(v -> {
            navController.navigate(R.id.action_calculatorFragment_menuFragment);
        });
        if (getArguments() != null) {
            CalculatorFragmentArgs args = CalculatorFragmentArgs.fromBundle(getArguments());
            FoodItem[] checkedFoodItems = args.getCheckedFoodItems();
            adapter = new CalculatorAdapter(Arrays.asList(checkedFoodItems));
            binding.rvItems.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvItems.setAdapter(adapter);

            for (FoodItem checkedFoodItem : checkedFoodItems) {
                calculatedCalories += checkedFoodItem.Calorie;
                calculatedCarbs += checkedFoodItem.Carbs;
                calculatedFat += checkedFoodItem.Fat;
                calculatedProtein += checkedFoodItem.Protein;
            }
            binding.tvCaloriesValue.setText(String.format("%.2f", calculatedCalories));
            binding.tvCarbs.setText(String.format("%.2f", calculatedCarbs) + "g\nCarbs");
            binding.tvFat.setText(String.format("%.2f", calculatedFat) + "g\nFat");
            binding.tvProtein.setText(String.format("%.2f", calculatedProtein) + "g\nProtein");
        }
    }
}