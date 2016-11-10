package com.tuojin.tvfilm.presenter;

import com.tuojin.tvfilm.base.BasePresenter;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.contract.SortListContract;
import com.tuojin.tvfilm.model.SortListModelImpl;

import java.util.List;

/**
 * Created by MVPHelper on 2016/09/21
 */

public class SortListPresenterImpl extends BasePresenter<SortListContract.View> implements SortListContract.Presenter {
    SortListModelImpl mModel = new SortListModelImpl(this);

    @Override
    public void onResume(int catcode,int page) {
        mView.showLoading();
        switch (catcode) {
            case 0:
                mModel.getRecentFilmList(page);
                break;
            case 1:
                mModel.getHotFilmList(page);
                break;
            case 2:
                mModel.getBigFilmList(page);
                break;
            case 3:
                mModel.getAdFilmList(page);
                break;
            case 4:
                mModel.getDouBanFilmList(page);
                break;
        }
        mView.hideLoading();
    }

    @Override
    public void refreshUI(List<FilmBean> beanList) {

    }
}