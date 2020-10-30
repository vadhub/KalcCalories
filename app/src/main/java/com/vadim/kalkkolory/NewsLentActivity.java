package com.vadim.kalkkolory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsLentActivity extends Fragment {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles= new ArrayList<>();
    private String apiKey;
    private String category;
    private String country;

    private FragmentNewsLentListener listener;


    private static final String URL = "http://newsapi.org/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_news_lent, container, false);


        recyclerView = (RecyclerView) v.findViewById(R.id.recycler);

        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        apiKey = getString(R.string.api_key);
        category = getString(R.string.category);
        country = getString(R.string.country);

        getPosts();

        return v;
    }



    private void getPosts(){
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL).build();
        JsonPlaceHolderAPI inter= retrofit.create(JsonPlaceHolderAPI.class);
        Call<News> call = inter.geNews(country, category, apiKey);

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(response.isSuccessful()&&response.body().getArticles()!=null){
                    if(articles!=null){
                        articles.clear();
                    }
                    adapter = new Adapter();
                    articles = response.body().getArticles();
                    adapter.setArticles(articles);
                    recyclerView.setAdapter(adapter);

                    initListener();

                }

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }

    interface FragmentNewsLentListener{
        void onInput(CharSequence charSequence);
    }

    private void initListener(){
        adapter.setOnItemClickListenerNews(new Adapter.OnItemClickListenerNews() {
            @Override
            public void onClickItem(View view, int position) {

                Article article = articles.get(position);
                CharSequence input =  article.getTitle();
                listener.onInput(input);
//                intent.putExtra("url", article.getUrl());
//                intent.putExtra("urtImg", article.getUrlToImage());
//                intent.putExtra("title",);
//                intent.putExtra("content", article.getContent());
//
//                startActivity(intent);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof FragmentNewsLentListener){
            listener = (FragmentNewsLentListener) context;
        }else{
            throw new RuntimeException(context.toString() + " must implement NewsLentActivity");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}