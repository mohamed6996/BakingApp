package com.tastey.baking.bakingapp.db;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by lenovo on 3/10/2018.
 */

@ContentProvider(authority = RecipeProvider.AUTHORITY, database = RecipeDatabase.class)
public class RecipeProvider {
    public static final String AUTHORITY = "com.tastey.baking.bakingapp.db.RecipeProvider";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    interface Path {
        String RECIPES = "recipes";
    }

    private static Uri buildUri(String... paths) {
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path : paths) {
            builder.appendPath(path);
        }
        return builder.build();
    }

    @TableEndpoint(table = RecipeDatabase.RECIPES)
    public static class Recipes {

        @ContentUri(
                path = Path.RECIPES,
                type = "vnd.android.cursor.dir/recipe",
                defaultSort = RecipeColumns.RECIPES_JSON + " ASC")
        public static final Uri CONTENT_URI = buildUri(Path.RECIPES);

        @InexactContentUri(
                name = "RECIPE_ID",
                path = Path.RECIPES + "/*",
                type = "vnd.android.cursor.item/recipe",
                whereColumn = RecipeColumns.RECIPES_JSON,
                pathSegment = 1)
        public static Uri withId(long id) {
            return buildUri(Path.RECIPES, String.valueOf(id));
        }
    }

}
