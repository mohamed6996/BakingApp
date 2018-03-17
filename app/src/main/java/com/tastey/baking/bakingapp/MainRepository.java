package com.tastey.baking.bakingapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.tastey.baking.bakingapp.api.ApiClient;
import com.tastey.baking.bakingapp.api.ApiInterface;
import com.tastey.baking.bakingapp.model.RecipeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lenovo on 3/8/2018.
 */

public class MainRepository {
    MutableLiveData<List<RecipeModel>> mutableLiveData = new MutableLiveData<>();


    public LiveData<List<RecipeModel>> getRecipes() {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        apiInterface.getRecipes().enqueue(new Callback<List<RecipeModel>>() {


            @Override
            public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());

                }

            }

            @Override
            public void onFailure(Call<List<RecipeModel>> call, Throwable t) {
            }
        });

        return mutableLiveData;
    }
}
