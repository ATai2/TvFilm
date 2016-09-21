package com.tuojin.tvfilm.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;

/**
 * 文 件 名: BaseActivity
 * 创 建 人: Administrator
 * 创建日期: 2016/9/18 15:08
 * 文件描述：基类
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    T mPresenter;

    public void setPresenter(T presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(getLayoutID());
        ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    public abstract int getLayoutID();
}
