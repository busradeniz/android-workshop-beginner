package com.busradeniz.newsapplication.api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleService {

    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String COUNTRY = "gb";
    private static final String CATEGORY = "business";
    private static final String API_KEY = "6b195c9d219f4487a12a02ea8085d798";
    private static ArticleService instance = null;

    private ArticleApi articleApi;

    public static ArticleService getInstance() {
        if (instance == null) {
            instance = new ArticleService();
        }
        return instance;
    }

    private ArticleService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        articleApi = retrofit.create(ArticleApi.class);

    }

    public Call<ArticleListApiResponse> getArticles() {
        return articleApi.getArticleList(COUNTRY, CATEGORY, API_KEY);
    }
}
