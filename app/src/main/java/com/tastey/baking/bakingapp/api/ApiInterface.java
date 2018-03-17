package com.tastey.baking.bakingapp.api;

import com.tastey.baking.bakingapp.model.RecipeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lenovo on 3/7/2018.
 */

public interface ApiInterface {

    @GET("baking.json")
    Call<List<RecipeModel>> getRecipes();
}
