package com.tuojin.tvfilm.base;

/**
 * 文 件 名: BaseView
 * 创 建 人: Administrator
 * 创建日期: 2016/9/20 18:07
 * 文件描述： 显示正在加载，关闭，以及错误
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public interface BaseView {
    void showLoading();
    void hideLoading();
    void showMessage(String msg);
}
