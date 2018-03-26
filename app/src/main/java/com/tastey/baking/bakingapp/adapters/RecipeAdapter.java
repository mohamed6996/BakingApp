package com.tastey.baking.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
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
    Context context;

    public RecipeAdapter(ListItemClickListener listener, Context context) {
        this.listener = listener;
        this.context = context;
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
            RecipeModel model = recipes.get(position);
            recipeName.setText(model.getName());
            setImage(position, model);
        }


        private void setImage(int position, RecipeModel model) {
            switch (position) {
                case 0:
                    if (model.getImage().isEmpty())
                        recipeImg.setImageResource(R.drawable.nutella_pie);
                    else Picasso.with(context).load(model.getImage()).into(recipeImg);
                    break;
                case 1:
                    if (model.getImage().isEmpty())
                        recipeImg.setImageResource(R.drawable.brownies);
                    else Picasso.with(context).load(model.getImage()).into(recipeImg);
                    break;
                case 2:
                    if (model.getImage().isEmpty())
                        recipeImg.setImageResource(R.drawable.yellow_cake);
                    else Picasso.with(context).load(model.getImage()).into(recipeImg);
                    break;
                case 3:
                    if (model.getImage().isEmpty())
                        recipeImg.setImageResource(R.drawable.cheese_cake);
                    else Picasso.with(context).load(model.getImage()).into(recipeImg);
                    break;

            }
        }

        @Override
        public void onClick(View view) {
            listener.onListITemClicked(getAdapterPosition());
        }
    }
}
