package com.tuojin.tvfilm.presenter;
import com.tuojin.tvfilm.contract.FrameFilmCateListContract;
import com.tuojin.tvfilm.model.FrameFilmCateListModelImpl;

/**
* Created by MVPHelper on 2016/09/27
*/

public class FrameFilmCateListPresenterImpl implements FrameFilmCateListContract.Presenter{

    FrameFilmCateListModelImpl mModel=new FrameFilmCateListModelImpl(this);
    @Override
    public void findFilmList() {

    }
}