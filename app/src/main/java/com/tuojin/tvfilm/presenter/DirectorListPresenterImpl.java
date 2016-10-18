package com.tuojin.tvfilm.presenter;

import com.tuojin.tvfilm.base.BasePresenter;
import com.tuojin.tvfilm.bean.DirectorBean;
import com.tuojin.tvfilm.contract.DirectorListContract;
import com.tuojin.tvfilm.model.DirectorListModelImpl;

import java.util.List;

/**
* Created by MVPHelper on 2016/09/30
*/

public class DirectorListPresenterImpl extends BasePresenter<DirectorListContract.View> implements DirectorListContract.Presenter{
    DirectorListModelImpl mModel=new DirectorListModelImpl(this);

    @Override
    public void list(String a) {
mModel.list(a);
    }

    @Override
    public void initList(List<DirectorBean> list) {
mView.initList(list);
    }

    @Override
    public void listByDirector(int id, String movie_director) {
        mModel.listByDirector(id,movie_director);
    }

    @Override
    public void initListByDirector(String data, String movie_director) {
        mView.initListByDirector(data,movie_director);
    }
}