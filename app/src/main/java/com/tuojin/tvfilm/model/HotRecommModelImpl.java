package com.tuojin.tvfilm.model;

import com.google.gson.Gson;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.RecommBean;
import com.tuojin.tvfilm.contract.HotRecommContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.HotRecommPresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

import java.util.List;

/**
 * Created by MVPHelper on 2016/09/20
 */

public class HotRecommModelImpl implements HotRecommContract.Model {

    HotRecommPresenterImpl mRecommPresenter;

    public HotRecommModelImpl(HotRecommPresenterImpl recommPresenter) {
        mRecommPresenter = recommPresenter;
    }

    @Override
    public void getRecommList(int page) {
        //网络请求
        TvFilmNetWorkWS netWorkWS = new TvFilmNetWorkWS();
        netWorkWS.sendMsg(Constant.PADMAC +
                        "|getFilmListOrderByHotest|orderByFeild=hotest&orderByType=desc&terminalCode=" +
                Constant.TERMINAL_CODE +
                "&startIndex=0&endIndex=10"
                , new TvFilmNetWorkWS.Success() {
                    @Override
                    public void excute(String data) {
                        List<FilmBean> mDatas = new Gson().fromJson(data, RecommBean.class).getData().getData();
                        int i = 0;
                        mRecommPresenter.loadData(mDatas);
                    }
                }, new TvFilmNetWorkWS.Failure() {
                    @Override
                    public void excute(String data) {
                    }
                });
    }
}