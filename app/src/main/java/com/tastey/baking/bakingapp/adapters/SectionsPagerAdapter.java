package com.tastey.baking.bakingapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.tastey.baking.bakingapp.fragments.PlaceholderFragment;
import com.tastey.baking.bakingapp.model.StepModel;

import java.util.List;

/**
 * Created by lenovo on 3/14/2018.
 */

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
    List<StepModel> steps;
    String recipeModel;

    public SectionsPagerAdapter(FragmentManager fm, List<StepModel> steps, String recipeModel) {
        super(fm);
        this.steps = steps;
        this.recipeModel = recipeModel;
    }

    @Override
    public Fragment getItem(int position) {
        return PlaceholderFragment.newInstance(position + 1, recipeModel);
    }

    @Override
    public int getCount() {
        return steps.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) return "intro";
        else return "step " + position;
    }


}
