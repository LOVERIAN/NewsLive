package com.loverian.newslive;
import android.content.Intent;

import android.os.Bundle;

import android.widget.TextView;


import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Yt extends YouTubeBaseActivity  implements YouTubePlayer.OnInitializedListener{

    public static final String API_KEY = "AIzaSyBNluTsmzdti5RijP-bR3lIU82Bmcxl-pk";
    String url;
    String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yt);

        Intent i = getIntent();
        url = i.getStringExtra("url");
        info = i.getStringExtra("info");


        TextView textView = findViewById(R.id.textView2);
        textView.setText(info);
        YouTubePlayerView playerView = (YouTubePlayerView) findViewById(R.id.ytview);
        playerView.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored){
            player.loadVideo(url);
    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {

    }
}