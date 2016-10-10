package com.tuojin.tvfilm.modules.catelist.framecatelist.activity;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.contract.DirectorDetailContract;
import com.tuojin.tvfilm.presenter.DirectorDetailPresenterImpl;

/**
 * 文 件 名: DirectorDetailActivity
 * 创 建 人: Administrator
 * 创建日期: 2016/9/30 18:11
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class DirectorDetailActivity extends BaseActivity<DirectorDetailContract.View,DirectorDetailPresenterImpl>
implements DirectorDetailContract.View{
    @Override
    protected DirectorDetailPresenterImpl initPresenter() {
        return new DirectorDetailPresenterImpl();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mPresenter.attach(this);


    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_director_detail;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String msg) {

    }
}
