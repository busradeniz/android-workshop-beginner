package com.busradeniz.newsapplication.api;

import retrofit2.Retrofit;

public class ArticleService {

    private static final String BASE_URL = "https://newsapi.org/v2/top-headlines";
    private static ArticleService instance = null;

    private Retrofit retrofit;

    public static ArticleService getInstance() {
        if (instance == null) {
            instance = new ArticleService();
        }
        return instance;
    }

    private ArticleService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }
}
