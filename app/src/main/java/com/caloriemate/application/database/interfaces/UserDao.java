package com.caloriemate.application.database.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.caloriemate.application.database.entities.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Query("SELECT * FROM user WHERE Email = :email AND Password = :password LIMIT 1")
    LiveData<User> findUserByEmail(String email, String password);
}