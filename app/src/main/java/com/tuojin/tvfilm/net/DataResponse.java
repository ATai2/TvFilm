package com.tuojin.tvfilm.net;

import java.util.List;

/**
 * 文 件 名: DataResponse
 * 创 建 人: Administrator
 * 创建日期: 2016/9/22 10:39
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class DataResponse <T>{

    /**
     * data : [{"id":1,"movie_country":"香港","img":"\\pad\\country\\1.png"},{"id":2,"movie_country":"台湾","img":"\\pad\\country\\2.png"},{"id":3,"movie_country":"中国大陆","img":"\\pad\\country\\3.png"},{"id":4,"movie_country":"韩国","img":"\\pad\\country\\5.png"},{"id":5,"movie_country":"日本","img":"\\pad\\country\\4.png"},{"id":6,"movie_country":"美国","img":"\\pad\\country\\6.png"},{"id":7,"movie_country":"英国","img":"1.jpg"},{"id":8,"movie_country":"加拿大","img":"1.jpg"},{"id":9,"movie_country":"法国","img":"1.jpg"},{"id":10,"movie_country":"澳大利亚","img":"1.jpg"},{"id":11,"movie_country":"新加坡","img":"1.jpg"},{"id":12,"movie_country":"南非","img":"1.jpg"},{"id":13,"movie_country":"德国","img":"1.jpg"},{"id":14,"movie_country":"秘鲁","img":"1.jpg"},{"id":15,"movie_country":"意大利","img":"1.jpg"},{"id":16,"movie_country":"","img":"1.jpg"},{"id":17,"movie_country":"俄罗斯","img":"1.jpg"},{"id":18,"movie_country":"爱尔兰","img":"1.jpg"},{"id":19,"movie_country":"黄政民","img":"1.jpg"},{"id":20,"movie_country":"尹宰文","img":"1.jpg"},{"id":21,"movie_country":"刘俊相","img":"1.jpg"},{"id":22,"movie_country":"李瑶媛","img":"1.jpg"},{"id":23,"movie_country":"郑雄仁","img":"1.jpg"},{"id":24,"movie_country":"新西兰","img":"1.jpg"},{"id":25,"movie_country":"泰国","img":"1.jpg"},{"id":26,"movie_country":"中国","img":"1.jpg"}]
     * user : PAD1465889962927
     */
    private List<T> data;
    private String user;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
