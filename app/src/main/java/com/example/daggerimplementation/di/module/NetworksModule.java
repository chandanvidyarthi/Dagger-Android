package com.example.daggerimplementation.di.module;

import android.app.Application;

import com.example.daggerimplementation.di.component.ResponseInterceptor;
import com.example.daggerimplementation.network.Webservices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworksModule {

    @Singleton
    @Provides
    public Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }


    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache, HttpLoggingInterceptor httpLoggingInterceptor, final ResponseInterceptor interceptor) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        client.addInterceptor(httpLoggingInterceptor);
        client.addInterceptor(interceptor);
        client.readTimeout(30, TimeUnit.SECONDS);
        client.connectTimeout(30, TimeUnit.SECONDS);
        /*    client.callTimeout(30, TimeUnit.SECONDS);*/
        //session expiry
//        client.interceptors().add(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//
//                Request request = chain.request();
//                okhttp3.Response response = chain.proceed(request);
//
//                if (response.code() == 401) {
//                    Intent intent = new Intent();
//                    intent.setAction("com.netree.foop.SESSION_LISTENER");
//                    application.sendBroadcast(intent);
////                    return response;
//                }
//                return response;
//            }
//        });
        return client.build();
    }
    @Singleton
    @Provides
    public Retrofit provideRetrofit(Gson gson) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.hopequre.com/APITEST/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        return retrofit;
    }
    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
    @Singleton
    @Provides
    ResponseInterceptor interceptor(Application application) {
        return new ResponseInterceptor(application.getApplicationContext());
    }


    @Singleton
    @Provides
    Webservices provideServices(Retrofit retrofit) {
        return retrofit.create(Webservices.class);
    }

}

