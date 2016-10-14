package com.tuojin.tvfilm.bean;

import java.util.List;

/**
 * 文 件 名: LiveListBean
 * 创 建 人: Administrator
 * 创建日期: 2016/10/14 17:06
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class LiveListBean {
    /**
     * timeStamp :
     * status : 3
     * data : {"data":[{"lcid":1,"lcname":"NBA"},{"lcid":2,"lcname":"英超"},{"lcid":3,"lcname":"欧冠"},{"lcid":4,"lcname":"西甲"},{"lcid":5,"lcname":"中超"},{"lcid":6,"lcname":"法网"},{"lcid":7,"lcname":"CBA"}],"user":"PAD1470377342730"}
     * msgType : getLiveCategoryList
     * code : 3
     * user : PAD1470377342730
     */

    private String timeStamp;
    private int status;
    /**
     * data : [{"lcid":1,"lcname":"NBA"},{"lcid":2,"lcname":"英超"},{"lcid":3,"lcname":"欧冠"},{"lcid":4,"lcname":"西甲"},{"lcid":5,"lcname":"中超"},{"lcid":6,"lcname":"法网"},{"lcid":7,"lcname":"CBA"}]
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
         * lcid : 1
         * lcname : NBA
         */

        private List<LiveBean> data;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public List<LiveBean> getData() {
            return data;
        }

        public void setData(List<LiveBean> data) {
            this.data = data;
        }


    }
}
