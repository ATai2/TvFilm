package com.tuojin.tvfilm.base;

import android.app.Application;
import android.content.Intent;

import com.litesuits.orm.LiteOrm;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.service.AutoBahnService;

/**
 * 文 件 名: BaseApplication
 * 创 建 人: Administrator
 * 创建日期: 2016/9/20 15:09
 * 文件描述： 网络请求部分采用WebSocket，在这里使用库    compile 'org.java-websocket:Java-WebSocket:1.3.0'
 * <p>
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class BaseApplication extends Application {

    private TvFilmNetWorkWS mInstance;
    public static LiteOrm mLiteOrm;

    @Override
    public void onCreate() {
        super.onCreate();

        startService(new Intent(getApplicationContext(), AutoBahnService.class));

        BaseContextManager.getInstance().mContext = getApplicationContext();
        if (mLiteOrm == null) {
            mLiteOrm = LiteOrm.newSingleInstance(this, "liteorm.db");
        }
        mLiteOrm.setDebugged(true);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
//        mInstance.mService.
        stopService(new Intent(getApplicationContext(), AutoBahnService.class));
//        stopService(new Intent(getApplicationContext(), ConnectService.class));
    }

}
