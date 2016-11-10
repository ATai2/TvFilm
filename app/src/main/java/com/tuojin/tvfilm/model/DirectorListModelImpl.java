package com.tuojin.tvfilm.model;

import com.tuojin.tvfilm.contract.DirectorListContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.DirectorListPresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

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
                );
    }

    @Override
    public void listByDirector(int id, final String movie_director) {
        netWorkWS.sendMsg(Constant.PADMAC +
                "|getFilmList|terminalCode=" +
                Constant.TERMINAL_CODE +
                "&searchMode=&keyword=&movieTypeStr=&placeId=" +

                "&yearId=&actorId=&directorId=" +
                id +
                "&startIndex=0&endIndex=100",3);
    }
}