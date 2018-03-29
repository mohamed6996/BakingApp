package com.tastey.baking.bakingapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tastey.baking.bakingapp.R;
import com.tastey.baking.bakingapp.adapters.SectionsPagerAdapter;
import com.tastey.baking.bakingapp.model.RecipeModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepDetailFragment extends Fragment {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private Toolbar toolbar;
    private ViewPager mViewPager;
    public static RecipeModel recipeModel;
    int received_index;
    String recipes_json;

    public StepDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        try {
            recipes_json = getArguments().getString(DetailFragment.STEP_INFO_EXTRA);
            received_index = getArguments().getInt(DetailFragment.STEP_INFO_POSITION, 0);
            recipeModel = new Gson().fromJson(recipes_json, RecipeModel.class);
            Toast.makeText(getContext(), "" + recipeModel.getSteps().get(received_index).getShortDescription(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("error_test", e.getMessage());
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step_detail, container, false);
        // setRetainInstance(true);
        try {
            toolbar = view.findViewById(R.id.step_info_toolbar);
            mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager(), recipeModel.getSteps(), recipes_json);
            mViewPager = view.findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);

            TabLayout tabLayout = view.findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mViewPager);

            mViewPager.setCurrentItem(received_index);
            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    toolbar.setTitle(recipeModel.getSteps().get(position).getShortDescription());
                }

                @Override
                public void onPageSelected(int position) {
                    mViewPager.setCurrentItem(position, true);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } catch (Exception e) {
            Log.e("error_test", e.getMessage());
        }

        return view;
    }

}
