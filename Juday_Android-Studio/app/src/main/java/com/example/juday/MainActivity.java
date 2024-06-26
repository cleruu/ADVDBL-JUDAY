package com.example.juday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.juday.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private final dbConnection user = null;

    private ActivityMainBinding binding;
    boolean firstBoot = true;
    boolean admin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String clearance_lvl = intent.getStringExtra("clearance_lvl");

        if (clearance_lvl != null && !clearance_lvl.isEmpty()){
            firstBoot = false;

            if (clearance_lvl.equals("admin")) admin = true;
        }


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (firstBoot) replaceFragment(new LoginFragment()); // Start the login page if first boot up
        else {
            binding.bottomNavbar.setBackground(null);
            if (admin) {
                replaceFragment(new admin_home());
                binding.bottomNavbar.setOnItemSelectedListener(item -> {
                    int itemId = item.getItemId();
                    Log.d("MenuItemSelected", "Item ID: " + itemId);

                    if (itemId == R.id.customize) {
                        replaceFragment(new admin_open_order());
                    } else if (itemId == R.id.home) {
                        replaceFragment(new admin_home());
                    } else if (itemId == R.id.stocks) {
                        replaceFragment(new admin_database());
                    } else {
                        return false;
                    }
                    return true;
                });
            }
            else {
                replaceFragment(new Home_Fragment());
                binding.bottomNavbar.setOnItemSelectedListener(item -> {
                    int itemId = item.getItemId();
                    Log.d("MenuItemSelected", "Item ID: " + itemId);

                    if (itemId == R.id.customize) {
                        replaceFragment(new Customize_Fragment());
                    } else if (itemId == R.id.home) {
                        replaceFragment(new Home_Fragment());
                    } else if (itemId == R.id.stocks) {
                        replaceFragment(new Stocks_Fragment());
                    } else {
                        return false;
                    }
                    return true;
                });
            }

        }



    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}
