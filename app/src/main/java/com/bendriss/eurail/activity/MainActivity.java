package com.bendriss.eurail.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.bendriss.eurail.R;
import com.bendriss.eurail.fragment.CountdownFragment;
import com.bendriss.eurail.fragment.MapFragment;
import com.bendriss.eurail.fragment.StorageFragment;
import com.bendriss.eurail.fragment.WebViewFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout, new MapFragment()).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fm = getSupportFragmentManager();
                switch (item.getItemId()) {
                    case R.id.action_map:
                        fm.beginTransaction().replace(R.id.mainFrameLayout, new MapFragment()).commit();
                        getSupportActionBar().setTitle(getResources().getString(R.string.map));
                        break;
                    case R.id.action_loading:
                        fm.beginTransaction().replace(R.id.mainFrameLayout, new CountdownFragment()).commit();
                        getSupportActionBar().setTitle(getResources().getString(R.string.loading));
                        break;
                    case R.id.action_storage:
                        fm.beginTransaction().replace(R.id.mainFrameLayout, new StorageFragment()).commit();
                        getSupportActionBar().setTitle(getResources().getString(R.string.text_storage));
                        break;
                    case R.id.action_webview:
                        fm.beginTransaction().replace(R.id.mainFrameLayout, new WebViewFragment()).commit();
                        getSupportActionBar().setTitle(getResources().getString(R.string.webview));
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}