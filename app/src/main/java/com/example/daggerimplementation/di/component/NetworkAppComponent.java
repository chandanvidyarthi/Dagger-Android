package com.example.daggerimplementation.di.component;

import com.example.daggerimplementation.MainActivity;
import com.example.daggerimplementation.di.module.AppModule;
import com.example.daggerimplementation.di.module.NetworksModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworksModule.class})
public interface NetworkAppComponent {
    void inject(MainActivity mainActivity);
}
