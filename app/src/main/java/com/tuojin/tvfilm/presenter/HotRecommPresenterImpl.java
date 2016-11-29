package com.tuojin.tvfilm.presenter;

import com.tuojin.tvfilm.base.BasePresenter;
import com.tuojin.tvfilm.bean.TerminalBean;
import com.tuojin.tvfilm.contract.HotRecommContract;
import com.tuojin.tvfilm.model.HotRecommModelImpl;

/**
* Created by MVPHelper on 2016/09/20
*/

public class HotRecommPresenterImpl extends BasePresenter<HotRecommContract.View> implements HotRecommContract.Presenter {
    HotRecommModelImpl model=new HotRecommModelImpl(this);
    @Override
    public void onResume() {
        //之后传入页数参数用于，数据加载
        model.getRecommList(0);
    }

    public void terminal() {
        model.getTerminal();
    }

    public void bind(TerminalBean terminalBean) {
        model.bind(terminalBean);
    }

    public void call(String msg) {
        model.call(msg);
    }
}