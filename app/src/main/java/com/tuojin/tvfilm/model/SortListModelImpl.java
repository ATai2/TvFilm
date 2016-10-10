package com.tuojin.tvfilm.model;

import com.google.gson.Gson;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.RecommBean;
import com.tuojin.tvfilm.contract.SortListContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.SortListPresenterImpl;

import java.util.List;

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
        mNetWorkWS.sendMsg("PAD1465889962927|getFilmListOrderByHotest|orderByFeild=hotest&orderByType=desc&terminalCode=SMET15128361&startIndex=" +
                20 * page +
                "&endIndex=" +
                20 * (page + 1), new TvFilmNetWorkWS.Success() {
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
    }
    /**
     * 获得最新电影列表
     * @param page
     */
    @Override
    public void getRecentFilmList(int page) {
        mNetWorkWS.sendMsg("PAD1465889962927|getFilmListOrderByNewest|orderByFeild=newest&orderByType=desc&terminalCode=SMET15128361&startIndex=" +
                20 * page +
                "&endIndex=" +
                20 * (page + 1), new TvFilmNetWorkWS.Success() {
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
    }
    /**
     * 获得豆瓣推荐电影列表
     * @param page
     */
    @Override
    public void getDouBanFilmList(int page) {
        mNetWorkWS.sendMsg("PAD1465889962927|getFilmListOrderByScore|orderByFeild=score&orderByType=desc&terminalCode=SMET15128361&startIndex=" +
                20 * page +
                "&endIndex=" +
                20 * (page + 1), new TvFilmNetWorkWS.Success() {
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
    }

    /**
     * 获得大片列表
     * @param page
     */
    public void getBigFilmList(int page) {
        mNetWorkWS.sendMsg("PAD1465889962927|getFilmListOrderByNewest|orderByFeild=newest&orderByType=desc&terminalCode=SMET15128361&startIndex=" +
                4 * page +
                "&endIndex=" +
                4 * (page + 1), new TvFilmNetWorkWS.Success() {
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
    }
    /**
     * 获得广告列表
     * @param page
     */
    public void getAdFilmList(int page) {
        mNetWorkWS.sendMsg("PAD1465889962927|getFilmListOrderByNewest|orderByFeild=newest&orderByType=desc&terminalCode=SMET15128361&startIndex=" +
                4 * page +
                "&endIndex=" +
                4 * (page + 1), new TvFilmNetWorkWS.Success() {
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
    }
}