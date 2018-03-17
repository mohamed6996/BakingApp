package com.tastey.baking.bakingapp.widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.tastey.baking.bakingapp.Constants;

/**
 * Created by lenovo on 3/10/2018.
 */

public class UpdateService extends IntentService {
    public UpdateService() {
        super("UpdateService");
    }

    //todo move this method to detail fragment
    public static void startUpdating(Context context, String recipeJson) {
        Intent intent = new Intent(context, UpdateService.class);
        intent.putExtra(Constants.INGREDIENT_WIDGET, recipeJson);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            String recipe_json = intent.getStringExtra(Constants.INGREDIENT_WIDGET);
            Intent newIntent = new Intent("android.appwidget.action.APPWIDGET_UPDATE_2");
            newIntent.putExtra(Constants.INGREDIENT_WIDGET, recipe_json);
            sendBroadcast(newIntent);
        }
    }
}
