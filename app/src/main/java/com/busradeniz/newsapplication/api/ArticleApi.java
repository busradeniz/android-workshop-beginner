package com.busradeniz.newsapplication.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleApi {

    @GET("top-headlines")
    Call<ArticleListApiResponse> getArticleList(@Query("country") String country, @Query("category") String category,
                                                @Query("apiKey") String apiKey);

}
