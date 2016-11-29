package com.tuojin.tvfilm.contract;

import com.tuojin.tvfilm.base.BaseView;
import com.tuojin.tvfilm.bean.FilmBean;

import java.util.List;

/**
 * 文 件 名: SearchContract
 * 创 建 人: Administrator
 * 创建日期: 2016/9/21 15:55
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class SearchContract {
public interface View extends BaseView{
    void refreshUI(List<FilmBean> beanList);
}

public interface Presenter{
    void search(String key);

    void refreshUI(List<FilmBean> beanList);

    void hotSearch();
}

public interface Model{
    void search(String key);

    void hotSearch();
}


}