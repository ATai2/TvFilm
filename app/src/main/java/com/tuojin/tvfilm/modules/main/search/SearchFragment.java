package com.tuojin.tvfilm.modules.main.search;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.contract.SearchContract;
import com.tuojin.tvfilm.presenter.SearchPresenterImpl;

/**
 * 文 件 名: SearchFragment
 * 创 建 人: Administrator
 * 创建日期: 2016/9/21 16:02
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class SearchFragment extends BaseFragment<SearchContract.View,SearchPresenterImpl> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected SearchPresenterImpl initPresenter() {
        return null;
    }
}
