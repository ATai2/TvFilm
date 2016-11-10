package com.tuojin.tvfilm.modules.launch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;
import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.modules.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LaunchActivity extends AppCompatActivity {

    @BindView(R.id.shimmer_tv)
    ShimmerTextView shimmerTv;

    Shimmer shimmer;

    /**
     * 启动页，开启文本闪烁
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ButterKnife.bind(this);
        shimmer = new Shimmer();
        shimmer.setDuration(1000);
        if (shimmer != null && shimmer.isAnimating()) {
            shimmer.cancel();
        } else {
            shimmer = new Shimmer();
            shimmer.start(shimmerTv);
        }
        new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (this){
                        wait(2000);
                        startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                        LaunchActivity.this.finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

//        TvFilmNetWorkWS ws = new TvFilmNetWorkWS();
//        ws.sendMsg("PAD1465889962927|login|username=admin&pwd=123456", new TvFilmNetWorkWS.Success() {
//            @Override
//            public void excute(String data) {
////                Toast.makeText(LaunchActivity.this, data, Toast.LENGTH_SHORT).show();
//                LogUtils.d("ws","success"+data);
//            }
//        }, new TvFilmNetWorkWS.Failure() {
//            @Override
//            public void excute(String data) {
//                LogUtils.d("ws","failure"+data);
//            }
//        });


    }
}
