package com.tastey.baking.bakingapp.db;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by lenovo on 3/10/2018.
 */

@Database(version = RecipeDatabase.VERSION)
public class RecipeDatabase {
    private RecipeDatabase() {
    }

    public static final int VERSION = 1;

    @Table(RecipeColumns.class)
    public static final String RECIPES = "recipes";
}
