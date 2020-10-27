package com.vadim.kalkkolory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsLentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles= new ArrayList<>();
    private String apiKey;
    private String category;
    private String country;

    private static final String URL = "http://newsapi.org/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_lent);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        apiKey = getString(R.string.api_key);
        category = getString(R.string.category);
        country = getString(R.string.country);

        getPosts();

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

                }

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(NewsLentActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }

}