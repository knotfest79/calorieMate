package com.caloriemate.application.view.model;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.caloriemate.application.database.entities.FoodItem;
import com.caloriemate.application.databinding.MenuItemListBinding;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private static List<FoodItem> items;
    private ItemClickListener listener;
    private static Set<Integer> checkedItems = new HashSet<>();

    public MenuAdapter(List<FoodItem> items, ItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MenuItemListBinding binding = MenuItemListBinding.inflate(layoutInflater, parent, false);
        return new MenuViewHolder(binding, listener);
    }

    public void updateList(List<FoodItem> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        FoodItem item = items.get(position);
        holder.bind(item, checkedItems.contains(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Set<Integer> getCheckedItems() {
        return new HashSet<>(checkedItems);
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {
        private final MenuItemListBinding binding;

        MenuViewHolder(@NonNull MenuItemListBinding binding, ItemClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;

            binding.itemTextView.setOnClickListener(v -> {
                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    listener.onItemClick(items.get(getAdapterPosition()));
                }
            });

            binding.cbWorkout.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    if (checkedItems.contains(position)) {
                        checkedItems.remove(position);
                    } else {
                        checkedItems.add(position);
                    }
                }
            });
        }

        void bind(FoodItem item, boolean isChecked) {
            binding.itemTextView.setText(item.Name + "\n" + item.Calorie + " kcal");
            binding.cbWorkout.setChecked(isChecked);
        }
    }
}