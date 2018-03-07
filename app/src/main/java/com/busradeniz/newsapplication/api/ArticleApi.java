package com.busradeniz.newsapplication.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ArticleApi {

    @GET("/api/unknown")
    Call<ArticleListApiResponse> getArticleList();

}
