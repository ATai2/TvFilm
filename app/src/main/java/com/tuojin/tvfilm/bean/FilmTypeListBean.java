package com.tuojin.tvfilm.bean;

import java.util.List;

/**
 * 文 件 名: FilmTypeListBean
 * 创 建 人: Administrator
 * 创建日期: 2016/9/26 17:24
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class FilmTypeListBean {
    /**
     * timeStamp :
     * status : 3
     * data : {"data":[{"id":1,"movieType":"爱情","img":"\\pad\\type\\1.png"},{"id":2,"movieType":"喜剧","img":"\\pad\\type\\2.png"},{"id":3,"movieType":"科幻","img":"\\pad\\type\\3.png"},{"id":4,"movieType":"动画","img":"\\pad\\type\\4.png"},{"id":5,"movieType":"动作","img":"\\pad\\type\\5.png"},{"id":6,"movieType":"古装","img":"\\pad\\type\\6.png"},{"id":7,"movieType":"奇幻","img":"1.jpg"},{"id":8,"movieType":"冒险","img":"1.jpg"},{"id":9,"movieType":"犯罪","img":"1.jpg"},{"id":10,"movieType":"剧情","img":"1.jpg"},{"id":11,"movieType":"战争","img":"1.jpg"},{"id":12,"movieType":"惊悚","img":"1.jpg"},{"id":13,"movieType":"悬疑","img":"1.jpg"},{"id":14,"movieType":"历史","img":"1.jpg"},{"id":15,"movieType":"传记","img":"1.jpg"},{"id":16,"movieType":"灾难","img":"1.jpg"},{"id":17,"movieType":"歌舞","img":"1.jpg"},{"id":18,"movieType":"家庭","img":"1.jpg"},{"id":19,"movieType":"恐怖","img":"1.jpg"},{"id":20,"movieType":"武侠","img":"1.jpg"},{"id":21,"movieType":"儿童","img":"1.jpg"},{"id":22,"movieType":"音乐","img":"1.jpg"},{"id":23,"movieType":"同性","img":"1.jpg"},{"id":24,"movieType":"西部","img":"1.jpg"},{"id":25,"movieType":"","img":"1.jpg"},{"id":26,"movieType":"运动","img":"1.jpg"}],"user":"PAD1465889962927"}
     * msgType : getFilmTypeList
     * code : 3
     * user : PAD1465889962927
     */

    private String timeStamp;
    private int status;
    /**
     * data : [{"id":1,"movieType":"爱情","img":"\\pad\\type\\1.png"},{"id":2,"movieType":"喜剧","img":"\\pad\\type\\2.png"},{"id":3,"movieType":"科幻","img":"\\pad\\type\\3.png"},{"id":4,"movieType":"动画","img":"\\pad\\type\\4.png"},{"id":5,"movieType":"动作","img":"\\pad\\type\\5.png"},{"id":6,"movieType":"古装","img":"\\pad\\type\\6.png"},{"id":7,"movieType":"奇幻","img":"1.jpg"},{"id":8,"movieType":"冒险","img":"1.jpg"},{"id":9,"movieType":"犯罪","img":"1.jpg"},{"id":10,"movieType":"剧情","img":"1.jpg"},{"id":11,"movieType":"战争","img":"1.jpg"},{"id":12,"movieType":"惊悚","img":"1.jpg"},{"id":13,"movieType":"悬疑","img":"1.jpg"},{"id":14,"movieType":"历史","img":"1.jpg"},{"id":15,"movieType":"传记","img":"1.jpg"},{"id":16,"movieType":"灾难","img":"1.jpg"},{"id":17,"movieType":"歌舞","img":"1.jpg"},{"id":18,"movieType":"家庭","img":"1.jpg"},{"id":19,"movieType":"恐怖","img":"1.jpg"},{"id":20,"movieType":"武侠","img":"1.jpg"},{"id":21,"movieType":"儿童","img":"1.jpg"},{"id":22,"movieType":"音乐","img":"1.jpg"},{"id":23,"movieType":"同性","img":"1.jpg"},{"id":24,"movieType":"西部","img":"1.jpg"},{"id":25,"movieType":"","img":"1.jpg"},{"id":26,"movieType":"运动","img":"1.jpg"}]
     * user : PAD1465889962927
     */

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
        /**
         * id : 1
         * movieType : 爱情
         * img : \pad\type\1.png
         */

        private List<FilmTypeBean> data;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public List<FilmTypeBean> getData() {
            return data;
        }

        public void setData(List<FilmTypeBean> data) {
            this.data = data;
        }


    }
}
