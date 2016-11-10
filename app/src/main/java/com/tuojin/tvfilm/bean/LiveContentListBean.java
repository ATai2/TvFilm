package com.tuojin.tvfilm.bean;

import java.util.List;

/**
 * 文 件 名: LiveContentListBean
 * 创 建 人: Administrator
 * 创建日期: 2016/10/19 17:18
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class LiveContentListBean {
    /**
     * timeStamp :
     * status : 3
     * data : {"data":[{"endtime":"2016-08-05 21:00:00","id":21,"duration":43200,"lturl":"http://live.bestmovie.com.cn:8088/channel05/2300.m3u8","livecontent":"马刺-热火","lcname":"NBA","ltname":"NBA","opentime":"2016-08-05 09:00:00","lcimgurl":"/lcimg/lc20160612175622.png"}],"user":"PAD1470377342730"}
     * msgType : getLiveContentList
     * code : 3
     * user : PAD1470377342730
     */

    private String timeStamp;
    private int status;
    /**
     * data : [{"endtime":"2016-08-05 21:00:00","id":21,"duration":43200,"lturl":"http://live.bestmovie.com.cn:8088/channel05/2300.m3u8","livecontent":"马刺-热火","lcname":"NBA","ltname":"NBA","opentime":"2016-08-05 09:00:00","lcimgurl":"/lcimg/lc20160612175622.png"}]
     * user : PAD1470377342730
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
         * endtime : 2016-08-05 21:00:00
         * id : 21
         * duration : 43200
         * lturl : http://live.bestmovie.com.cn:8088/channel05/2300.m3u8
         * livecontent : 马刺-热火
         * lcname : NBA
         * ltname : NBA
         * opentime : 2016-08-05 09:00:00
         * lcimgurl : /lcimg/lc20160612175622.png
         */

        private List<LiveContentBean> data;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public List<LiveContentBean> getData() {
            return data;
        }

        public void setData(List<LiveContentBean> data) {
            this.data = data;
        }

    }
}
