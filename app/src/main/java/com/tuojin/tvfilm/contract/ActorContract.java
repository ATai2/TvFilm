package com.tuojin.tvfilm.contract;

import com.tuojin.tvfilm.base.BaseView;
import com.tuojin.tvfilm.bean.ActorBean;

import java.util.List;

/**
 * 文 件 名: ActorContract
 * 创 建 人: Administrator
 * 创建日期: 2016/10/13 10:45
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class ActorContract {
    public interface View extends BaseView {
        void initList(List<ActorBean> list);

        void initListByActor(String data, String movie_actor);
    }

    public interface Presenter{
        void list(String a);

        void initList(List<ActorBean> list);

        void listByActor(int id, String movie_actor);

        void initListByActor(String data, String movie_actor);
    }

    public interface Model{
        void list(String a);

        void listByActor(int id, String movie_actor);
    }
}