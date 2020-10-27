package com.vadim.kalkkolory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderAPI {
    @GET("v2/top-headlines")
    Call<News> geNews(@Query("country") String country,@Query("category") String category, @Query("apiKey") String apiKey);
}
