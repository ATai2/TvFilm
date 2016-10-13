package com.tuojin.tvfilm.modules.catelist.live;

import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.contract.LiveContract;
import com.tuojin.tvfilm.presenter.LivePresenterImpl;

/**
 * 文 件 名: LiveActivity
 * 创 建 人: Administrator
 * 创建日期: 2016/10/13 11:20
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class LiveActivity extends BaseActivity<LiveContract.View,LivePresenterImpl> {
    @Override
    protected LivePresenterImpl initPresenter() {
        return new LivePresenterImpl();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutID() {
        return 0;
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String msg) {

    }
}
