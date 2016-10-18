package com.tuojin.tvfilm.model;

import com.google.gson.Gson;
import com.tuojin.tvfilm.bean.AreaBean;
import com.tuojin.tvfilm.bean.AreaListBean;
import com.tuojin.tvfilm.contract.AreaContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.AreaPresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

import java.util.List;

/**
* Created by MVPHelper on 2016/10/09
*/

public class AreaModelImpl implements AreaContract.Model{
    AreaPresenterImpl mPresenter;

    TvFilmNetWorkWS mNetWorkWS=new TvFilmNetWorkWS();
    public AreaModelImpl(AreaPresenterImpl presenter) {
        mPresenter = presenter;
    }

    @Override
    public void list(String a) {
        mNetWorkWS.sendMsg(Constant.PADMAC+"|getPlaceList|startIndex=0&endIndex=50", new TvFilmNetWorkWS.Success() {
            @Override
            public void excute(String data) {
                List<AreaBean> list = new Gson().fromJson(data, AreaListBean.class).getData().getData();
                mPresenter.initList(list);
            }
        }, new TvFilmNetWorkWS.Failure() {
            @Override
            public void excute(String data) {

            }
        });
    }

    @Override
    public void listByArea(int id, final String movie_country) {
        mNetWorkWS.sendMsg(Constant.PADMAC +
                "|getFilmList|terminalCode=" +
                Constant.TERMINAL_CODE +
                "&searchMode=&keyword=&movieTypeStr=&placeId=" +
                id +
                "&yearId=&actorId=&directorId=&startIndex=0&endIndex=100", new TvFilmNetWorkWS.Success() {
            @Override
            public void excute(String data) {
                mPresenter.initListByArea(data,movie_country);
            }
        }, new TvFilmNetWorkWS.Failure() {
            @Override
            public void excute(String data) {

            }
        });
    }
}