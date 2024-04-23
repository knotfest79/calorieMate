package com.caloriemate.application.view.model;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.caloriemate.application.database.entities.FoodItem;
import com.caloriemate.application.databinding.FoodItemListBinding;

import java.util.List;

public class CalculatorAdapter extends RecyclerView.Adapter<CalculatorAdapter.CalculatorViewHolder> {
    private List<FoodItem> items;
    public CalculatorAdapter(List<FoodItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CalculatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FoodItemListBinding binding = FoodItemListBinding.inflate(layoutInflater, parent, false);
        return new CalculatorViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CalculatorViewHolder holder, int position) {
        FoodItem item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class CalculatorViewHolder extends RecyclerView.ViewHolder {
        FoodItemListBinding binding;
        public CalculatorViewHolder(@NonNull FoodItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(FoodItem item) {
            binding.itemListNameTextView.setText(item.Name);
            binding.itemCaloriesListTextView.setText(String.format("%.2f", item.Calorie) + " kcal");
        }
    }
}
