package com.tastey.baking.bakingapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.tastey.baking.bakingapp.model.RecipeModel;

import java.util.List;

/**
 * Created by lenovo on 3/8/2018.
 */

public class MainViewModel extends ViewModel {
    MainRepository repository = null;
    LiveData<List<RecipeModel>> ret_list;

    public LiveData<List<RecipeModel>> getLiveRecipes() {
        if (repository == null) {
            repository = new MainRepository();
             ret_list = repository.getRecipes();
        }
        return ret_list ;
    }
}
