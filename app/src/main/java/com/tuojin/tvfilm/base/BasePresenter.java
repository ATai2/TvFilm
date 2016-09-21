package com.tuojin.tvfilm.base;

/**
 * 文 件 名: BasePresenter
 * 创 建 人: Administrator
 * 创建日期: 2016/9/18 14:57
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class BasePresenter<T> {
    public T mView;
    public void attach(T mView) {
        this.mView = mView;
    }

    public void dettach() {
        mView = null;
    }

    public void showErrorMessage(String msg){

    }
}
