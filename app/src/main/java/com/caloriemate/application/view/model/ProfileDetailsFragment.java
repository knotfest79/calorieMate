package com.caloriemate.application.view.model;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.caloriemate.application.R;
import com.caloriemate.application.database.entities.User;
import com.caloriemate.application.databinding.ProfileDetailsFragmentBinding;
import com.caloriemate.application.databinding.ProfileFragmentBinding;

import java.util.Objects;

public class ProfileDetailsFragment extends Fragment {

    private ProfileDetailsViewModel mViewModel;
    private ProfileDetailsFragmentBinding binding;
    private User user = new User();;

    public static ProfileDetailsFragment newInstance() {
        return new ProfileDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ProfileDetailsFragmentBinding.inflate(inflater, container, false);
        Spinner genderSpinner = binding.spinnerGender;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.gender_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileDetailsViewModel.class);
        NavController navController = NavHostFragment.findNavController(this);
        binding.btnProfileNext.setEnabled(false);
        // Retrieve arguments
        if (getArguments() != null) {
            String userEmail = ProfileDetailsFragmentArgs.fromBundle(getArguments()).getUserEmail();
            String userPassword = ProfileDetailsFragmentArgs.fromBundle(getArguments()).getUserPassword();
            mViewModel.getUser(userEmail, userPassword).observe(getViewLifecycleOwner(), user1 -> {
                user = user1;
                binding.btnProfileNext.setEnabled(true);
            });
        }

        binding.btnProfileNext.setOnClickListener(v -> {
            boolean formIsValid = validateForm();
            if (formIsValid) {
                user.DOB = Objects.requireNonNull(binding.etDateOfBirth.getText()).toString();
                user.Gender = binding.spinnerGender.getSelectedItem().toString();
                user.Height = Integer.valueOf(Objects.requireNonNull(binding.heightTextInputLayout.getEditText()).getText().toString());
                user.Weight = Integer.valueOf(Objects.requireNonNull(binding.weightTextInputLayout.getEditText()).getText().toString());
                mViewModel.updateProfile(user);
                navController.navigate(R.id.action_profileDetailsFragment_to_menuFragment);
            } else {
                Toast.makeText(getContext(), "Please, fill in all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateForm() {
        //validate gender
        if (binding.spinnerGender.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), "Please select a gender", Toast.LENGTH_SHORT).show();
            return false;
        }

        // validate date of birth
        String dob = binding.etDateOfBirth.getText().toString();
        if (dob.isEmpty() || !dob.matches("\\d{2}\\d{2}\\d{4}")) {
            Toast.makeText(getContext(), "Enter a valid date of birth (DDMMYYYY)", Toast.LENGTH_SHORT);
            return false;
        }

        // validate weight
        String weight = binding.weightTextInputLayout.getEditText().getText().toString();
        if (weight.isEmpty()) {
            Toast.makeText(getContext(), "Please enter your weight", Toast.LENGTH_SHORT).show();
            return false;
        }

        // validate height
        String height = binding.heightTextInputLayout.getEditText().toString();
        if (height.isEmpty()) {
            Toast.makeText(getContext(), "Please enter your height", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}