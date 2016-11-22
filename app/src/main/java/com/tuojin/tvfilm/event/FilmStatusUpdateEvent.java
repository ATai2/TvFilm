package com.tuojin.tvfilm.event;

/**
 * 文 件 名: FilmPlayEvent
 * 创 建 人: Administrator
 * 创建日期: 2016/10/26 15:23
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class FilmStatusUpdateEvent {
    public String msg;

    public FilmStatusUpdateEvent(String msg) {
        this.msg = msg;
    }
}
