package com.tuojin.tvfilm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 文 件 名: FilmBean
 * 创 建 人: Administrator
 * 创建日期: 2016/9/22 11:58
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public  class FilmBean implements Parcelable {
    private String score;
    private String expiredate;
    private String publishdate;
    private String type;
    private String createtime;
    private String filmlength;
    private int movie_clickcount;
    private String video;
    private String movie_name;
    private String audio;
    private String filmhlength;
    private int status;
    private String kdm_addr;
    private String director;
    private String moviecountry;
    private String file_desc;
    private int max_playtimes;
    private String modifytime;
    private String movieCountry;
    private String duration;
    private String movie_ename;
    private String cast;
    private String poster;
    private String filename;
    private String mid;
    private String uuid;
    private String brief;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getFilmlength() {
        return filmlength;
    }

    public void setFilmlength(String filmlength) {
        this.filmlength = filmlength;
    }

    public int getMovie_clickcount() {
        return movie_clickcount;
    }

    public void setMovie_clickcount(int movie_clickcount) {
        this.movie_clickcount = movie_clickcount;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getFilmhlength() {
        return filmhlength;
    }

    public void setFilmhlength(String filmhlength) {
        this.filmhlength = filmhlength;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getKdm_addr() {
        return kdm_addr;
    }

    public void setKdm_addr(String kdm_addr) {
        this.kdm_addr = kdm_addr;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getMoviecountry() {
        return moviecountry;
    }

    public void setMoviecountry(String moviecountry) {
        this.moviecountry = moviecountry;
    }

    public String getFile_desc() {
        return file_desc;
    }

    public void setFile_desc(String file_desc) {
        this.file_desc = file_desc;
    }

    public int getMax_playtimes() {
        return max_playtimes;
    }

    public void setMax_playtimes(int max_playtimes) {
        this.max_playtimes = max_playtimes;
    }

    public Object getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public String getMovieCountry() {
        return movieCountry;
    }

    public void setMovieCountry(String movieCountry) {
        this.movieCountry = movieCountry;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMovie_ename() {
        return movie_ename;
    }

    public void setMovie_ename(String movie_ename) {
        this.movie_ename = movie_ename;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.score);
        dest.writeString(this.expiredate);
        dest.writeString(this.publishdate);
        dest.writeString(this.type);
        dest.writeString(this.createtime);
        dest.writeString(this.filmlength);
        dest.writeInt(this.movie_clickcount);
        dest.writeString(this.video);
        dest.writeString(this.movie_name);
        dest.writeString(this.audio);
        dest.writeString(this.filmhlength);
        dest.writeInt(this.status);
        dest.writeString(this.kdm_addr);
        dest.writeString(this.director);
        dest.writeString(this.moviecountry);
        dest.writeString(this.file_desc);
        dest.writeInt(this.max_playtimes);
        dest.writeString(this.modifytime);
        dest.writeString(this.movieCountry);
        dest.writeString(this.duration);
        dest.writeString(this.movie_ename);
        dest.writeString(this.cast);
        dest.writeString(this.poster);
        dest.writeString(this.filename);
        dest.writeString(this.mid);
        dest.writeString(this.uuid);
        dest.writeString(this.brief);
    }

    public FilmBean() {
    }

    protected FilmBean(Parcel in) {
        this.score = in.readString();
        this.expiredate = in.readString();
        this.publishdate = in.readString();
        this.type = in.readString();
        this.createtime = in.readString();
        this.filmlength = in.readString();
        this.movie_clickcount = in.readInt();
        this.video = in.readString();
        this.movie_name = in.readString();
        this.audio = in.readString();
        this.filmhlength = in.readString();
        this.status = in.readInt();
        this.kdm_addr = in.readString();
        this.director = in.readString();
        this.moviecountry = in.readString();
        this.file_desc = in.readString();
        this.max_playtimes = in.readInt();
        this.modifytime = in.readString();
        this.movieCountry = in.readString();
        this.duration = in.readString();
        this.movie_ename = in.readString();
        this.cast = in.readString();
        this.poster = in.readString();
        this.filename = in.readString();
        this.mid = in.readString();
        this.uuid = in.readString();
        this.brief = in.readString();
    }

    public static final Creator CREATOR = new Creator<FilmBean>() {
        @Override
        public FilmBean createFromParcel(Parcel source) {
            return new FilmBean(source);
        }

        @Override
        public FilmBean[] newArray(int size) {
            return new FilmBean[size];
        }
    };
}