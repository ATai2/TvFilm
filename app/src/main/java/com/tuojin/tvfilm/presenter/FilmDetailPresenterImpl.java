package com.tuojin.tvfilm.presenter;
import com.tuojin.tvfilm.base.BasePresenter;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.FilmDetailBean;
import com.tuojin.tvfilm.contract.FilmDetailContract;
import com.tuojin.tvfilm.model.FilmDetailModelImpl;
import com.tuojin.tvfilm.utils.LogUtils;

import java.util.List;

/**
* Created by MVPHelper on 2016/09/22
*/

public class FilmDetailPresenterImpl extends BasePresenter<FilmDetailContract.View> implements FilmDetailContract.Presenter{

    private FilmDetailModelImpl mDetailModel= new FilmDetailModelImpl(this);

    @Override
    public void onResume(String mid,String uuid) {
        LogUtils.d("11","onResume"+mid+uuid);
        mDetailModel.onResume( mid, uuid);
        mDetailModel.onResumeInitRecycleView();
    }

    @Override
    public void refresh(FilmDetailBean.DataBean.FilmDetailDataBean bean) {
        mView.refresh(bean);
    }

    @Override
    public void play(FilmDetailBean.DataBean.FilmDetailDataBean film) {
        mDetailModel.play(film);
    }

    @Override
    public void initList() {
        mDetailModel.initList();
    }

    @Override
    public void initListUI(List<FilmBean> mDatas) {
        mView.initListUI(mDatas);
    }

    @Override
    public void stop(FilmDetailBean.DataBean.FilmDetailDataBean bean) {
        mDetailModel.stop(bean);
    }
}