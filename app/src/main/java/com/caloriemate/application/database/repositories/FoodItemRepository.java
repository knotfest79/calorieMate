package com.caloriemate.application.database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.caloriemate.application.database.AppDatabase;
import com.caloriemate.application.database.entities.FoodItem;
import com.caloriemate.application.database.interfaces.FoodItemDao;

import java.util.List;

public class FoodItemRepository {

    private FoodItemDao foodItemDao;

    public FoodItemRepository(Application application) {
        AppDatabase db = Room.databaseBuilder(application, AppDatabase.class, "CalorieDB").fallbackToDestructiveMigration().build();
        foodItemDao = db.foodItemDao();
    }

    public LiveData<List<FoodItem>> getAllFoodItems() {
        return foodItemDao.getAll();
    }
}
