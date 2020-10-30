package com.vadim.kalkkolory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

public class NewsActivity extends FragmentActivity {

    private ImageView mainimg;
    private TextView description;
    private TextView title;

    private View itemMenuNews;
    private View itemMenuInfo;
    private View itemMenuMain;

    private String url;
    private String urlToImg;
    private String getTitle;
    private String getDescription;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        itemMenuNews = (View) findViewById(R.id.news);
        itemMenuInfo = (View) findViewById(R.id.information);
        itemMenuMain = (View) findViewById(R.id.calculate);

        mainimg = (ImageView) findViewById(R.id.app_bar_image);
        description = (TextView) findViewById(R.id.descriptionNews);

        itemMenuInfo.setOnClickListener(v -> toRefer(v));
        itemMenuMain.setOnClickListener(v -> toMain(v));
        itemMenuNews.setOnClickListener(v -> toNews(v));

        title = (TextView) findViewById(R.id.textTitle);

        Intent intent = getIntent();

        url = intent.getStringExtra("url");
        urlToImg = intent.getStringExtra("urtImg");
        getTitle = intent.getStringExtra("title");
        getDescription = intent.getStringExtra("content");

        Picasso.get().load(urlToImg).error(R.drawable.not_found).into(mainimg);
        if(getTitle!=null){
            title.setText(getTitle);
        }

        description.setText(getDescription);

    }

    private void toNews(View v) {
        Intent intent = new Intent(this, NewsLentActivity.class);
        startActivity(intent);
    }

    public void toRefer(View view) {
        Intent intent = new Intent(this, Informer.class);
        startActivity(intent);
    }

    public void toMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}