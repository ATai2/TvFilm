package com.tuojin.myapplication;

import android.app.Application;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;
import okhttp3.OkHttpClient;

/**
 * 文 件 名: BaseApplication
 * 创 建 人: Administrator
 * 创建日期: 2016/9/30 16:49
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initFresco();
    }

    private void initOkHttpFinal() {
        //创建默认的OkHttpFinal配置参数
        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        OkHttpFinal.getInstance().init(builder.build());
    }
    private void initFresco() {
        OkHttpClient okHttpClient = OkHttpFinal.getInstance().getOkHttpClientBuilder().build();

        File f = new File(Environment.getExternalStorageDirectory().getPath() + "/" + AppUtils.getPackageName(this));
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this).setBaseDirectoryPath(f).build();

        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(this, okHttpClient)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();

        Fresco.initialize(this, config);
    }

}
