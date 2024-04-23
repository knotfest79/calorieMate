package com.caloriemate.application.database.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.caloriemate.application.database.entities.FoodItem;

import java.util.List;

@Dao
public interface FoodItemDao {

    @Insert
    void insert(FoodItem foodItem);

    @Query("SELECT * FROM FoodItem")
    LiveData<List<FoodItem>> getAll();
}
