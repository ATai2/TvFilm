package com.tuojin.tvfilm.bean;

/**
 * 文 件 名: ActorBean
 * 创 建 人: Administrator
 * 创建日期: 2016/10/13 11:09
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class ActorBean  {
    private int id;
    private String img;
    private String movie_actor;

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

    public String getMovie_actor() {
        return movie_actor;
    }

    public void setMovie_actor(String movie_actor) {
        this.movie_actor = movie_actor;
    }
}