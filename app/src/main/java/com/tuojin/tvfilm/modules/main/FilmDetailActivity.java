package com.tuojin.tvfilm.modules.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.blankj.utilcode.utils.SPUtils;
import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.contract.FilmDetailContract;
import com.tuojin.tvfilm.modules.main.filmdetail.FilmDetailFragment;
import com.tuojin.tvfilm.presenter.FilmDetailPresenterImpl;

/**
 * 文 件 名: FilmDetailActivity
 * 创 建 人: Administrator
 * 创建日期: 2016/9/22 13:17
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class FilmDetailActivity extends BaseActivity<FilmDetailContract.View, FilmDetailPresenterImpl>{

    FilmDetailFragment mDetailFragment;
    private FilmBean mFilm;
    private boolean mBig;

    @Override
    protected FilmDetailPresenterImpl initPresenter() {
        String ip=new SPUtils(mActivity,"terminal").getString("ip");
        return new FilmDetailPresenterImpl(ip);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mFilm = intent.getParcelableExtra("film");
        mBig = intent.getBooleanExtra("big", false);
        showFilmDetailFragment();
    }

    @Override
    protected void initView() {


    }

    /**
     * 向Fragment中传递序列化参数。
     */
    private void showFilmDetailFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mDetailFragment= (FilmDetailFragment) fragmentManager.findFragmentByTag("ll_filmdetail_frag");
        if (mDetailFragment == null) {
            mDetailFragment=new FilmDetailFragment();

            if (mFilm != null) {
                Bundle bundle=new Bundle();
                bundle.putParcelable("film",mFilm);
                bundle.putBoolean("big",mBig);
                mDetailFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.ll_filmdetail_frag, mDetailFragment, "ll_filmdetail_frag");
            }
        }
        fragmentTransaction.commit();
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_detail;
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        mDetailFragment.onKeyUp(keyCode, event);
        return super.onKeyUp(keyCode, event);

    }

    @Override
    public void showMessage(String msg) {

    }
}
