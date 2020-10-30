package com.vadim.kalkkolory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigateActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigate);

        navigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        navigationView.setOnNavigationItemSelectedListener(navSelect);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navSelect = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()){
                case R.id.calculate:
                    fragment = new MainActivity();
                    break;
                case R.id.news:
                    fragment = new NewsLentActivity();
                    break;
                case R.id.information:
                    fragment = new Informer();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragment).commit();
            return true;
        }
    };
}