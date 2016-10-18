package com.tuojin.tvfilm.contract;

import com.tuojin.tvfilm.base.BaseView;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.FilmTypeBean;

import java.util.List;

/**
 * 文 件 名: FilmTypeContract
 * 创 建 人: Administrator
 * 创建日期: 2016/9/29 14:01
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class FilmTypeContract {
public interface View extends BaseView{
    void initFilmFragment(List<FilmBean> data);

    void initMenu(List<FilmTypeBean> list);
}

public interface Presenter{
    void onClick(int type);

    void onResume(int sortType);

    void menu();

    void initMenu(List<FilmTypeBean> list);
}

public interface Model{
    void onClick(int type);

    void menu();
}


}