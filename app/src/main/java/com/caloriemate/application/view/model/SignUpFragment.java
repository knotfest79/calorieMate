package com.caloriemate.application.view.model;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.caloriemate.application.R;
import com.caloriemate.application.database.entities.User;
import com.caloriemate.application.databinding.SignUpFragmentBinding;

public class SignUpFragment extends Fragment {

    private SignUpViewModel mViewModel;
    private SignUpFragmentBinding binding;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SignUpFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        NavController navController = NavHostFragment.findNavController(this);
        EditText emailEditText = binding.emailTextInput.getEditText();
        EditText passwordEditText = binding.passwordTextInput.getEditText();
        EditText repeatPasswordEditText = binding.repeatPasswordTextInput.getEditText();

        binding.btnSignUp.setOnClickListener(v -> {
            if (!isValidEmail(emailEditText.getText().toString())) {
                Toast.makeText(getContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
            } else if (!isValidPassword(passwordEditText.getText().toString(), repeatPasswordEditText.getText().toString())) {
                Toast.makeText(getContext(), "Password validation failed", Toast.LENGTH_SHORT).show();
            } else {
                User user = new User();
                user.Email = emailEditText.getText().toString();
                user.Password = passwordEditText.getText().toString();
                mViewModel.signUp(user);

                // Navigate with Safe Args
                SignUpFragmentDirections.ActionSignUpFragmentToProfileDetailsFragment action = SignUpFragmentDirections.actionSignUpFragmentToProfileDetailsFragment(user.Email, user.Password);
                navController.navigate(action);
            }
        });

    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password, String repeatPassword) {
        return password != null && password.equals(repeatPassword) && password.length() >= 6;
    }
}