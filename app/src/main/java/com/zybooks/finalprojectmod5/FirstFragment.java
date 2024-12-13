package com.zybooks.finalprojectmod5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.zybooks.finalprojectmod5.databinding.FragmentFirstBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refreshWeightTable();
    }

    private void refreshWeightTable() {
        var weights = ((Home)getActivity()).dbHelper.getWeights(((Home)getActivity()).getUsername());

        // Add weights to the table layout
        for (int i = 0; i < weights.length; i++) {
            TableRow row = new TableRow(getActivity());
            String date = (new SimpleDateFormat("MM/dd/yyyy")).format(new Date(weights[i].first));
            String weight = String.valueOf(weights[i].second) + " lbs";

            TextView dateView = new TextView(getActivity());
            dateView.setText(date);
            dateView.setWidth(340);
            dateView.setPadding(10, 10, 10, 10);
            dateView.setTextSize(16);

            TextView weightView = new TextView(getActivity());
            weightView.setText(weight);
            weightView.setWidth(340);
            weightView.setPadding(10, 10, 10, 10);
            weightView.setTextSize(16);

            Button deleteButton = new Button(getActivity());
            deleteButton.setText("Delete");
            deleteButton.setWidth(140);
            deleteButton.setPadding(10, 10, 10, 10);
            deleteButton.setTextSize(16);
            deleteButton.setId(i);

            int finalI = i;
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((Home)getActivity()).dbHelper.deleteWeight(weights[finalI].first, ((Home)getActivity()).getUsername());
                    binding.weightsTable.removeAllViews();
                    refreshWeightTable();
                }
            });

            row.addView(dateView);
            row.addView(weightView);
            row.addView(deleteButton);
            binding.weightsTable.addView(row);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}