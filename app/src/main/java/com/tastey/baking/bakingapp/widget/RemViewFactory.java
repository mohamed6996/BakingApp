package com.tastey.baking.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.tastey.baking.bakingapp.model.IngredientModel;
import com.tastey.baking.bakingapp.model.RecipeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 3/10/2018.
 */

// acts as the adapter for your widget list
public class RemViewFactory implements RemoteViewsService.RemoteViewsFactory {
    List<String> mCollection = new ArrayList<>();
    Context mContext = null;
    Intent intent;
    String recipe_json;


    public RemViewFactory(Context mContext, Intent intent) {
        this.mContext = mContext;
        this.intent = intent;
    }

    private void initData()

    {
        try {
            if (!recipe_json.isEmpty()) {
                RecipeModel recipeModel = new Gson().fromJson(recipe_json, RecipeModel.class);
                List<IngredientModel> ingredientModels = recipeModel.getIngredients();
                mCollection.clear();
                for (int i = 0; i < ingredientModels.size(); i++) {
                    mCollection.add(ingredientModels.get(i).getIngredient() + " " + ingredientModels.get(i).getQuantity() + " " + ingredientModels.get(i).getMeasure());
                }
            }
        } catch (Exception e) {
            Log.e("w_erroe", e.getMessage());
        }


    }

    @Override
    public void onCreate() {
        // initData();
    }

    @Override
    public void onDataSetChanged() {
        this.recipe_json = RecipeWidgetProvider.Recipe_json;
        initData();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mCollection.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews view = new RemoteViews(mContext.getPackageName(),
                android.R.layout.simple_list_item_1);
        view.setTextViewText(android.R.id.text1, mCollection.get(position));
        view.setTextColor(android.R.id.text1, Color.BLACK);
        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
