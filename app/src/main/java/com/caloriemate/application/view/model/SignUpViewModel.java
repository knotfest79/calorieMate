package com.caloriemate.application.view.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.caloriemate.application.database.entities.User;
import com.caloriemate.application.database.repositories.UserRepository;
public class SignUpViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    public SignUpViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public void signUp(User user) {
        userRepository.insert(user);
    }
}