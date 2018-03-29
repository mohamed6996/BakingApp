package com.tastey.baking.bakingapp.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;
import com.tastey.baking.bakingapp.R;
import com.tastey.baking.bakingapp.model.RecipeModel;
import com.tastey.baking.bakingapp.model.StepModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by lenovo on 3/14/2018.
 */

public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    SimpleExoPlayer player;
    long playbackPosition;
    int currentWindow;
    boolean playWhenReady = true;
    RecipeModel recipeModel;
    int received_index;
    @BindView(R.id.simple_exp_view)
    SimpleExoPlayerView playerView;
    @BindView(R.id.step_long_desc)
    TextView step_desc;
    @BindView(R.id.video_thumbnail)
    ImageView videoThumbnail;

    SimpleExoPlayer simpleExoPlayer;


    public PlaceholderFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        String recipes_json = getArguments().getString(DetailFragment.STEP_INFO_EXTRA);
        received_index = getArguments().getInt(DetailFragment.STEP_INFO_POSITION, 0);
        recipeModel = new Gson().fromJson(recipes_json, RecipeModel.class);


    }
//
//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//        savedInstanceState.putInt("current_window", currentWindow);
//        savedInstanceState.putLong("play_back_pos", playbackPosition);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_info, container, false);
        ButterKnife.bind(this, rootView);
        //  setPlayerVisibility();
        StepModel stepModel = recipeModel.getSteps().get(getArguments().getInt(ARG_SECTION_NUMBER) - 1);
        if (stepModel.getDescription() != null) {
            step_desc.setText(stepModel.getDescription());
        }




        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            playbackPosition = savedInstanceState.getLong("play_back_pos");
            currentWindow = savedInstanceState.getInt("current_window");
        }
        initializePlayer();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//            initializePlayer();
//    }

    @Override
    public void onStop() {
        super.onStop();
        if(getActivity().isChangingConfigurations()) {
            Log.i(getTag(), "configuration is changing: keep playing");
        } else {
            releasePlayer();
        }

    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        if ((Util.SDK_INT <= 23 || player == null)) {
//            initializePlayer();
//        }
//    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        if (Util.SDK_INT <= 23) {
//            releasePlayer();
//        }
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (Util.SDK_INT > 23) {
//            releasePlayer();
//        }
//    }

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

        if (player == null) {

            player = ExoPlayerFactory.newSimpleInstance(
                    new DefaultRenderersFactory(getContext()),
                    new DefaultTrackSelector(), new DefaultLoadControl());

            playerView.setPlayer(player);
            player.setPlayWhenReady(playWhenReady);
            player.seekTo(currentWindow, playbackPosition);

            Uri uri = Uri.parse(recipeModel.getSteps().get(getArguments().getInt(ARG_SECTION_NUMBER) - 1).getVideoURL());
            MediaSource mediaSource = buildMediaSource(uri);
            player.prepare(mediaSource,false,false);

            videoThumbnail.setVisibility(View.GONE);
        }

    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("Baking_app")).
                createMediaSource(uri);
    }


    public static PlaceholderFragment newInstance(int sectionNumber, String recipeModel) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(DetailFragment.STEP_INFO_EXTRA, recipeModel);
        fragment.setArguments(args);
        return fragment;
    }

    private void setPlayerVisibility() {
        StepModel stepModel = recipeModel.getSteps().get(getArguments().getInt(ARG_SECTION_NUMBER) - 1);
        if (stepModel.getVideoURL().isEmpty()) {
            playerView.setVisibility(View.GONE);
        } else if (!stepModel.getThumbnailURL().isEmpty()) {
            videoThumbnail.setVisibility(View.VISIBLE);
            Picasso.with(getContext()).load(stepModel.getThumbnailURL()).into(videoThumbnail);
        } else {
            playerView.setVisibility(View.VISIBLE);
        }
    }
}