package com.example.myapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {
    private static Retrofit retrofit=null;
    public static final String Api="lDMukFwhMwDuiZypG3d0GDvn7dxCZhWXJyvADbdtwdKOdTUgDbw30uz0";

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