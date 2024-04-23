package com.caloriemate.application.database.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.caloriemate.application.database.AppDatabase;
import com.caloriemate.application.database.entities.User;
import com.caloriemate.application.database.interfaces.UserDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    private final UserDao userDao;
    private static AppDatabase instance;
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public UserRepository(Application application) {
        AppDatabase db = Room.databaseBuilder(application, AppDatabase.class, "CalorieDB").fallbackToDestructiveMigration().build();
        userDao = db.userDao();
    }

    // main thread - ui running
    // run a user repository - save data -

    public void insert(User user) {
        executorService.execute(() -> userDao.insert(user));
    }

    public void update(User user) {

        if (user == null) {
            Log.e("UserRepository", "Attempted");
            return;
        }
        executorService.execute(() -> userDao.update(user));
    }

    public LiveData<User> findUserByEmailAndPassword(String email, String password) {
        return userDao.findUserByEmail(email, password);
    }
}
