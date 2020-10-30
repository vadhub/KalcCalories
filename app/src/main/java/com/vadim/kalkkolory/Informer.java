package com.vadim.kalkkolory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.squareup.picasso.Picasso;

public class Informer extends FragmentActivity {

    private View itemMenuNews;
    private View itemMenuMain;
    private View itemMenuInfo;
 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informer);

        itemMenuNews = (View) findViewById(R.id.news);
        itemMenuInfo = (BottomNavigationItemView) findViewById(R.id.information);
        itemMenuMain = (View) findViewById(R.id.calculate);

        itemMenuMain.setOnClickListener(v -> onClickReverse(v));
        itemMenuNews.setOnClickListener(v -> toNews(v));


    }
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onStart() {
        super.onStart();
    }

    private void toNews(View v) {
        Intent intent = new Intent(this, NewsLentActivity.class);
        startActivity(intent);
    }

    public void onClickReverse(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}