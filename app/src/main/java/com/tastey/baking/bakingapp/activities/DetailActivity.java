package com.tastey.baking.bakingapp.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.tastey.baking.bakingapp.R;
import com.tastey.baking.bakingapp.fragments.DetailFragment;

public class DetailActivity extends AppCompatActivity {
    public static boolean mTWO_PANE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTWO_PANE = getResources().getBoolean(R.bool.isTablet);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_fragment_container, new DetailFragment()).commit();
        }

    }

}
