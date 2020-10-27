package com.vadim.kalkkolory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_news, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(articles.get(position).getUrlToImage()!=null&&articles.get(position).getDescription()!=null){
            Picasso.get().load(articles.get(position).getUrlToImage()).into(holder.imageMain);
            holder.title.setText(articles.get(position).getTitle());
            holder.description.setText(articles.get(position).getDescription());
        }else if(articles.get(position).getUrlToImage()==null){
            holder.title.setText(articles.get(position).getTitle());
            holder.description.setText(articles.get(position).getDescription());
        }else if(articles.get(position).getDescription()==null){
            Picasso.get().load(articles.get(position).getUrlToImage()).into(holder.imageMain);
            holder.description.setText(articles.get(position).getDescription());
        }else if(articles.get(position).getTitle()==null){
            Picasso.get().load(articles.get(position).getUrlToImage()).into(holder.imageMain);
            holder.title.setText(articles.get(position).getTitle());
        }else if(articles.get(position).getTitle()==null&&articles.get(position).getUrlToImage()==null){
            holder.description.setText(articles.get(position).getDescription());
        }



    }

    @Override
    public int getItemCount() {
        return articles.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageMain;
        private TextView title;
        private TextView description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageMain = (ImageView) itemView.findViewById(R.id.imageView);
            title = (TextView) itemView.findViewById(R.id.title_);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
