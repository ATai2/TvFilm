package com.tuojin.tvfilm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 文 件 名: DirectorBean
 * 创 建 人: Administrator
 * 创建日期: 2016/9/30 18:16
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class DirectorBean {
    private int id;
    private String img;
    private String movie_director;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMovie_director() {
        return movie_director;
    }

    public void setMovie_director(String movie_director) {
        this.movie_director = movie_director;
    }
}
