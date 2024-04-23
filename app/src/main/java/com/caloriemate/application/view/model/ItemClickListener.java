package com.caloriemate.application.view.model;

import com.caloriemate.application.database.entities.FoodItem;

public interface ItemClickListener {
    void onItemClick(String itemDetail);
    void onItemClick(FoodItem item);
    void onCheckboxClick(int position, boolean isChecked);
}