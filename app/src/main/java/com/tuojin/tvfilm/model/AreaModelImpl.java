package com.tuojin.tvfilm.model;

import com.tuojin.tvfilm.contract.AreaContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.AreaPresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

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
        mNetWorkWS.sendMsg(Constant.PADMAC+"|getPlaceList|startIndex=0&endIndex=50");
    }

    @Override
    public void listByArea(int id, final String movie_country) {
        mNetWorkWS.sendMsg(Constant.PADMAC +
                "|getFilmList|terminalCode=" +
                Constant.TERMINAL_CODE +
                "&searchMode=&keyword=&movieTypeStr=&placeId=" +
                id +
                "&yearId=&actorId=&directorId=&startIndex=0&endIndex=100",2);
    }
}