package com.example.buildcollab.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.buildcollab.R;
import com.example.buildcollab.fragments.ChatFragment;
import com.example.buildcollab.fragments.GroupFragment;
import com.example.buildcollab.fragments.HomeFragment;
import com.example.buildcollab.fragments.ProjectFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();

        bottomNav.setSelectedItemId(R.id.nav_home);

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_group:
                        fragment = new GroupFragment();
                        break;
                    case R.id.nav_chat:
                        fragment = new ChatFragment();
                        break;

                    case R.id.nav_project:
                        fragment = new ProjectFragment();
                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();

                return true;
            }

        });

    }


}