package com.vadim.kalkkolory;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

public class NewsActivity extends AppCompatActivity {

    private ImageView mainimg;
    private TextView description;
    private TextView title;

    private String url;
    private String urlToImg;
    private String getTitle;
    private String getDescription;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mainimg = (ImageView) findViewById(R.id.app_bar_image);
        description = (TextView) findViewById(R.id.descriptionNews);

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
}