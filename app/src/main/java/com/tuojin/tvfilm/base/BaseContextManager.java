package com.tuojin.tvfilm.base;

import android.content.Context;

/**
 * 文 件 名: BaseContextManager
 * 创 建 人: Administrator
 * 创建日期: 2016/10/28 14:03
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class BaseContextManager {
    public Context mContext;
    private static BaseContextManager ourInstance = new BaseContextManager();

    public static BaseContextManager getInstance() {
        return ourInstance;
    }

    private BaseContextManager() {
    }
}
