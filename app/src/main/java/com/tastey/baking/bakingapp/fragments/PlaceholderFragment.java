package com.tastey.baking.bakingapp.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.gson.Gson;
import com.tastey.baking.bakingapp.R;
import com.tastey.baking.bakingapp.model.RecipeModel;
import com.tastey.baking.bakingapp.model.StepModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tastey.baking.bakingapp.activities.MainActivity.mTWO_PANE;

/**
 * Created by lenovo on 3/14/2018.
 */

public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    SimpleExoPlayer player;
    long playbackPosition;
    int currentWindow;
    boolean playWhenReady;
    RecipeModel recipeModel;
    int received_index;
    @BindView(R.id.simple_exp_view)
    SimpleExoPlayerView playerView;
    @BindView(R.id.step_long_desc)
    TextView step_desc;


    public PlaceholderFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mTWO_PANE) {
            String recipes_json = getArguments().getString(DetailFragment.STEP_INFO_EXTRA);
            received_index = getArguments().getInt(DetailFragment.STEP_INFO_POSITION, 0);
            recipeModel = new Gson().fromJson(recipes_json, RecipeModel.class);
        } else {
            String recipes_json = getActivity().getIntent().getExtras().getString(DetailFragment.STEP_INFO_EXTRA, "");
            received_index = getActivity().getIntent().getExtras().getInt(DetailFragment.STEP_INFO_POSITION, 0);
            recipeModel = new Gson().fromJson(recipes_json, RecipeModel.class);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_info, container, false);
        ButterKnife.bind(this, rootView);

        setPlayerVisibility();
        StepModel stepModel = recipeModel.getSteps().get(getArguments().getInt(ARG_SECTION_NUMBER) - 1);
        if (stepModel.getDescription() != null) {
            step_desc.setText(stepModel.getDescription());
        }

        return rootView;
    }

    private void setPlayerVisibility() {
        StepModel stepModel = recipeModel.getSteps().get(getArguments().getInt(ARG_SECTION_NUMBER) - 1);
        if (stepModel.getVideoURL().isEmpty())
            playerView.setVisibility(View.GONE);
        else
            playerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        initializePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }


    private void initializePlayer() {

        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(player);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);

        Uri uri = Uri.parse(recipeModel.getSteps().get(getArguments().getInt(ARG_SECTION_NUMBER) - 1).getVideoURL()); //todo
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("Baking_app")).
                createMediaSource(uri);
    }


    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
}