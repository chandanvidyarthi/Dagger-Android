package com.example.daggerimplementation.network;


import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface Webservices {

    @POST("END_POINTS")
    Call<JSONObject> exampleAPICALL(@Body JSONObject jsonObject);

}
