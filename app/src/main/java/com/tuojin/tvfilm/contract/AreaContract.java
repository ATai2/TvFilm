package com.tuojin.tvfilm.contract;

import com.tuojin.tvfilm.base.BaseView;

/**
 * 文 件 名: AreaContract
 * 创 建 人: Administrator
 * 创建日期: 2016/10/9 13:50
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class AreaContract {
public interface View extends BaseView{
}

public interface Presenter{
    void list(String a);
    void listByArea(int id, String movie_country);
}

public interface Model{
    void list(String a);

    void listByArea(int id, String movie_country);
}


}