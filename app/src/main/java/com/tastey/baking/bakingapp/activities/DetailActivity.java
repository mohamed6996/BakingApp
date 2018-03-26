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
    FloatingActionButton fab;
    private int maxScrollSize;
    private static final int PERCENTAGE_TO_SHOW_IMAGE = 20;
    private boolean isImageHidden;

    public static boolean mTWO_PANE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTWO_PANE = getResources().getBoolean(R.bool.isTablet);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_fragment_container, new DetailFragment()).commit();
        }


//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        fab = findViewById(R.id.detail_fab);
//        AppBarLayout appBarLayout = findViewById(R.id.detail_appbar);
//        appBarLayout.addOnOffsetChangedListener(this);


    }

//    @Override
//    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//        // reference: https://github.com/saulmm/CoordinatorExamples
//        if (maxScrollSize == 0) maxScrollSize = appBarLayout.getTotalScrollRange();
//
//        int currentScrollPercentage = (Math.abs(verticalOffset)) * 100 / maxScrollSize;
//
//        if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
//            if (!isImageHidden) {
//                isImageHidden = true;
//                ViewCompat.animate(fab).scaleX(0).scaleY(0).start();
//            }
//        }
//
//        if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
//            if (isImageHidden) {
//                isImageHidden = false;
//                ViewCompat.animate(fab).scaleX(1).scaleY(1).start();
//
//            }
//        }
//
//    }
}
