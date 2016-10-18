package com.tuojin.tvfilm.presenter;
import com.tuojin.tvfilm.base.BasePresenter;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.FilmTypeBean;
import com.tuojin.tvfilm.contract.FilmTypeContract;
import com.tuojin.tvfilm.model.FilmTypeModelImpl;

import java.util.List;

/**
* Created by MVPHelper on 2016/09/29
*/

public class FilmTypePresenterImpl extends BasePresenter<FilmTypeContract.View> implements FilmTypeContract.Presenter{

FilmTypeModelImpl mModel=new FilmTypeModelImpl(this);
    @Override
    public void onClick(int type) {
        mModel.onClick(type);
    }

    @Override
    public void onResume(int sortType) {
        mModel.onClick(sortType);
    }

    @Override
    public void menu() {
        mModel.menu();
    }

    @Override
    public void initMenu(List<FilmTypeBean> list) {
        mView.initMenu(list);
    }

    public void initFilmFragment(List<FilmBean> data){
        mView.initFilmFragment(data);
    }
}