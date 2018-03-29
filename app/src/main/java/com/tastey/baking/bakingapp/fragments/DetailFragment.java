package com.tastey.baking.bakingapp.fragments;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tastey.baking.bakingapp.ListItemClickListener;
import com.tastey.baking.bakingapp.R;
import com.tastey.baking.bakingapp.activities.StepInfoActivity;
import com.tastey.baking.bakingapp.widget.UpdateService;
import com.tastey.baking.bakingapp.adapters.IngredientsAdapter;
import com.tastey.baking.bakingapp.adapters.StepsAdapter;
import com.tastey.baking.bakingapp.model.RecipeModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tastey.baking.bakingapp.activities.DetailActivity.mTWO_PANE;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailFragment extends Fragment implements ListItemClickListener, AppBarLayout.OnOffsetChangedListener {

    @BindView(R.id.ingredient_rec_view)
    RecyclerView ingredientRecyclerView;
    @BindView(R.id.step_rec_view)
    RecyclerView stepRecyclerView;
    @BindView(R.id.detail_image_view)
    ImageView recipeImage;
    @BindView(R.id.detail_collapsing)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.detail_fab)
    FloatingActionButton fab;

    IngredientsAdapter ingredientsAdapter;
    StepsAdapter stepsAdapter;
    RecipeModel recipeModel;
    String recipes_json;
    int position;
    public static final String STEP_INFO_EXTRA = "step_info";
    public static final String STEP_INFO_POSITION = "step_info_position";

    boolean isVisibile = false;

    private int maxScrollSize;
    private static final int PERCENTAGE_TO_SHOW_IMAGE = 20;
    private boolean isImageHidden;


    public DetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipes_json = getActivity().getIntent().getStringExtra(MainFragment.INTENT_EXTRA_RECIPES);
        int position = getActivity().getIntent().getIntExtra(MainFragment.INTENT_EXTRA_POSITION, 0);

        recipeModel = new Gson().fromJson(recipes_json, RecipeModel.class);
        this.position = position;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        setImage(position);
        collapsingToolbar.setTitle(recipeModel.getName());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateService.startUpdating(getContext(), recipes_json);
                Snackbar.make(view, "Added to widget", Snackbar.LENGTH_SHORT).show();
                //  Toast.makeText(getContext(), "Added to widget", Toast.LENGTH_SHORT).show();
            }
        });

        Toolbar toolbar = view.findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AppBarLayout appBarLayout = view.findViewById(R.id.detail_appbar);
        appBarLayout.addOnOffsetChangedListener(this);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ingredientRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ingredientsAdapter = new IngredientsAdapter();
        ingredientsAdapter.setList(recipeModel.getIngredients());
        ingredientsAdapter.notifyDataSetChanged();
        ingredientRecyclerView.setAdapter(ingredientsAdapter);

        stepRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        stepsAdapter = new StepsAdapter(this);
        stepsAdapter.setList(recipeModel.getSteps());
        stepsAdapter.notifyDataSetChanged();
        stepRecyclerView.setAdapter(stepsAdapter);


    }

    @Override
    public void onListITemClicked(int clickedItemIndex) {
        Bundle bundle = new Bundle();
        bundle.putString(STEP_INFO_EXTRA, recipes_json);
        bundle.putInt(STEP_INFO_POSITION, clickedItemIndex);

        if (mTWO_PANE) {
            if (!isVisibile) {
                StepDetailFragment stepDetailFragment = new StepDetailFragment();
                stepDetailFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.step_info_container, stepDetailFragment).commit();
                isVisibile = true;
            }
        } else {
            Intent intent = new Intent(getActivity(), StepInfoActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
//            StepDetailFragment stepDetailFragment = new StepDetailFragment();
//            stepDetailFragment.setArguments(bundle);
//            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.detail_fragment_container, stepDetailFragment).addToBackStack("null").commit();

        }


    }

    private void setImage(int position) {
        switch (position) {
            case 0:
                recipeImage.setImageResource(R.drawable.nutella_pie);
                break;
            case 1:
                recipeImage.setImageResource(R.drawable.brownies);
                break;
            case 2:
                recipeImage.setImageResource(R.drawable.yellow_cake);
                break;
            case 3:
                recipeImage.setImageResource(R.drawable.cheese_cake);
                break;
        }
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        // reference: https://github.com/saulmm/CoordinatorExamples
        if (maxScrollSize == 0) maxScrollSize = appBarLayout.getTotalScrollRange();

        int currentScrollPercentage = (Math.abs(verticalOffset)) * 100 / maxScrollSize;

        if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
            if (!isImageHidden) {
                isImageHidden = true;
                ViewCompat.animate(fab).scaleX(0).scaleY(0).start();
            }
        }

        if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
            if (isImageHidden) {
                isImageHidden = false;
                ViewCompat.animate(fab).scaleX(1).scaleY(1).start();

            }
        }

    }

}
