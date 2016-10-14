package com.tuojin.tvfilm.contract;

import com.tuojin.tvfilm.base.BaseView;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.FilmDetailBean;

import java.util.List;

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
    void refresh(FilmDetailBean.DataBean.FilmDetailDataBean bean);

    void initListUI(List<FilmBean> mDatas);
}

public interface Presenter{
    //更新数据
    void onResume(String mid,String uuid);
    //更新页面数据
    void refresh(FilmDetailBean.DataBean.FilmDetailDataBean bean);

    void play(FilmDetailBean.DataBean.FilmDetailDataBean film);

    void initList();

    void initListUI(List<FilmBean> mDatas);

    void stop(FilmDetailBean.DataBean.FilmDetailDataBean bean);
}

public interface Model{
    void onResume(String mid,String uuid);

    void onResumeInitRecycleView();

    void play(FilmDetailBean.DataBean.FilmDetailDataBean bean);

    void initList();
}


}