package com.tastey.baking.bakingapp.model;

/**
 * Created by lenovo on 3/7/2018.
 */

public class IngredientModel {
    float quantity;
    String measure;
    String ingredient;

    public IngredientModel(float quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public float getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}
