package com.caloriemate.application.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.caloriemate.application.database.entities.FoodItem;
import com.caloriemate.application.database.entities.User;
import com.caloriemate.application.database.interfaces.FoodItemDao;
import com.caloriemate.application.database.interfaces.UserDao;

@Database(entities = {User.class, FoodItem.class}, version = 8)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract FoodItemDao foodItemDao();
}