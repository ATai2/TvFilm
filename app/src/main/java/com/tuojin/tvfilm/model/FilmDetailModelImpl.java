package com.tuojin.tvfilm.model;

import com.tuojin.tvfilm.bean.FilmDetailBean;
import com.tuojin.tvfilm.contract.FilmDetailContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.FilmDetailPresenterImpl;
import com.tuojin.tvfilm.utils.Constant;
import com.tuojin.tvfilm.utils.LogUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by MVPHelper on 2016/09/22
 */

public class FilmDetailModelImpl implements FilmDetailContract.Model {
    public FilmDetailModelImpl(FilmDetailPresenterImpl presenter) {
        mPresenter = presenter;
    }
    TvFilmNetWorkWS netWorkWS =new TvFilmNetWorkWS();
    FilmDetailPresenterImpl mPresenter;


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
        netWorkWS.sendMsg(cmd);
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
                    "&terminalCode=" + Constant.TERMINAL_CODE +
                    "&splname=" + Constant.PADMAC +
                    "&cplname=" + encode +
                    "&cpluuid=" + bean.getUuid() +
                    "&total_duration=" + bean.getFilmlength() +
                    "&cpl_dcpuri=" + replace +
                    "&cpl_kdmuri=" + kdm +
                    "&timeStamp=" + new Date().getTime()+
                    "&charge_flag="+bean.getCharge_flag();
            netWorkWS.sendMsg(cmd);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initList() {
        //网络请求

        netWorkWS.sendMsg(Constant.PADMAC + "|getFilmListOrderByHotest|orderByFeild=newest&orderByType=desc&terminalCode=" +
                        Constant.TERMINAL_CODE + "&startIndex=0&endIndex=10"
                ,102);
    }


    @Override
    public void getQrCode(FilmDetailBean.DataBean.FilmDetailDataBean bean) {

            String cmd = Constant.PADMAC + "|payAli|mac=" + Constant.PADMAC +
                    "&ipAddress=" +
                    Constant.IP_TERMINAL +
                    "&terminalCode=" + Constant.TERMINAL_CODE +
                    "&cpluuid=" + bean.getUuid() +
                    "&timeStamp=" + new Date().getTime();
            netWorkWS.sendMsg(cmd);

    }

    @Override
    public void pause() {

        String cmd = Constant.PADMAC + "|pausePlay|mac=" +
                Constant.PADMAC +
                "&ipAddress=" + Constant.IP_TERMINAL +
                "&terminalCode=" + Constant.TERMINAL_CODE +
                "&timeStamp=" + new Date().getTime();
        LogUtils.d("11", cmd);
        netWorkWS.sendMsg(cmd);
    }


    public void playStatus() {

        String cmd = Constant.PADMAC + "|playStatus|mac=" +
                Constant.PADMAC +
                "&ipAddress=" + Constant.IP_TERMINAL +
                "&terminalCode=" + Constant.TERMINAL_CODE +
                "&timeStamp=" + new Date().getTime();
        LogUtils.d("11", cmd);
        netWorkWS.sendMsg(cmd);
    }

    @Override
    public void rePlay() {

        String cmd = Constant.PADMAC + "|continuePlay|mac=" +
                Constant.PADMAC +
                "&ipAddress=" + Constant.IP_TERMINAL +
                "&terminalCode=" + Constant.TERMINAL_CODE +
                "&timeStamp=" + new Date().getTime();
        LogUtils.d("11", cmd);
        netWorkWS.sendMsg(cmd);
    }

    public void stop(FilmDetailBean.DataBean.FilmDetailDataBean bean) {

        String cmd = Constant.PADMAC + "|stopPlay|mac=" +
                Constant.PADMAC +
                "&ipAddress=" + Constant.IP_TERMINAL +
                "&terminalCode=" + Constant.TERMINAL_CODE +
                "&timeStamp=" + new Date().getTime();

        LogUtils.d("11", cmd);
        netWorkWS.sendMsg(cmd);
    }
//  通过网络获得影片详情

}