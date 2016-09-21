package com.tuojin.tvfilm.base;

import android.app.Application;

import com.tuojin.tvfilm.net.TvFilmNetWorkWS;

/**
 * 文 件 名: BaseApplication
 * 创 建 人: Administrator
 * 创建日期: 2016/9/20 15:09
 * 文件描述： 网络请求部分采用WebSocket，在这里使用库    compile 'org.java-websocket:Java-WebSocket:1.3.0'
 *
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class BaseApplication extends Application {

    private TvFilmNetWorkWS mInstance;

    @Override
    public void onCreate() {
        super.onCreate();



    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }
}
