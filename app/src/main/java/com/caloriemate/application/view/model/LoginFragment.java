package com.caloriemate.application.view.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.caloriemate.application.R;
import com.caloriemate.application.database.entities.User;
import com.caloriemate.application.databinding.LoginFragmentBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private LoginFragmentBinding binding;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LoginFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        Button loginButton = binding.btnLogin;
        TextView signUpTextView = binding.tvSignUp;
        TextInputEditText emailEditText = view.findViewById(R.id.tvEmailInputText);
        TextInputEditText passwordEditText = view.findViewById(R.id.tvPasswordInputText);
        NavController navController = NavHostFragment.findNavController(this);
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            System.out.println(password);
            if (!isValidEmail(email)) {
                Toast.makeText(getContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
            } else if (!isValidPassword(password)) {
                Toast.makeText(getContext(), "Password validation failed", Toast.LENGTH_SHORT).show();
            } else {
                mViewModel.login(email, password).observe(getViewLifecycleOwner(), user -> {
                    if (user != null) {
                        navController.navigate(R.id.action_loginFragment_to_menuFragment);
                    } else {
                        Toast.makeText(getContext(), "Login failed: Check your credentials", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        signUpTextView.setOnClickListener(v -> {
            navController.navigate(R.id.action_loginFragment_to_signUpFragment);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            BottomNavigationView bottomAppBar = activity.findViewById(R.id.bottomNavigationView);
            if (bottomAppBar != null) {
                bottomAppBar.setVisibility(View.INVISIBLE);
            }
        }
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}