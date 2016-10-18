package com.tuojin.tvfilm.presenter;

import com.tuojin.tvfilm.base.BasePresenter;
import com.tuojin.tvfilm.bean.DirectorBean;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.FilmTypeBean;
import com.tuojin.tvfilm.contract.CateListFilmContract;
import com.tuojin.tvfilm.model.CateListFilmModelImpl;
import com.tuojin.tvfilm.modules.catelist.framecatelist.CateFilmListActivty;

import java.util.List;

/**
* Created by MVPHelper on 2016/09/27
*/

public class CateListFilmPresenterImpl extends BasePresenter<CateListFilmContract.View> implements CateListFilmContract.Presenter{
    CateListFilmModelImpl mModel=new CateListFilmModelImpl(this);
    @Override
    public void initRadioGroup(int position) {
        switch (position){
            case 0:
                mModel.initRadioGroupFilmType();
                break;
        }
        mModel.initRadioGroup(position);
    }

    @Override
    public void initViewRadioGroup(List<DirectorBean> list) {
        mView.initViewRadioGroup(list);
    }

    @Override
    public void onItemClick(CateFilmListActivty cateFilmListActivty, DirectorBean directBean) {
        mModel.onItemClick(directBean);
    }

    @Override
    public void initFilmFragment(List<FilmBean> data1) {
        mView.initFilmFragment(data1);
    }

    @Override
    public void onFilmTypeItemClick(CateFilmListActivty context, FilmTypeBean filmTypeBean) {
        mModel.onFilmTypeItemClick(filmTypeBean);
    }

    public void onResume(String catcode) {
        mModel.onFilmTypeItemInit(catcode);
    }
}