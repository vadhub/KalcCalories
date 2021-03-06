package com.vadim.kalkkolory;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigateActivity extends AppCompatActivity implements FragmentNewsLentListener, NewsActivityListener{

    private BottomNavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigate);

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
                case R.id.information:
                    fragment = new Informer();
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragment).commit();
            return true;
        }
    };

    @Override
    public void onInput(CharSequence charSequence, CharSequence description, CharSequence urlImg, CharSequence url) {
        NewsActivity newsActivity = new NewsActivity();

        Bundle bundle = new Bundle();
        bundle.putString("title", (String) charSequence);
        bundle.putString("description", (String) description);
        bundle.putString("urlImg", (String) urlImg);
        bundle.putString("url", (String) url);


        newsActivity.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, newsActivity).commit();

    }

    @Override
    public void onInputUrl(CharSequence url) {
        WebviewFragment webviewFragment = new WebviewFragment();

        Bundle bundle = new Bundle();
        bundle.putString("url_link",(String) url);

        webviewFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, webviewFragment).commit();

    }
}