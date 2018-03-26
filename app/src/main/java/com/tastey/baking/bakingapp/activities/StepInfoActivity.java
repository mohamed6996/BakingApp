package com.tastey.baking.bakingapp.activities;


import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.google.gson.Gson;
import com.tastey.baking.bakingapp.R;
import com.tastey.baking.bakingapp.adapters.SectionsPagerAdapter;
import com.tastey.baking.bakingapp.fragments.DetailFragment;
import com.tastey.baking.bakingapp.model.RecipeModel;


public class StepInfoActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private Toolbar toolbar;
    private ViewPager mViewPager;
    public static RecipeModel recipeModel;
    int received_index;
    String recipes_json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_info);



            toolbar = findViewById(R.id.step_info_toolbar);

            recipes_json = getIntent().getExtras().getString(DetailFragment.STEP_INFO_EXTRA);
            received_index = getIntent().getExtras().getInt(DetailFragment.STEP_INFO_POSITION, 0);
            recipeModel = new Gson().fromJson(recipes_json, RecipeModel.class);

            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), recipeModel.getSteps(), recipes_json);

            mViewPager = findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);

            TabLayout tabLayout = findViewById(R.id.tabs);
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


    }


}
