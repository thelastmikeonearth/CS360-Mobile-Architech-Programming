package com.zybooks.finalprojectmod5;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.zybooks.finalprojectmod5.databinding.FragmentSecondBinding;
import com.zybooks.finalprojectmod5.databinding.FragmentThirdBinding;


public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;
    private String username;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        username = ((Home)getActivity()).getUsername();
        binding = FragmentThirdBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        var previousGoal = ((Home)getActivity()).dbHelper.getGoal(username);
        if (previousGoal != -1) {
            binding.GoalWeightInput.setText(String.valueOf(previousGoal));
        }

        binding.SetGoalButton.setOnClickListener(v ->
        {
            // Set goal in db
            ((Home)getActivity()).dbHelper.updateGoal(Double.parseDouble(binding.GoalWeightInput.getText().toString()), username);

            // Navigate home
            NavHostFragment.findNavController(ThirdFragment.this)
                    .navigate(R.id.action_ThirdFragment_to_FirstFragment);
            ((Home)getActivity()).fab.setVisibility(View.VISIBLE);
            ((Home)getActivity()).fab.setImageResource(R.drawable.ic_add);
        });

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.SEND_SMS}, 111);
        }

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 111);
        }

    }
}