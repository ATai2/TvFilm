package com.tuojin.tvfilm.modules.catelist.live;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.tuojin.tvfilm.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class FullScreenLiveActivity extends AppCompatActivity {

    @BindView(R.id.jc_video)
    JCVideoPlayerStandard mJcVideo;
    @BindView(R.id.activity_full_screen_live)
    RelativeLayout mActivityFullScreenLive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_live);
        ButterKnife.bind(this);
        String url = getIntent().getStringExtra("url");
        Log.d("abs", url);

//        url = "http://192.168.1.243/Westworld.S01E01.2016.HD1080P.X264.AAC.English.CHS-ENG.Mp4Ba.mp4";
//
//        url = "http://192.168.1.243/Shanghai%20Tower%20(650%20meters).mp4";

//        url = "http://192.168.1.243/KievStar.mp4";


        String pic = getIntent().getStringExtra("pic");
        mJcVideo.setUp(url, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "正在直播");
        mJcVideo.progressBar.setVisibility(View.INVISIBLE);
        mJcVideo.bottomProgressBar.setVisibility(View.GONE);
        mJcVideo.loadingProgressBar.setVisibility(View.INVISIBLE);
        mJcVideo.onClick(mJcVideo.startButton);
      //  mJcVideo.set
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            mJcVideo.onClick(mJcVideo.startButton);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
        this.setResult(1);
    }
}
