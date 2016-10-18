package com.tuojin.tvfilm.model;
import com.google.gson.Gson;
import com.tuojin.tvfilm.bean.LiveBean;
import com.tuojin.tvfilm.bean.LiveListBean;
import com.tuojin.tvfilm.contract.LiveContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.LivePresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

import java.util.List;

/**
* Created by MVPHelper on 2016/10/13
*/

public class LiveModelImpl implements LiveContract.Model{
    LivePresenterImpl mPresenter;
    TvFilmNetWorkWS mNetWorkWS=new TvFilmNetWorkWS();
    public LiveModelImpl(LivePresenterImpl presenter) {
        mPresenter = presenter;
    }

    @Override
    public void menu() {
        mNetWorkWS.sendMsg(Constant.PADMAC +
                        "|getLiveCategoryList|startIndex=0&endIndex=27"
                , new TvFilmNetWorkWS.Success() {
                    @Override
                    public void excute(String data) {
                        List<LiveBean> mDatas = new Gson().fromJson(data, LiveListBean.class).getData().getData();
                        int i = 0;
                        mPresenter.initMenu(mDatas);
                    }
                }, new TvFilmNetWorkWS.Failure() {
                    @Override
                    public void excute(String data) {
                    }
                });
    }
}