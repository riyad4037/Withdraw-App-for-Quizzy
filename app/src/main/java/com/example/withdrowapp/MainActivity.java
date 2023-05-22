package com.example.withdrowapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.BottomNavigationBarView);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.Contents, new HomeFragment());
        transaction.commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                HomeFragment homeFragment = new HomeFragment();

                WithdrawFragment walletFragment = new WithdrawFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.HomeItem:
                        transaction.replace(R.id.Contents, homeFragment);
                        transaction.commit();
                        break;
                    case R.id.WithdrawItem:
                        transaction.replace(R.id.Contents, walletFragment);
                        transaction.commit();
                        break;
                }
                return true;
            }
        });

    }
}