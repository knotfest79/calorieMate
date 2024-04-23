package com.caloriemate.application.view.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.caloriemate.application.database.entities.FoodItem;
import com.caloriemate.application.database.repositories.FoodItemRepository;

import java.util.List;

public class MenuViewModel extends AndroidViewModel {
    private FoodItemRepository foodItemRepository;

    public MenuViewModel(Application application) {
        super(application);
        foodItemRepository = new FoodItemRepository(application);
    }

    public LiveData<List<FoodItem>> getAllFoodItems() {
        return foodItemRepository.getAllFoodItems();
    }
}
