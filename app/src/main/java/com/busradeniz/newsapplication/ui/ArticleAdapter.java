package com.busradeniz.newsapplication.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.busradeniz.newsapplication.R;
import com.busradeniz.newsapplication.api.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private List<Article> articleList = new ArrayList<>();

    @NonNull
    @Override
    public ArticleAdapter.ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article, parent, false);

        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.title.setText(article.getTitle());

        Glide.with(holder.itemView.getContext())
                .load(article.getUrlToImage())
                .into(holder.imgArticle);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }


    public void updateDataSet(List<Article> list) {
        articleList.addAll(list);
    }

    protected class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView imgArticle;

        ArticleViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.txtTitle);
            imgArticle = view.findViewById(R.id.imgArticle);
        }
    }

}
