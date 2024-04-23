package com.caloriemate.application.view.model;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caloriemate.application.R;
import com.caloriemate.application.database.entities.FoodItem;
import com.caloriemate.application.databinding.ItemDetailsFragmentBinding;

public class ItemDetailsFragment extends Fragment {

    private ItemDetailsViewModel mViewModel;
    private ItemDetailsFragmentBinding binding;

    public static ItemDetailsFragment newInstance() {
        return new ItemDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ItemDetailsFragmentBinding.inflate(inflater, container, false);
        NavController navController = NavHostFragment.findNavController(this);
        binding.ibReturnView.setOnClickListener(v -> {
            navController.navigate(R.id.action_itemDetailsFragment_to_menuFragment);
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            ItemDetailsFragmentArgs args = ItemDetailsFragmentArgs.fromBundle(getArguments());
            FoodItem itemDetail = args.getItemDetails();
            binding.ivItem.setImageResource(itemDetail.getImageResource(itemDetail.ImageName));
            binding.itemNameTextView.setText(itemDetail.Name);
            binding.caloriesValue.setText(String.format("%.2f", itemDetail.Calorie)+"\nCalories");
            binding.carbsValue.setText(String.format("%.2f", itemDetail.Carbs)+"\nCarbs");
            binding.proteinValue.setText(String.format("%.2f", itemDetail.Protein)+"\nProtein");
            binding.fatValue.setText(String.format("%.2f", itemDetail.Fat)+"\nFat");

            binding.tvSugars.setText("Sugars: " + Double.toString(itemDetail.sugar)+"g");
            binding.tvKg.setText("KiloJoule: " +Double.toString(itemDetail.kilojoule)+"kj");
            binding.tvCalcium.setText("Calcium: " +Double.toString(itemDetail.Calcium)+"mg");
            binding.tvMagnesium.setText("Magnesium: " +Double.toString(itemDetail.Sodium)+"mg");
            binding.tvPotassium.setText("Potassium: " +Double.toString(itemDetail.Potassium)+"mg");
            binding.tvSelenium.setText("Zinc: " +Double.toString(itemDetail.Zinc)+"mg");
            binding.tvAlchol.setText("Selenium: " +Double.toString(itemDetail.Selenium)+"mg");
            binding.tvFiber.setText("Alchol: " +Double.toString(itemDetail.Fiber)+"g");
            binding.tvIron.setText("Fiber: " +Double.toString(itemDetail.kilojoule)+"g");
            binding.tvPhosphorus.setText("Iron: " +Double.toString(itemDetail.Phosphorous)+"mg");
            binding.tvSodium.setText("Phosphorus: " +Double.toString(itemDetail.Sodium)+"mg");
            binding.tvCopper.setText("Sodium: " +Double.toString(itemDetail.Copper)+"mg");
        }
    }
}