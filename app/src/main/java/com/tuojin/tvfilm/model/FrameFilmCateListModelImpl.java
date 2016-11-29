package com.tuojin.tvfilm.model;
import com.tuojin.tvfilm.contract.FrameFilmCateListContract;
import com.tuojin.tvfilm.presenter.FrameFilmCateListPresenterImpl;

/**
* Created by MVPHelper on 2016/09/27
*/

public class FrameFilmCateListModelImpl implements FrameFilmCateListContract.Model{
    FrameFilmCateListPresenterImpl mPresenter;
    public FrameFilmCateListModelImpl(FrameFilmCateListPresenterImpl frameFilmCateListPresenter) {
        this.mPresenter=frameFilmCateListPresenter;
    }

}