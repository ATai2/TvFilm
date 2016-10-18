package com.tuojin.tvfilm.model;

import com.google.gson.Gson;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.FilmDetailBean;
import com.tuojin.tvfilm.bean.RecommBean;
import com.tuojin.tvfilm.contract.FilmDetailContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.FilmDetailPresenterImpl;
import com.tuojin.tvfilm.utils.Constant;
import com.tuojin.tvfilm.utils.LogUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * Created by MVPHelper on 2016/09/22
 */

public class FilmDetailModelImpl implements FilmDetailContract.Model {
    public FilmDetailModelImpl(FilmDetailPresenterImpl presenter) {
        mPresenter = presenter;
    }

    FilmDetailPresenterImpl mPresenter;
    TvFilmNetWorkWS netWorkWS = new TvFilmNetWorkWS();

    @Override
    public void onResume(String mid, String uuid) {
        String cmd = Constant.PADMAC +
                "|getFilmDetail|mid=" +
                mid +
                "&uuid=" +
                uuid +
                "&terminalCode=" +
                Constant.TERMINAL_CODE;

        LogUtils.d("11", cmd);
        netWorkWS.sendMsg(cmd, new TvFilmNetWorkWS.Success() {
            @Override
            public void excute(String data) {
                FilmDetailBean.DataBean.FilmDetailDataBean bean = new Gson().fromJson(data, FilmDetailBean.class).getData().getData();
                mPresenter.refresh(bean);

            }
        }, new TvFilmNetWorkWS.Failure() {
            @Override
            public void excute(String data) {

            }
        });
    }

    @Override
    public void onResumeInitRecycleView() {

    }

    @Override
    public void play(FilmDetailBean.DataBean.FilmDetailDataBean bean) {
        try {
            String replace = bean.getAudio().replace("/", "%2F");
            String encode = URLEncoder.encode(bean.getFilename(), "utf-8");
            String kdm = bean.getKdm_addr().replace("/", "%2F");
            String cmd = Constant.PADMAC + "|startPlay|mac=" + Constant.PADMAC +
                    "&ipAddress=" +
                    Constant.IP_TERMINAL +
                    "&terminalCode=" + Constant.TERMINAL_CODE
                    +
                    "&splname=" + Constant.PADMAC +
                    "&cplname=" + encode +
//                    "%E7%BE%8E%E4%BA%BA%E9%B1%BC" +
                    "&cpluuid=" + bean.getUuid() +
//                    "023416ed-c7ed-48db-b1b3-78262fe7348e" +
                    "&total_duration=" + bean.getFilmlength() +
                    "&cpl_dcpuri=" + replace +
//                    "%2Fopt%2Fvideo%2Fnasbak%2FBESTV160422133926003485%2FDCP%2F023416ed-c7ed-48db-b1b3-78262fe7348e%2Favc_MeiRenYu_ac3_vid.mxf" +
                    "&cpl_kdmuri=" + kdm +
//                    "%2Fopt%2Fvideo%2Fnasbak%2FBESTV160422133926003485%2FKDM%2FSMET1" +
//                    "5128361%2F023416ed-c7ed-48db-b1b3-78262fe7348e_SMET15128361_170331235959_9999.xml" +
                    "&timeStamp=" + new Date().getTime();
            netWorkWS.sendMsg(cmd
                    , new TvFilmNetWorkWS.Success() {
                        @Override
                        public void excute(String data) {

                            int i = 0;
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
    public void initList() {
        //网络请求
        TvFilmNetWorkWS netWorkWS = new TvFilmNetWorkWS();
        netWorkWS.sendMsg(Constant.PADMAC + "|getFilmListOrderByNewest|orderByFeild=newest&orderByType=desc&terminalCode=" +
                        Constant.TERMINAL_CODE + "&startIndex=0&endIndex=10"
                , new TvFilmNetWorkWS.Success() {
                    @Override
                    public void excute(String data) {
                        List<FilmBean> mDatas = new Gson().fromJson(data, RecommBean.class).getData().getData();
                        mPresenter.initListUI(mDatas);
                    }
                }, new TvFilmNetWorkWS.Failure() {
                    @Override
                    public void excute(String data) {
                    }
                });
    }

    public void stop(FilmDetailBean.DataBean.FilmDetailDataBean bean) {
        String cmd = Constant.PADMAC + "|stopPlay|mac=" +
                Constant.PADMAC +
                "&ipAddress=" + Constant.IP_TERMINAL +
                "&terminalCode=" + Constant.TERMINAL_CODE +
                "&timeStamp=" + new Date().getTime();

        LogUtils.d("11", cmd);
        netWorkWS.sendMsg(cmd, new TvFilmNetWorkWS.Success() {
            @Override
            public void excute(String data) {


            }
        }, new TvFilmNetWorkWS.Failure() {
            @Override
            public void excute(String data) {

            }
        });
    }
//  通过网络获得影片详情

}