package com.caloriemate.application.view.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.caloriemate.application.database.entities.User;
import com.caloriemate.application.database.repositories.UserRepository;

public class ProfileDetailsViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    public ProfileDetailsViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public void updateProfile(User user) {
        userRepository.update(user);
    }

    public LiveData<User> getUser(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password);
    }
}