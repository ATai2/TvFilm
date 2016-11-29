package com.tuojin.tvfilm.presenter;
import com.tuojin.tvfilm.base.BasePresenter;
import com.tuojin.tvfilm.bean.LiveBean;
import com.tuojin.tvfilm.bean.LiveContentBean;
import com.tuojin.tvfilm.contract.LiveContract;
import com.tuojin.tvfilm.model.LiveModelImpl;

import java.util.List;

/**
* Created by MVPHelper on 2016/10/13
*/

public class LivePresenterImpl extends BasePresenter<LiveContract.View> implements LiveContract.Presenter{
    LiveModelImpl mModel=new LiveModelImpl(this);
    @Override
    public void menu() {
        mModel.menu();
    }

    @Override
    public void initMenu(List<LiveBean> mDatas) {
        mView.initMenu(mDatas);
    }

    @Override
    public void list(String text) {
        mModel.list(text);
    }

    public void playLive(LiveContentBean bean) {
        mModel.playLive(bean);
    }

    public void stopLive() {
        mModel.stopLive();
    }
}