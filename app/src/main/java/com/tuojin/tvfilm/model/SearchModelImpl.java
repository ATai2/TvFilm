package com.tuojin.tvfilm.model;
import com.google.gson.Gson;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.RecommBean;
import com.tuojin.tvfilm.contract.SearchContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.SearchPresenterImpl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import static android.R.attr.data;
import static android.R.attr.searchMode;

/**
* Created by MVPHelper on 2016/09/21
*/

public class SearchModelImpl implements SearchContract.Model{
    SearchPresenterImpl mPresenter;
    TvFilmNetWorkWS mNetWorkWS=new TvFilmNetWorkWS();

    public SearchModelImpl(SearchPresenterImpl presenter) {
        mPresenter = presenter;
    }

    @Override
    public void search(String key) {
        try {
            mNetWorkWS.sendMsg("PAD1465889962927|getFilmList|terminalCode=SMET15128361&searchMode=&keyword=" +
                    URLEncoder.encode(key,"utf-8") +
                    "&movieTypeStr=&placeId=&yearId=&actorId=&directorId=&startIndex=0&endIndex=100", new TvFilmNetWorkWS.Success() {
                @Override
                public void excute(String data) {
                    List<FilmBean> beanList = new Gson().fromJson(data, RecommBean.class).getData().getData();
                    if (beanList != null) {
                        mPresenter.refreshUI(beanList);
                    }

                }
            }, new TvFilmNetWorkWS.Failure() {
                @Override
                public void excute(String data) {

                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}