package com.tastey.baking.bakingapp.activities;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tastey.baking.bakingapp.R;
import com.tastey.baking.bakingapp.adapters.SectionsPagerAdapter;
import com.tastey.baking.bakingapp.fragments.PlaceholderFragment;
import com.tastey.baking.bakingapp.fragments.StepDetailFragment;
import com.tastey.baking.bakingapp.model.RecipeModel;

import static com.tastey.baking.bakingapp.fragments.DetailFragment.STEP_INFO_EXTRA;
import static com.tastey.baking.bakingapp.fragments.DetailFragment.STEP_INFO_POSITION;


public class StepInfoActivity extends AppCompatActivity {

    public static RecipeModel recipeModel;
    int received_index;
    String recipes_json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_info);

        recipes_json = getIntent().getExtras().getString(STEP_INFO_EXTRA);
        received_index = getIntent().getExtras().getInt(STEP_INFO_POSITION, 0);
        recipeModel = new Gson().fromJson(recipes_json, RecipeModel.class);
        Bundle bundle = new Bundle();
        bundle.putString(STEP_INFO_EXTRA, recipes_json);
        bundle.putInt(STEP_INFO_POSITION, received_index);
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag("place_holder");
        if (fragment == null) {
            StepDetailFragment stepDetailFragment = new StepDetailFragment();
            stepDetailFragment.setArguments(bundle);
            manager.beginTransaction().add(R.id.step_activity_container, stepDetailFragment, "place_holder").commit();
        }

    }


}
