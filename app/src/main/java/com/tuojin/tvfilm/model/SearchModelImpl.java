package com.tuojin.tvfilm.model;

import com.tuojin.tvfilm.contract.SearchContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.SearchPresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by MVPHelper on 2016/09/21
 */

public class SearchModelImpl implements SearchContract.Model {
    SearchPresenterImpl mPresenter;
    TvFilmNetWorkWS mNetWorkWS = new TvFilmNetWorkWS();

    public SearchModelImpl(SearchPresenterImpl presenter) {
        mPresenter = presenter;
    }

    @Override
    public void search(String key) {
//        mPresenter.mView.showLoading();
        try {
            String cmd = Constant.PADMAC +
                    "|getFilmList|terminalCode=" +
                    Constant.TERMINAL_CODE +
                    "&searchMode=&keyword=" +
                    URLEncoder.encode(key, "utf-8") +
                    "&movieTypeStr=&placeId=&yearId=&actorId=&directorId=&startIndex=0&endIndex=100";
            mNetWorkWS.sendMsg(cmd, 6);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hotSearch() {

        String cmd = Constant.PADMAC +
                "|getFilmListOrderByHotest|orderByFeild=hotest&orderByType=desc&terminalCode=" +
                Constant.TERMINAL_CODE +
                "&startIndex=0&endIndex=20";
        mNetWorkWS.sendMsg(cmd, 104);

    }
}