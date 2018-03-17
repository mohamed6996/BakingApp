package com.tastey.baking.bakingapp.model;

import java.util.List;

/**
 * Created by lenovo on 3/7/2018.
 */

public class RecipeModel {
    private int id;
    private String name;
    private List<IngredientModel> ingredients;
    private List<StepModel> steps;

    public RecipeModel(int id, String name, List<IngredientModel> ingredients, List<StepModel> steps) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<IngredientModel> getIngredients() {
        return ingredients;
    }

    public List<StepModel> getSteps() {
        return steps;
    }
}
