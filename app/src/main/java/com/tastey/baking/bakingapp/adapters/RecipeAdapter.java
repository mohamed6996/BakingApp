package com.tastey.baking.bakingapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tastey.baking.bakingapp.ListItemClickListener;
import com.tastey.baking.bakingapp.R;
import com.tastey.baking.bakingapp.model.RecipeModel;

import java.util.List;

/**
 * Created by lenovo on 3/8/2018.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    List<RecipeModel> recipes;
    ListItemClickListener listener;

    public RecipeAdapter(ListItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (recipes != null) return recipes.size();
        else return 0;
    }

    public void setList(List<RecipeModel> recipes) {
        this.recipes = recipes;
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView recipeImg;
        TextView recipeName, recipeServings, recipeSteps, recipeIngred;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipeImg = itemView.findViewById(R.id.recipe_img);
            recipeName = itemView.findViewById(R.id.recipe_name);
            itemView.setOnClickListener(this);
        }


        void bind(int position) {
            setImage(position);
            RecipeModel model = recipes.get(position);
            recipeName.setText(model.getName());

        }

        private void setImage(int position) {
            switch (position) {
                case 0:
                    recipeImg.setImageResource(R.drawable.nutella_pie);
                    break;
                case 1:
                    recipeImg.setImageResource(R.drawable.brownies);
                    break;
                case 2:
                    recipeImg.setImageResource(R.drawable.yellow_cake);
                    break;
                case 3:
                    recipeImg.setImageResource(R.drawable.cheese_cake);
                    break;
            }
        }

        @Override
        public void onClick(View view) {
            listener.onListITemClicked(getAdapterPosition());
        }
    }
}
