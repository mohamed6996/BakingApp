package com.tastey.baking.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import com.tastey.baking.bakingapp.Constants;
import com.tastey.baking.bakingapp.R;


public class RecipeWidgetProvider extends AppWidgetProvider {

    public static String Recipe_json;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);
        // Set up the collection
        setRemoteAdapter(context, views);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    public static void updateIngredients(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // called when a new widget is created as well as every update interval
        // for (int appWidgetId : appWidgetIds) {
        // updateAppWidget(context, appWidgetManager, appWidgetId);
        //        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, RecipeWidgetProvider.class));

        String intentAction = intent.getAction();
        if (intentAction.equals("android.appwidget.action.APPWIDGET_UPDATE_2")) {
            Recipe_json = intent.getStringExtra(Constants.INGREDIENT_WIDGET);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);

            RecipeWidgetProvider.updateIngredients(context, appWidgetManager, appWidgetIds);
            super.onReceive(context, intent);
        }
    }


    private static void setRemoteAdapter(Context context, @NonNull final RemoteViews views) {
        views.setRemoteAdapter(R.id.widget_list,
                new Intent(context, WidgetService.class));
    }


}

