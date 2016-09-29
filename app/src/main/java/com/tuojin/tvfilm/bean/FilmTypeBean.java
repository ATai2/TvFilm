package com.tuojin.tvfilm.bean;

/**
 * 文 件 名: FilmTypeBean
 * 创 建 人: Administrator
 * 创建日期: 2016/9/28 17:58
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public  class FilmTypeBean {
    private int id;
    private String movieType;
    private String img;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}