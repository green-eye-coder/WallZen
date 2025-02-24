package com.example.myapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//================================================
//==== Create ApiUtilities.java in same package and add your Pexels API Key====
//================================================
public class ApiUtilitiesExample {
    private static Retrofit retrofit=null;
    public static final String Api="Add your api key here";

    public static ApiInterface getApiInterface(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(ApiInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit.create(ApiInterface.class);
    }
}