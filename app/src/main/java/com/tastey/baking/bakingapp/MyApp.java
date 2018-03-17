package com.tastey.baking.bakingapp;

import android.app.Application;
import android.support.test.espresso.IdlingResource;

import com.facebook.stetho.Stetho;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * Created by lenovo on 3/10/2018.
 */

public class MyApp extends Application {

    RecipesIdlingRes recipesIdlingRes;


    public MyApp() {
        initIdlingRes();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Stetho.initializeWithDefaults(this);
    }


    private void initIdlingRes() {
        if (recipesIdlingRes == null) {
            recipesIdlingRes = new RecipesIdlingRes();
        }
    }

    public void setIdleState(boolean isIdle) {
        recipesIdlingRes.setIdleState(isIdle);
    }

    public IdlingResource getIdlingRes() {
        return recipesIdlingRes;
    }
}
