package com.tuojin.tvfilm.model;

import com.tuojin.tvfilm.contract.ActorContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.ActorPresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

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
                "|getActorList|startIndex=0&endIndex=50");
    }

    @Override
    public void listByActor(int id, final String movie_actor) {
        mNetWorkWS.sendMsg(Constant.PADMAC +
                "|getFilmList|terminalCode=" +
                Constant.TERMINAL_CODE +
                "&searchMode=&keyword=&movieTypeStr=&placeId=" +
                "&yearId=&actorId=" +
                id +
                "&directorId=&startIndex=0&endIndex=100");
    }
}