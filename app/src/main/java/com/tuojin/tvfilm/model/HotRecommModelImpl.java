package com.tuojin.tvfilm.model;

import com.tuojin.tvfilm.bean.TerminalBean;
import com.tuojin.tvfilm.contract.HotRecommContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.HotRecommPresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

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
}