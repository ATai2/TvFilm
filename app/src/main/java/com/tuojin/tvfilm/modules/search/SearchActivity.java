package com.tuojin.tvfilm.modules.search;

import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.contract.SearchContract;
import com.tuojin.tvfilm.presenter.SearchPresenterImpl;

/**
 * 文 件 名: SearchActivity
 * 创 建 人: Administrator
 * 创建日期: 2016/9/23 13:08
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class SearchActivity extends BaseActivity<SearchContract.View,SearchPresenterImpl> implements SearchContract.View {
    @Override
    protected SearchPresenterImpl initPresenter() {
        return new SearchPresenterImpl();
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
}
