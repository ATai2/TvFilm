package com.tuojin.tvfilm.contract;

import com.tuojin.tvfilm.base.BaseView;

/**
 * 文 件 名: FrameFilmCateListContract
 * 创 建 人: Administrator
 * 创建日期: 2016/9/27 12:00
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class FrameFilmCateListContract {
public interface View extends BaseView{
}

public interface Presenter{
    void findFilmList();
}

public interface Model{
}


}