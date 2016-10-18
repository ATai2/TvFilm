package com.tuojin.tvfilm.model;

import com.google.gson.Gson;
import com.tuojin.tvfilm.bean.DirectListBean;
import com.tuojin.tvfilm.bean.DirectorBean;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.FilmSearchKeyWordBean;
import com.tuojin.tvfilm.bean.FilmTypeBean;
import com.tuojin.tvfilm.contract.CateListFilmContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.CateListFilmPresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by MVPHelper on 2016/09/27
 */

public class CateListFilmModelImpl implements CateListFilmContract.Model {
    CateListFilmPresenterImpl mPresenter;
    TvFilmNetWorkWS netWorkWS = new TvFilmNetWorkWS();

    public CateListFilmModelImpl(CateListFilmPresenterImpl presenter) {
        mPresenter = presenter;
    }

    /**
     * 导演列表初始化
     *
     * @param position
     */
    @Override
    public void initRadioGroup(int position) {

        netWorkWS.sendMsg(Constant.PADMAC +
                "|getDoctorList|startIndex=0&endIndex=20"
                , new TvFilmNetWorkWS.Success() {
                    @Override
                    public void excute(String data) {

                        List<DirectorBean> list = new Gson().fromJson(data, DirectListBean.class).getData().getData();
                        if (list != null) {
                            mPresenter.initViewRadioGroup(list);
                        }
                    }
                }, new TvFilmNetWorkWS.Failure() {
                    @Override
                    public void excute(String data) {

                    }
                });
    }

    /**
     * 导演--电影列表
     *
     * @param directBean
     */
    @Override
    public void onItemClick(DirectorBean directBean) {
        netWorkWS.sendMsg(Constant.PADMAC +
                "|getFilmList|terminalCode=" +
                Constant.TERMINAL_CODE +
                "&searchMode=&keyword=&movieTypeStr=&placeId=&yearId=&actorId=&directorId=" +
                        directBean.getId() + "&startIndex=0&endIndex=20"
                , new TvFilmNetWorkWS.Success() {
                    @Override
                    public void excute(String data) {
                        List<FilmBean> data1 = new Gson().fromJson(data, FilmSearchKeyWordBean.class).getData().getData();
                        mPresenter.initFilmFragment(data1);
                    }
                }, new TvFilmNetWorkWS.Failure() {
                    @Override
                    public void excute(String data) {

                    }
                });
    }

    /**
     * 电影类型，---列表
     *
     * @param filmTypeBean 根据电影类型名查找
     */
    @Override
    public void onFilmTypeItemClick(FilmTypeBean filmTypeBean) {
        try {
            netWorkWS.sendMsg(Constant.PADMAC +
                    "|getFilmList|terminalCode=SMET15128361&searchMode=&keyword=&movieTypeStr=" +
                            URLEncoder.encode(filmTypeBean.getMovieType(), "utf-8")
                            + "&placeId=&yearId=&actorId=&directorId=&startIndex=0&endIndex=20"
                    , new TvFilmNetWorkWS.Success() {
                        @Override
                        public void excute(String data) {
                            List<FilmBean> data1 = new Gson().fromJson(data, FilmSearchKeyWordBean.class).getData().getData();
                            mPresenter.initFilmFragment(data1);
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

    @Override
    public void onFilmTypeItemInit(String catcode) {
        try {
            netWorkWS.sendMsg(Constant.PADMAC+"|getFilmList|terminalCode=" +
                    Constant.TERMINAL_CODE +
                    "&searchMode=&keyword=&movieTypeStr=" +
                            URLEncoder.encode(catcode, "utf-8")
                            + "&placeId=&yearId=&actorId=&directorId=&startIndex=0&endIndex=20"
                    , new TvFilmNetWorkWS.Success() {
                        @Override
                        public void excute(String data) {
                            List<FilmBean> data1 = new Gson().fromJson(data, FilmSearchKeyWordBean.class).getData().getData();
                            mPresenter.initFilmFragment(data1);
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

    public void initRadioGroupFilmType() {

    }
}