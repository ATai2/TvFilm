package com.tuojin.tvfilm.presenter;
import com.tuojin.tvfilm.base.BasePresenter;
import com.tuojin.tvfilm.bean.ActorBean;
import com.tuojin.tvfilm.contract.ActorContract;
import com.tuojin.tvfilm.model.ActorModelImpl;

import java.util.List;

/**
* Created by MVPHelper on 2016/10/13
*/

public class ActorPresenterImpl extends BasePresenter<ActorContract.View> implements ActorContract.Presenter{
    ActorModelImpl mModel=new ActorModelImpl(this);
    @Override
    public void list(String a ){
        mModel.list(a);
    }

    @Override
    public void initList(List<ActorBean> list) {
        mView.initList(list);
    }

    @Override
    public void listByActor(int id, String movie_actor) {
        mModel.listByActor(id,movie_actor);
    }

    @Override
    public void initListByActor(String data, String movie_actor) {
        mView.initListByActor(data,movie_actor);
    }
}