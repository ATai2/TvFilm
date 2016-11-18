//package com.tuojin.tvfilm.modules.catelist.live;
//
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.widget.Toast;
//
//import com.afollestad.easyvideoplayer.EasyVideoCallback;
//import com.afollestad.easyvideoplayer.EasyVideoPlayer;
//import com.afollestad.materialdialogs.MaterialDialog;
//import com.tuojin.tvfilm.R;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class MP4Activity extends AppCompatActivity implements EasyVideoCallback {
//
//    private EasyVideoPlayer player;
//    Timer mTimer=new Timer();
//    BtnTimerTask mBtnTimerTask;
//    boolean isShow;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_mp4);
//
//        player = (EasyVideoPlayer) findViewById(R.id.player);
//        assert player != null;
////        player.setSource();
//        player.setCallback(this);
//    }
//
//    @SuppressWarnings("Range")
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
//            player.showControls();
//        }
//        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
//            player.showControls();
//        }
//        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
//            player.showControls();
//        }
//        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
//            player.showControls();
//        }
//        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
//            player.showControls();
//        }
//        timerSchedule();
//
//        return super.onKeyDown(keyCode, event);
//    }
//
//    private void timerSchedule() {
//        if (mTimer != null) {
//            if (mBtnTimerTask != null) {
//                mBtnTimerTask.cancel();
//            }
//        }
//
//        mBtnTimerTask=new BtnTimerTask();
//        mTimer.schedule(mBtnTimerTask,3000);
//    }
//
//    class BtnTimerTask extends TimerTask {
//
//        @Override
//        public void run() {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    player.hideControls();
//                }
//            });
//
//        }
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        player.pause();
//    }
//
//    @Override
//    public void onStarted(EasyVideoPlayer player) {
//    }
//
//    @Override
//    public void onPaused(EasyVideoPlayer player) {
//    }
//
//    @Override
//    public void onPreparing(EasyVideoPlayer player) {
//        Log.d("EVP-Sample", "onPreparing()");
//    }
//
//    @Override
//    public void onPrepared(EasyVideoPlayer player) {
//        Log.d("EVP-Sample", "onPrepared()");
//    }
//
//    @Override
//    public void onBuffering(int percent) {
//        Log.d("EVP-Sample", "onBuffering(): " + percent + "%");
//    }
//
//    @Override
//    public void onError(EasyVideoPlayer player, Exception e) {
//        Log.d("EVP-Sample", "onError(): " + e.getMessage());
//        new MaterialDialog.Builder(this)
//                .title(R.string.error)
//                .content(e.getMessage())
//                .positiveText(android.R.string.ok)
//                .show();
//    }
//
//    @Override
//    public void onCompletion(EasyVideoPlayer player) {
//        Log.d("EVP-Sample", "onCompletion()");
//    }
//
//    @Override
//    public void onRetry(EasyVideoPlayer player, Uri source) {
//        Toast.makeText(this, "Retry", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onSubmit(EasyVideoPlayer player, Uri source) {
//        Toast.makeText(this, "Submit", Toast.LENGTH_SHORT).show();
//    }
//}