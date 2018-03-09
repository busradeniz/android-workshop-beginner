package com.busradeniz.newsapplication.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.busradeniz.newsapplication.R;
import com.busradeniz.newsapplication.api.ArticleListApiResponse;
import com.busradeniz.newsapplication.api.ArticleService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArticleService articleService = ArticleService.getInstance();
    private ArticleAdapter adapter;
    private RecyclerView articleRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initRecyclerView();
        getArticles();
    }

    private void initRecyclerView() {
        articleRecyclerView = findViewById(R.id.recyclerView);

        adapter = new ArticleAdapter();
        articleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        articleRecyclerView.setAdapter(adapter);
    }

    private void getArticles() {
        articleService.getArticles()
                .enqueue(new Callback<ArticleListApiResponse>() {
                    @Override
                    public void onResponse(Call<ArticleListApiResponse> call, Response<ArticleListApiResponse> response) {
                        Log.i("MainActivity", "response : " + response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<ArticleListApiResponse> call, Throwable t) {
                        Log.e("MainActivity", "getArticle failed: " + t.getLocalizedMessage());
                    }
                });
    }
}
