package com.tuojin.tvfilm.bean;

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
public class CategoryInfo {
    private String title;
    private String pic;

    public CategoryInfo() {
    }

    public CategoryInfo(String title, String pic) {
        this.title = title;
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
