package com.example.proyectoseminario.retrofit_data;

import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApiService {
    @GET("db")
    Call<JsonObject> getDbItems();
}



