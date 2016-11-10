package com.tuojin.tvfilm.bean;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * 文 件 名: LiteFilmBean
 * 创 建 人: Administrator
 * 创建日期: 2016/10/26 14:17
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
@Table("filmhistory")
public class LiteFilmBean {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    @Column("_id")
    private int id;
    @NotNull
    private String filmBean;

    public String getFilmBean() {
        return filmBean;
    }

    public void setFilmBean(String filmBean) {
        this.filmBean = filmBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
