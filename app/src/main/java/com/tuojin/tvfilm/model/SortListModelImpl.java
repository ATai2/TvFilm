package com.tuojin.tvfilm.model;

import com.tuojin.tvfilm.contract.SortListContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.SortListPresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

/**
* Created by MVPHelper on 2016/09/21
*/

public class SortListModelImpl implements SortListContract.Model{

    TvFilmNetWorkWS mNetWorkWS=new TvFilmNetWorkWS();

    SortListPresenterImpl mPresenter;

    public SortListModelImpl(SortListPresenterImpl presenter) {
        mPresenter = presenter;
    }

    /**
     * 获得最热电影列表
     * @param page
     */
    @Override
    public void getHotFilmList(int page) {
        mNetWorkWS.sendMsg(Constant.PADMAC +
                "|getFilmListOrderByHotest|orderByFeild=hotest&orderByType=desc&terminalCode=" +
                Constant.TERMINAL_CODE +
                "&startIndex=" +
                20 * page +
                "&endIndex=" +
                20 * (page + 1),103);
    }
    /**
     * 获得最新电影列表
     * @param page
     */
    @Override
    public void getRecentFilmList(int page) {
        mNetWorkWS.sendMsg(Constant.PADMAC +
                "|getFilmListOrderByNewest|orderByFeild=newest&orderByType=desc&terminalCode=" +
                Constant.TERMINAL_CODE +
                "&startIndex=" +
                20 * page +
                "&endIndex=" +
                20 * (page + 1));
    }
    /**
     * 获得豆瓣推荐电影列表
     * @param page
     */
    @Override
    public void getDouBanFilmList(int page) {
        mNetWorkWS.sendMsg(Constant.PADMAC +
                "|getFilmListOrderByScore|orderByFeild=score&orderByType=desc&terminalCode=" +
                Constant.TERMINAL_CODE +
                "&startIndex=" +
                20 * page +
                "&endIndex=" +
                20 * (page + 1));
    }

    /**
     * 获得大片列表
     * PAD1477376621152|getFilmListOrderByPaymovie|orderByFeild=paymovie&orderByType=desc&terminalCode=SMET14017428&startIndex=0&endIndex=8
     * @param page
     */
    public void getBigFilmList(int page) {
        mNetWorkWS.sendMsg(Constant.PADMAC +
                "|getFilmListOrderByPaymovie|orderByFeild=paymovie&orderByType=desc&terminalCode=" +
                Constant.TERMINAL_CODE+
                "&startIndex=0&endIndex=100"
               );
    }
    /**
     * 获得广告列表
     * @param page
     */
    public void getAdFilmList(int page) {
        mNetWorkWS.sendMsg(Constant.PADMAC +
                "|getFilmListOrderByNewest|orderByFeild=newest&orderByType=desc&terminalCode=" +
                Constant.TERMINAL_CODE+
                "&startIndex=" +
                4 * page +
                "&endIndex=" +
                4 * (page + 1));
    }
}