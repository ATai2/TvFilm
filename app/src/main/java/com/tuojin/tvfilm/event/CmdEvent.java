package com.tuojin.tvfilm.event;

/**
 * 文 件 名: CmdEvent
 * 创 建 人: Administrator
 * 创建日期: 2016/10/28 14:47
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class CmdEvent {
   public String msg;
    public int mInt=0;

    public CmdEvent(String msg,int i) {
        this.msg = msg;
        mInt=i;
    }
}
