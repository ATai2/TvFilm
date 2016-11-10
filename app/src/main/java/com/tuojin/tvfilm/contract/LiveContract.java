package com.tuojin.tvfilm.contract;

import com.tuojin.tvfilm.base.BaseView;
import com.tuojin.tvfilm.bean.LiveBean;
import com.tuojin.tvfilm.bean.LiveContentBean;

import java.util.List;

/**
 * 文 件 名: LiveContract
 * 创 建 人: Administrator
 * 创建日期: 2016/10/13 11:29
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class LiveContract {
public interface View extends BaseView{
    void initMenu(List<LiveBean> mDatas);

    void initList(List<LiveContentBean> mDatas);
}

public interface Presenter{
    void menu();

    void initMenu(List<LiveBean> mDatas);

    void list(String text);

    void initList(List<LiveContentBean> mDatas);
}

public interface Model{
    void menu();

    void list(String text);
}


}