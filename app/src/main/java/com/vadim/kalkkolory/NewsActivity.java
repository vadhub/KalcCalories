package com.vadim.kalkkolory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    private TextView link;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_news, container, false);

        title = (TextView) v.findViewById(R.id.textTitle);
        description = (TextView) v.findViewById(R.id.descriptionNews);
        mainimg = (ImageView) v.findViewById(R.id.app_bar_image);
        link = (TextView) v.findViewById(R.id.link);

        link.setPaintFlags(link.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        Bundle bundle = getArguments();

        titleText = bundle.get("title").toString();
        descriptionText = bundle.get("description").toString();
        urlImg = bundle.get("urlImg").toString();
        url = bundle.get("url").toString();

        title.setText(titleText);
        link.setText("link");

        Picasso.get().load(urlImg).error(R.drawable.not_found).into(mainimg);
        description.setText(descriptionText);

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onInputUrl(url);
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof NewsActivityListener){
            listener = (NewsActivityListener) context;
        }else{
            throw new RuntimeException(context.toString() + " must implement NewsActivity");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}