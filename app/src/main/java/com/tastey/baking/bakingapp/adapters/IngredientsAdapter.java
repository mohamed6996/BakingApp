package com.tastey.baking.bakingapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tastey.baking.bakingapp.R;
import com.tastey.baking.bakingapp.model.IngredientModel;

import java.util.List;

/**
 * Created by lenovo on 3/9/2018.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {

    List<IngredientModel> ingredients;

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (ingredients != null) return ingredients.size();
        else return 0;
    }

    public void setList(List<IngredientModel> ingredients) {
        this.ingredients = ingredients;
    }


    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView quantity_measure, ingredient;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            quantity_measure = itemView.findViewById(R.id.quantityAndMeasure);
            ingredient = itemView.findViewById(R.id.ingredient);
        }

        private void bind(int position) {
            IngredientModel model = ingredients.get(position);
            ingredient.setText(model.getIngredient());
            StringBuilder builder = new StringBuilder();
            builder.append("( ").append(model.getQuantity()).append(" ").append(model.getMeasure()).append(" )");
            String qunatityAndMeasure = builder.toString();
            quantity_measure.setText(qunatityAndMeasure);

        }
    }
}
