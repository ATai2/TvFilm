package com.tuojin.tvfilm.model;

import com.tuojin.tvfilm.contract.LiveContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.LivePresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

/**
* Created by MVPHelper on 2016/10/13
*/

public class LiveModelImpl implements LiveContract.Model{
    LivePresenterImpl mPresenter;
    TvFilmNetWorkWS mNetWorkWS=new TvFilmNetWorkWS();

    public LiveModelImpl() {
    }

    public LiveModelImpl(LivePresenterImpl presenter) {
        mPresenter = presenter;
    }

    @Override
    public void menu() {
        mNetWorkWS.sendMsg(Constant.PADMAC +
                        "|getLiveCategoryList|startIndex=0&endIndex=27"
                );
    }

    @Override
    public void list(String text) {
        String cmd = Constant.PADMAC +
                "|getLiveContentList|terminalCode=" +
                Constant.TERMINAL_CODE +
                "&lcname=NBA&startIndex=0&endIndex=20";
        mNetWorkWS.sendMsg(cmd);
    }
}