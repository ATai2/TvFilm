package com.tuojin.tvfilm.modules.catelist.fragments;


import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.bean.FilmTypeBean;
import com.tuojin.tvfilm.contract.CateListContract;
import com.tuojin.tvfilm.presenter.CateListPresenterImpl;


public class FilmTypeFragment extends BaseFragment<CateListContract.View<FilmTypeBean>,CateListPresenterImpl<FilmTypeBean>> {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_film_type;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected CateListPresenterImpl initPresenter() {
        return null;
    }
}
