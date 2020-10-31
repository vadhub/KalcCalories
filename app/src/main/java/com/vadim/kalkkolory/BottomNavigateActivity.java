package com.vadim.kalkkolory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigateActivity extends AppCompatActivity implements NewsLentActivity.FragmentNewsLentListener {

    private BottomNavigationView navigationView;

    private NewsActivity newsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigate);

        newsActivity = new NewsActivity();
        navigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        navigationView.setOnNavigationItemSelectedListener(navSelect);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new MainActivity()).commit();


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
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragment).commit();
            return true;
        }
    };

    @Override
    public void onInput(CharSequence charSequence) {
        newsActivity.updateText(charSequence);
    }
}