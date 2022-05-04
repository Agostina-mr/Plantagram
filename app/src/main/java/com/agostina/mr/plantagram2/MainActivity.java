package com.agostina.mr.plantagram2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.agostina.mr.plantagram2.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private NavController navController;
    private boolean isFabVisible;
    private static Context context;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity.context = getApplicationContext();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        checkIfSignedIn();
        binding.appBarMain.fab.setVisibility(View.VISIBLE);
        isFabVisible = true;



        binding.appBarMain.fab
                .setOnClickListener(v->{
                    if (isFabVisible){
                        binding.appBarMain.fab.setVisibility(View.INVISIBLE);
                        navController.navigate(R.id.camera);
                    }
                });


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        binding.appBarMain.fab.setVisibility(View.VISIBLE);
        isFabVisible = true;
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public static Context getContext(){
        return MainActivity.getContext();
    }
    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
              //  viewModel.init();
                navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            } else
                startLoginActivity();
        });
    }

    private void startLoginActivity() {
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }
}

