package com.tuojin.tvfilm.modules.launch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.blankj.utilcode.utils.AppUtils;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;
import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.modules.main.MainActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.finalteam.okhttpfinal.FileDownloadCallback;
import cn.finalteam.okhttpfinal.HttpRequest;

public class LaunchActivity extends AppCompatActivity {

    @BindView(R.id.shimmer_tv)
    ShimmerTextView shimmerTv;

    Shimmer shimmer;
    /* SD卡根目录 */
    private File rootDie;
    /* 输出文件名称 */
    private String outFileName = "app.apk";
    /* 进度条对话框 */
    private ProgressDialog pdialog;
//    private File mFile;
    private Handler handler1 = new Handler() {
        public void handleMessage(Message msg) {
            // 如果有更新就提示
            if (msg.what==0) {   //在下面的代码段

                String path = "http://www.bestmovie.com.cn/download/version/PAD/1.2.1/FilmBSTApp.apk";
                rootDie = Environment.getExternalStorageDirectory();
/* 新文件的目录 */
                mNewFile = new File(rootDie + "/download/app.apk");
                HttpRequest.download(path, mNewFile,new FileDownloadCallback(){

                    @Override
                    public void onProgress(int progress, long networkSpeed) {
                        super.onProgress(progress, networkSpeed);
                        pdialog.setProgress(progress);
                    }
                    //下载失败
                    @Override
                    public void onFailure() {
                        super.onFailure();
                        Toast.makeText(getBaseContext(), "下载失败", Toast.LENGTH_SHORT).show();
                    }

                    //下载完成（下载成功）
                    @Override
                    public void onDone() {
                        super.onDone();
                        pdialog.dismiss();
                        Toast.makeText(getBaseContext(), "下载成功", Toast.LENGTH_SHORT).show();
                        AppUtils.installApp(LaunchActivity.this,mNewFile);
                    }
                    @Override
                    public void onStart() {
                        super.onStart();
                          /* 实例化进度条对话框 */
                        pdialog = new ProgressDialog(LaunchActivity.this);
/* 进度条对话框属性设置 */
                        pdialog.setMessage("正在下载中...");
/* 进度值最大100 */
                        pdialog.setMax(100);
/* 水平风格进度条 */
                        pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
/* 无限循环模式 */
                        pdialog.setIndeterminate(false);
/* 可取消 */
                        pdialog.setCancelable(true);
/* 显示对话框 */
                        pdialog.show();
                    }
                });

            }
        }

        ;
    };
    private File mNewFile;

    /**
     * 启动页，开启文本闪烁
     *
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
        new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(2000);
//                        checkVersion();
//                        if (true) {
//                            handler1.sendEmptyMessage(0);
////                            checkAndCreateDir();
////                            new MyLoadAsyncTask().execute(path);
//                        } else {
                            startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                            LaunchActivity.this.finish();
//                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
