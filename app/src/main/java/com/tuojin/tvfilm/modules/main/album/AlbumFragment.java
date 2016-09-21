package com.tuojin.tvfilm.modules.main.album;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.contract.AlbumContract;
import com.tuojin.tvfilm.presenter.AlbumPresenterImpl;

/**
 * 文 件 名: AlbumFragment
 * 创 建 人: Administrator
 * 创建日期: 2016/9/21 16:01
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class AlbumFragment extends BaseFragment<AlbumContract.View,AlbumPresenterImpl> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_album;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected AlbumPresenterImpl initPresenter() {
        return null;
    }
}
