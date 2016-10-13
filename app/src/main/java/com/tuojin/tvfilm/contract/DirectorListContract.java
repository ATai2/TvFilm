package com.tuojin.tvfilm.contract;

import com.tuojin.tvfilm.base.BaseView;
import com.tuojin.tvfilm.bean.DirectorBean;

import java.util.List;

/**
 * 文 件 名: DirectorListContract
 * 创 建 人: Administrator
 * 创建日期: 2016/9/30 10:01
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class DirectorListContract {
    public interface View extends BaseView{
        void initList(List<DirectorBean> list);

        void initListByDirector(String data, String movie_director);
    }

    public interface Presenter{
        void list(String a);
        void initList(List<DirectorBean> list);

        void listByDirector(int id, String movie_director);

        void initListByDirector(String data, String movie_director);
    }

    public interface Model{
        void list(String a);

        void listByDirector(int id, String movie_director);
    }

}