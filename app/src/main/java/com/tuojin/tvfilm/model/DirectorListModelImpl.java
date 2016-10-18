package com.tuojin.tvfilm.model;

import com.google.gson.Gson;
import com.tuojin.tvfilm.bean.DirectListBean;
import com.tuojin.tvfilm.bean.DirectorBean;
import com.tuojin.tvfilm.contract.DirectorListContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.DirectorListPresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

import java.util.List;

/**
* Created by MVPHelper on 2016/09/30
*/

public class DirectorListModelImpl implements DirectorListContract.Model{

    DirectorListPresenterImpl mPresenter;
    TvFilmNetWorkWS netWorkWS=new TvFilmNetWorkWS();

    public DirectorListModelImpl(DirectorListPresenterImpl presenter) {
        mPresenter = presenter;
    }

    @Override
    public void list(String a) {
        netWorkWS.sendMsg(Constant.PADMAC +
                "|getDoctorList|startIndex=0&endIndex=20"
                , new TvFilmNetWorkWS.Success() {
                    @Override
                    public void excute(String data) {

                        List<DirectorBean> list = new Gson().fromJson(data, DirectListBean.class).getData().getData();
                        if (list != null) {
                            mPresenter.initList(list);
                        }
                    }
                }, new TvFilmNetWorkWS.Failure() {
                    @Override
                    public void excute(String data) {

                    }
                });
    }

    @Override
    public void listByDirector(int id, final String movie_director) {
        netWorkWS.sendMsg(Constant.PADMAC +
                "|getFilmList|terminalCode=" +
                Constant.TERMINAL_CODE +
                "&searchMode=&keyword=&movieTypeStr=&placeId=" +

                "&yearId=&actorId=&directorId=" +
                id +
                "&startIndex=0&endIndex=100", new TvFilmNetWorkWS.Success() {
            @Override
            public void excute(String data) {
                mPresenter.initListByDirector(data,movie_director);
            }
        }, new TvFilmNetWorkWS.Failure() {
            @Override
            public void excute(String data) {

            }
        });
    }
}