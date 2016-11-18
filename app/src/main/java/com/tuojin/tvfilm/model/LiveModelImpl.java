package com.tuojin.tvfilm.model;

import com.tuojin.tvfilm.bean.LiveContentBean;
import com.tuojin.tvfilm.contract.LiveContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.LivePresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by MVPHelper on 2016/10/13
 */

public class LiveModelImpl implements LiveContract.Model {
    LivePresenterImpl mPresenter;
    TvFilmNetWorkWS mNetWorkWS = new TvFilmNetWorkWS();
    private final String mIp;

    public LiveModelImpl(LivePresenterImpl presenter) {
        mPresenter = presenter;
        mIp = Constant.IP_TERMINAL;
//                new SPUtils(mPresenter.getContext(), "terminal").getString("ip");
    }

    @Override
    public void menu() {
        mNetWorkWS.sendMsg(Constant.PADMAC +
                "|getLiveCategoryList|startIndex=0&endIndex=27"
        );
    }

    @Override
    public void list(String text) {
        String cmd = null;
        try {
            cmd = Constant.PADMAC +
                    "|getLiveContentList|terminalCode=" +
                    Constant.TERMINAL_CODE +
                    "&lcname=" +
                    URLEncoder.encode(text, "utf-8") +
                    "&startIndex=0&endIndex=20";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mNetWorkWS.sendMsg(cmd);
    }


    public void playLive(LiveContentBean bean) {
        String cmd = Constant.PADMAC + "|startLive|mac=" +
                Constant.PADMAC +
                "&ipAddress=" + mIp +
                "&terminalCode=" + Constant.TERMINAL_CODE +
                "&liveid="+bean.getId()+
                "&timeStamp=" + new Date().getTime();
        mNetWorkWS.sendMsg(cmd);
//        PAD1470377342730|startLive|mac=PAD1470377342730&ipAddress=192.168.1.175&
// terminalCode=SMET15128361&liveid=21&timeStamp=1470378484858
    }

    public void stopLive() {
        String cmd = Constant.PADMAC + "|stopLive|mac=" +
                Constant.PADMAC +
                "&ipAddress=" + mIp +
                "&terminalCode=" + Constant.TERMINAL_CODE +
                "&timeStamp=" + new Date().getTime();
        mNetWorkWS.sendMsg(cmd);
    }
}