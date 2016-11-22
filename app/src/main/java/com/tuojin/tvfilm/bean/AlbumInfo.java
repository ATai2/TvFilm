package com.tuojin.tvfilm.bean;

import android.graphics.drawable.Drawable;

/**
 * 文 件 名: CategoryInfo
 * 创 建 人: Administrator
 * 创建日期: 2016/9/21 14:25
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class AlbumInfo {
    private String title;
    private Drawable pic;

    public AlbumInfo() {
    }

    public AlbumInfo(String title, Drawable pic) {
        this.title = title;
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getPic() {
        return pic;
    }

    public void setPic(Drawable pic) {
        this.pic = pic;
    }
}
