package com.zybooks.finalprojectmod5;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.zybooks.finalprojectmod5.databinding.ActivityHomeBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Home extends AppCompatActivity {

    public DatabaseHelper dbHelper;
    public SharedPreferences sharedPreferences;
    private AppBarConfiguration appBarConfiguration;
    private ActivityHomeBinding binding;
    public FloatingActionButton fab;
    public FloatingActionButton fabData;
    public FloatingActionButton fabSms;
    public EditText goalWeightInput;
    public TableLayout weightsTable;
    private boolean fabExpanded = false;

    public String getUsername() {
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        return sharedPreferences.getString("username", "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DatabaseHelper(this);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        goalWeightInput = this.findViewById(R.id.GoalWeightInput);
        weightsTable = this.findViewById(R.id.weightsTable);

        fab = this.findViewById(R.id.fab);
        fabData = this.findViewById(R.id.fabData);
        fabSms = this.findViewById(R.id.fabSms);

        fabData.setVisibility(View.INVISIBLE);
        fabSms.setVisibility(View.INVISIBLE);;

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fabExpanded = !fabExpanded;

                if (fabExpanded)
                {
                    fabData.setVisibility(View.VISIBLE);
                    fabSms.setVisibility(View.VISIBLE);
                    fab.setImageResource(R.drawable.ic_close);
                }
                else
                {
                    fabData.setVisibility(View.INVISIBLE);
                    fabSms.setVisibility(View.INVISIBLE);
                    fab.setImageResource(R.drawable.ic_add);
                }
            }
        });

        binding.fabData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabExpanded = false;
                fab.setVisibility(View.INVISIBLE);
                fabData.setVisibility(View.INVISIBLE);
                fabSms.setVisibility(View.INVISIBLE);

                Navigation.findNavController(Home.this, R.id.nav_host_fragment_content_home)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.fabSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabExpanded = false;
                fab.setVisibility(View.INVISIBLE);
                fabData.setVisibility(View.INVISIBLE);
                fabSms.setVisibility(View.INVISIBLE);

                Navigation.findNavController(Home.this, R.id.nav_host_fragment_content_home)
                        .navigate(R.id.action_FirstFragment_to_ThirdFragment);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        fab.setImageResource(R.drawable.ic_add);
        fab.setVisibility(View.VISIBLE);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

}