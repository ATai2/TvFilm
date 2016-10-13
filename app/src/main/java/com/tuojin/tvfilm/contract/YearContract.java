package com.tuojin.tvfilm.contract;

import com.tuojin.tvfilm.base.BaseView;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.YearBean;

import java.util.List;

/**
 * 文 件 名: YearContract
 * 创 建 人: Administrator
 * 创建日期: 2016/10/11 16:48
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class YearContract {
public interface View extends BaseView{
    void initMenu(List<YearBean> list);

    void initList(List<FilmBean> list);
}

public interface Presenter{
    void menu();

    void initMenu(List<YearBean> list);

    void list(int i);

    void initList(List<FilmBean> list);
}

public interface Model{
    void menu();

    void list(int i);
}


}