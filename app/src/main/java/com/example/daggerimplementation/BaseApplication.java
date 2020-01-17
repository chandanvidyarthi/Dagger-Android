package com.example.daggerimplementation;

import android.app.Application;
import android.content.Context;

import com.example.daggerimplementation.di.component.DaggerNetworkAppComponent;
import com.example.daggerimplementation.di.component.NetworkAppComponent;
import com.example.daggerimplementation.di.module.AppModule;
import com.example.daggerimplementation.di.module.NetworksModule;


public class BaseApplication extends Application {
    private NetworkAppComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        networkComponent =  DaggerNetworkAppComponent.builder()
                .appModule(new AppModule(this))
                .networksModule(new NetworksModule())
                .build();
    }

    public NetworkAppComponent getAppComponent() {
        return networkComponent;
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
