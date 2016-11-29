package com.tuojin.tvfilm.presenter;

import com.tuojin.tvfilm.base.BasePresenter;
import com.tuojin.tvfilm.bean.FilmDetailBean;
import com.tuojin.tvfilm.contract.FilmDetailContract;
import com.tuojin.tvfilm.model.FilmDetailModelImpl;
import com.tuojin.tvfilm.utils.LogUtils;

/**
 * Created by MVPHelper on 2016/09/22
 */

public class FilmDetailPresenterImpl extends BasePresenter<FilmDetailContract.View> implements FilmDetailContract.Presenter {

    private FilmDetailModelImpl mDetailModel;
    private String ip;

    public FilmDetailPresenterImpl(String ip) {
        this.ip = ip;
        mDetailModel = new FilmDetailModelImpl(this, ip);
    }

    @Override
    public void onResume(String mid, String uuid) {
        LogUtils.d("11", "onResume" + mid + uuid);
        mDetailModel.onResume(mid, uuid);
    }

    @Override
    public void play(FilmDetailBean.DataBean.FilmDetailDataBean film) {
        mDetailModel.play(film);
    }

    @Override
    public void initList() {
        mDetailModel.initList();
    }

    @Override
    public void stop(FilmDetailBean.DataBean.FilmDetailDataBean bean) {
        mDetailModel.stop(bean);
    }

    @Override
    public void getQrCode(FilmDetailBean.DataBean.FilmDetailDataBean bean,int i) {
        mDetailModel.getQrCode(bean,i);
    }

    @Override
    public void pause() {
        mDetailModel.pause();
    }

    @Override
    public void rePlay() {
        mDetailModel.rePlay();
    }

    public void checkFilm() {
        mDetailModel.checkFilm();
    }

    public void checkFilm(int p) {
        mDetailModel.checkFilm(p);
    }

    public void previewrePlay() {
        mDetailModel.previewReplay();
    }

    public void previewstop(FilmDetailBean.DataBean.FilmDetailDataBean bean) {
        mDetailModel.previewStop(bean);
    }

    public void continuPlay() {
        mDetailModel.continuePlay();
    }

    public void gotoPostion(int currentposition) {
        mDetailModel.gotoPlay(currentposition);
    }
}