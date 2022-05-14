package com.agostina.mr.plantagram2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.databinding.ActivityMainBinding;
import com.agostina.mr.plantagram2.ui.signin.SignInActivity;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private NavController navController;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        checkIfSignedIn();



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_logout:
               viewModel.signOut();
                return true;
            case R.id.action_settings:
              //  Navigation.findNavController(this, R.id.users_profile_fragment);
                navController.navigate(R.id.users_profile_fragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                Toast.makeText(this, "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                viewModel.init();
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

