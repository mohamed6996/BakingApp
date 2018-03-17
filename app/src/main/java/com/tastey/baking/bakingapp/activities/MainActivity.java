package com.tastey.baking.bakingapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.tastey.baking.bakingapp.R;
import com.tastey.baking.bakingapp.api.ApiClient;
import com.tastey.baking.bakingapp.api.ApiInterface;
import com.tastey.baking.bakingapp.fragments.MainFragment;
import com.tastey.baking.bakingapp.model.RecipeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static boolean mTWO_PANE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.step_info_container) != null) {
            mTWO_PANE = true;
        }

    }
}
