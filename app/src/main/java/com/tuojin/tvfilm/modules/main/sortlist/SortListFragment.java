package com.tuojin.tvfilm.modules.main.sortlist;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.base.BaseView;
import com.tuojin.tvfilm.contract.SortListContract;
import com.tuojin.tvfilm.presenter.SortListPresenterImpl;

/**
 * 文 件 名: SortListFragment
 * 创 建 人: Administrator
 * 创建日期: 2016/9/21 15:53
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class SortListFragment  extends BaseFragment<SortListContract.View, SortListPresenterImpl> implements SortListContract.View, BaseView {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sortlist;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected SortListPresenterImpl initPresenter() {
        return null;
    }
}
