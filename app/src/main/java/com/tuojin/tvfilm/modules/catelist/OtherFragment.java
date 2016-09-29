package com.tuojin.tvfilm.modules.catelist;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.contract.CateListFilmContract;
import com.tuojin.tvfilm.presenter.CateListFilmPresenterImpl;

/**
 * 文 件 名: OtherFragment
 * 创 建 人: Administrator
 * 创建日期: 2016/9/27 12:57
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class OtherFragment extends BaseFragment<CateListFilmContract.View,CateListFilmPresenterImpl> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_other;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected CateListFilmPresenterImpl initPresenter() {
        return null;
    }
}
