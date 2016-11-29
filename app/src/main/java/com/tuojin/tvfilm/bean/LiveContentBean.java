package com.tuojin.tvfilm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 文 件 名: LiveContentBean
 * 创 建 人: Administrator
 * 创建日期: 2016/10/19 17:19
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public  class LiveContentBean implements Parcelable {
    private String endtime;
    private int id;
    private int duration;
    private String lturl;
    private String livecontent;
    private String lcname;
    private String ltname;
    private String opentime;
    private String lcimgurl;


    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLturl() {
        return lturl;
    }

    public void setLturl(String lturl) {
        this.lturl = lturl;
    }

    public String getLivecontent() {
        return livecontent;
    }

    public void setLivecontent(String livecontent) {
        this.livecontent = livecontent;
    }

    public String getLcname() {
        return lcname;
    }

    public void setLcname(String lcname) {
        this.lcname = lcname;
    }

    public String getLtname() {
        return ltname;
    }

    public void setLtname(String ltname) {
        this.ltname = ltname;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getLcimgurl() {
        return lcimgurl;
    }

    public void setLcimgurl(String lcimgurl) {
        this.lcimgurl = lcimgurl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.endtime);
        dest.writeInt(this.id);
        dest.writeInt(this.duration);
        dest.writeString(this.lturl);
        dest.writeString(this.livecontent);
        dest.writeString(this.lcname);
        dest.writeString(this.ltname);
        dest.writeString(this.opentime);
        dest.writeString(this.lcimgurl);
    }

    public LiveContentBean() {
    }

    protected LiveContentBean(Parcel in) {
        this.endtime = in.readString();
        this.id = in.readInt();
        this.duration = in.readInt();
        this.lturl = in.readString();
        this.livecontent = in.readString();
        this.lcname = in.readString();
        this.ltname = in.readString();
        this.opentime = in.readString();
        this.lcimgurl = in.readString();
    }

    public static final Parcelable.Creator<LiveContentBean> CREATOR = new Parcelable.Creator<LiveContentBean>() {
        @Override
        public LiveContentBean createFromParcel(Parcel source) {
            return new LiveContentBean(source);
        }

        @Override
        public LiveContentBean[] newArray(int size) {
            return new LiveContentBean[size];
        }
    };
}
