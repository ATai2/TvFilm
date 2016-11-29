package com.tuojin.tvfilm.contract;

import com.tuojin.tvfilm.base.BaseView;
import com.tuojin.tvfilm.bean.FilmBean;

import java.util.List;

/**
 * 文 件 名: SortListContract
 * 创 建 人: Administrator
 * 创建日期: 2016/9/21 15:54
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class SortListContract {
public interface View extends BaseView {
    //RecyclerView初始化
}

public interface Presenter{
    void onResume(int catcode,int page);

    void refreshUI(List<FilmBean> beanList);
}

public interface Model{
    void getHotFilmList(int page);
    void getRecentFilmList(int page);
    void  getDouBanFilmList(int page);
}


}