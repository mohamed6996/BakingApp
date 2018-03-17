package com.tastey.baking.bakingapp.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tastey.baking.bakingapp.activities.DetailActivity;
import com.tastey.baking.bakingapp.ListItemClickListener;
import com.tastey.baking.bakingapp.MainViewModel;
import com.tastey.baking.bakingapp.MyApp;
import com.tastey.baking.bakingapp.R;
import com.tastey.baking.bakingapp.adapters.RecipeAdapter;
import com.tastey.baking.bakingapp.model.RecipeModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements ListItemClickListener {

    RecyclerView mRecyclerView;
    RecipeAdapter mAdapter;
    MainViewModel viewModel;
    List<RecipeModel> recipesList;


    public static final String INTENT_EXTRA_RECIPES = "recipes";
    public static final String INTENT_EXTRA_POSITION = "position";

    MyApp myApp ;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = view.findViewById(R.id.main_rec_view);
        myApp = (MyApp) getActivity().getApplicationContext();
        myApp.setIdleState(false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new RecipeAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        try {
            viewModel.getLiveRecipes().observe(this, new Observer<List<RecipeModel>>() {
                @Override
                public void onChanged(@Nullable List<RecipeModel> recipeModels) {
                    if (recipeModels != null) {
                        myApp.setIdleState(true); // espresso testing
                        recipesList = recipeModels;
                        mAdapter.setList(recipeModels);
                        mAdapter.notifyDataSetChanged();

                    }

                }
            });
        } catch (Exception e) {
            Toast.makeText(getContext(), "error " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onListITemClicked(int clickedItemIndex) {
        RecipeModel model = recipesList.get(clickedItemIndex);
        String recipes_json = new Gson().toJson(model);
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(INTENT_EXTRA_RECIPES, recipes_json);
        intent.putExtra(INTENT_EXTRA_POSITION, clickedItemIndex);
        startActivity(intent);
    }
}
