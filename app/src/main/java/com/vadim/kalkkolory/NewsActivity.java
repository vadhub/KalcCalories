package com.vadim.kalkkolory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

public class NewsActivity extends Fragment {

    private ImageView mainimg;
    private TextView description;
    private TextView title;

    private String titleText;
    private String descriptionText;
    private String urlImg;
    private String url;

    private NewsActivityListener listener;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_news, container, false);

        title = (TextView) v.findViewById(R.id.textTitle);
        description = (TextView) v.findViewById(R.id.descriptionNews);
        mainimg = (ImageView) v.findViewById(R.id.app_bar_image);

        Bundle bundle = getArguments();

        titleText = bundle.get("title").toString();
        descriptionText = bundle.get("description").toString();
        urlImg = bundle.get("urlImg").toString();
        url = bundle.get("url").toString();

        title.setText(titleText);

        Picasso.get().load(urlImg).error(R.drawable.not_found).into(mainimg);
        description.setText(descriptionText);

        return v;
    }

}