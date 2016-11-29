package com.tuojin.tvfilm.model;

import com.tuojin.tvfilm.bean.TerminalBean;
import com.tuojin.tvfilm.contract.HotRecommContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.HotRecommPresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by MVPHelper on 2016/09/20
 */

public class HotRecommModelImpl implements HotRecommContract.Model {

    HotRecommPresenterImpl mRecommPresenter;
    TvFilmNetWorkWS netWorkWS =new TvFilmNetWorkWS();
    public HotRecommModelImpl(HotRecommPresenterImpl recommPresenter) {
        mRecommPresenter = recommPresenter;
    }

    @Override
    public void getRecommList(int page) {
        //网络请求

        String cmd = Constant.PADMAC +
                "|getFilmListOrderByHotest|orderByFeild=hotest&orderByType=desc&terminalCode=" +
                Constant.TERMINAL_CODE +
                "&startIndex=0&endIndex=10";
        netWorkWS.sendMsg(cmd,101);
    }

    public void getTerminal() {
        String cmd = Constant.PADMAC +
                "|getTerminal|mac="+Constant.PADMAC;
        netWorkWS.sendMsg(cmd);
    }

    public void bind(TerminalBean terminalBean) {
        String cmd = Constant.PADMAC +
                "|bindTerminal|mac="+Constant.PADMAC+"&terminalCode=" +
                terminalBean.getTerminal_code() +
                "&ver=1.1.34";
        netWorkWS.sendMsg(cmd);
    }

    public void call(String msg) {
//        PAD1465889962927|callService|mac=PAD1465889962927&terminalCode=SMET15128361&msg=%E5%91%BC%E5%8F%AB%E6%80%BB%E5%8F%B0%2C%E5%90%AC%E5%88%B0%E8%AF%B7%E5%9B%9E%E7%AD%94...
        String cmd = null;
        try {
            cmd = Constant.PADMAC +
                    "|callService|mac="+Constant.PADMAC+"&terminalCode=" +
                    Constant.TERMINAL_CODE+
                    "&msg="+ URLEncoder.encode(msg,"utf-8");
            netWorkWS.sendMsg(cmd);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}