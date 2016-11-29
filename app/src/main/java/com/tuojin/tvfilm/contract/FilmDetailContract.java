package com.tuojin.tvfilm.contract;

import com.tuojin.tvfilm.base.BaseView;
import com.tuojin.tvfilm.bean.FilmDetailBean;

/**
 * 文 件 名: FilmDetailContract
 * 创 建 人: Administrator
 * 创建日期: 2016/9/22 13:19
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class FilmDetailContract {
public interface View extends BaseView{
    //刷新界面数据
    void qrCode(String qrCode);
}

public interface Presenter{
    //更新数据
    void onResume(String mid,String uuid);

    void play(FilmDetailBean.DataBean.FilmDetailDataBean film);

    void initList();

    void stop(FilmDetailBean.DataBean.FilmDetailDataBean bean);

    void getQrCode(FilmDetailBean.DataBean.FilmDetailDataBean bean,int i);

    void pause();

    void rePlay();

}

public interface Model{
    void onResume(String mid,String uuid);

    void play(FilmDetailBean.DataBean.FilmDetailDataBean bean);

    void initList();

    void getQrCode(FilmDetailBean.DataBean.FilmDetailDataBean bean,int i);

    void pause();

    void rePlay();
}


}