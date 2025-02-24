package com.example.myapplication;

import static com.example.myapplication.ApiUtilities.Api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public interface ApiInterface {

    String BASE_URL="https://api.pexels.com/v1/";
    @Headers("Authorization: "+Api)
    @GET("curated")
    Call<SearchModel> getImage(
            @Query("page") int page,
            @Query("per_page") int per_page
    );

    @Headers("Authorization: "+Api)
    @GET("search")
    Call<SearchModel> getSearchImage(
            @Query("query") String query,
            @Query("page") int page,
            @Query("per_page") int per_page
    );
}
