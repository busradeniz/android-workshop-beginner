package com.busradeniz.newsapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.busradeniz.newsapplication.api.ArticleListApiResponse;
import com.busradeniz.newsapplication.api.ArticleService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<ArticleListApiResponse> {

    private ArticleService articleService = ArticleService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getArticles();
    }

    private void getArticles() {
        articleService.getArticles()
                .enqueue(this);
    }

    @Override
    public void onResponse(Call<ArticleListApiResponse> call, Response<ArticleListApiResponse> response) {
        Log.i("MainActivity", "response : " + response.body().toString());
    }

    @Override
    public void onFailure(Call<ArticleListApiResponse> call, Throwable t) {
        Log.e("MainActivity", "getArticle failed: " + t.getLocalizedMessage());
    }
}
