package com.tastey.baking.bakingapp;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by lenovo on 3/13/2018.
 */

public class RecipesIdlingRes implements IdlingResource {
    private volatile ResourceCallback callback;
    private AtomicBoolean mIsIdle = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdle.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }

    public void setIdleState(boolean isIdle) {
        mIsIdle.set(isIdle);
        if (isIdle && callback != null) {
            callback.onTransitionToIdle();
        }
    }
}
