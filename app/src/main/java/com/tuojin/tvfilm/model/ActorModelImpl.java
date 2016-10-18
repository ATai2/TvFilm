package com.tuojin.tvfilm.model;

import com.google.gson.Gson;
import com.tuojin.tvfilm.bean.ActorBean;
import com.tuojin.tvfilm.bean.ActorListBean;
import com.tuojin.tvfilm.contract.ActorContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.ActorPresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

import java.util.List;

/**
* Created by MVPHelper on 2016/10/13
*/

public class ActorModelImpl implements ActorContract.Model{
    ActorPresenterImpl mPresenter;
    TvFilmNetWorkWS mNetWorkWS=new TvFilmNetWorkWS();
    public ActorModelImpl(ActorPresenterImpl presenter) {
        mPresenter = presenter;
    }

    @Override
    public void list(String a) {
        mNetWorkWS.sendMsg(Constant.PADMAC +
                "|getActorList|startIndex=0&endIndex=50", new TvFilmNetWorkWS.Success() {
            @Override
            public void excute(String data) {
                List<ActorBean> list = new Gson().fromJson(data, ActorListBean.class).getData().getData();
                mPresenter.initList(list);
            }
        }, new TvFilmNetWorkWS.Failure() {
            @Override
            public void excute(String data) {

            }
        });
    }

    @Override
    public void listByActor(int id, final String movie_actor) {
        mNetWorkWS.sendMsg(Constant.PADMAC +
                "|getFilmList|terminalCode=" +
                Constant.TERMINAL_CODE +
                "&searchMode=&keyword=&movieTypeStr=&placeId=" +
                "&yearId=&actorId=" +
                id +
                "&directorId=&startIndex=0&endIndex=100", new TvFilmNetWorkWS.Success() {
            @Override
            public void excute(String data) {
                mPresenter.initListByActor(data,movie_actor);
            }
        }, new TvFilmNetWorkWS.Failure() {
            @Override
            public void excute(String data) {

            }
        });
    }
}