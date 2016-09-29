package com.tuojin.tvfilm.bean;

import java.util.List;

/**
 * 文 件 名: DirectListBean
 * 创 建 人: Administrator
 * 创建日期: 2016/9/26 14:17
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class DirectListBean {

    private String timeStamp;
    private int status;
    private DataBean data;
    private String msgType;
    private int code;
    private String user;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public static class DataBean {
        private String user;

        private List<DirectorBean> data;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public List<DirectorBean> getData() {
            return data;
        }

        public void setData(List<DirectorBean> data) {
            this.data = data;
        }

        public static class DirectorBean {
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
    }
}
