package com.example.retrofit.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.retrofit.R;
import com.example.retrofit.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupNavigation();

    }

    private void setupNavigation() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);

//        switch (item.getItemId()) {
//            case R.id.navigation_home:
//                chara(firstFragment);
//                return true;
//            case R.id.navigation_dashboard:
//                changeFragment(secondFragment);
//                return true;
//        }
//        return false;
//    }

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.characterFragment,
                R.id.locationFragment,
                R.id.episodeFragment
        ).build();
    }
}