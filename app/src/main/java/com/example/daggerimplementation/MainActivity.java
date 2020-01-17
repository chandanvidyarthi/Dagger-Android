package com.example.daggerimplementation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.daggerimplementation.network.Webservices;

import org.json.JSONObject;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Inject
    Webservices webservices;

    @Inject
    SharedPrefUtil sharedPrefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((BaseApplication) getApplication()).getAppComponent().inject(MainActivity.this);
        apiCall();
        setSharedPref();
        getSharedPref();
    }

    public void getSharedPref(){
        Log.d(MainActivity.class.getSimpleName(),"getPrefData"+sharedPrefUtil.get("key",null));
    }

    public void setSharedPref(){
        sharedPrefUtil.put("key","test");
    }

    public void apiCall() {
        JSONObject jsonObject = new JSONObject();
        webservices.exampleAPICALL(jsonObject).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {

            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {

            }
        });
    }
}
