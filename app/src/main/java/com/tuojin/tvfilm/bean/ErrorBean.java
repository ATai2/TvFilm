package com.tuojin.tvfilm.bean;

/**
 * 文 件 名: ErrorBean
 * 创 建 人: Administrator
 * 创建日期: 2016/11/10 16:42
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class ErrorBean {
    /**
     * timeStamp : 1478767265948
     * status : 2
     * data : {"user":"PAD1470022778326","msg":"云端支付连接异常"}
     * msgType : payAli
     * code : 2
     * user : PAD1470022778326
     */

    private String timeStamp;
    private int status;
    /**
     * user : PAD1470022778326
     * msg : 云端支付连接异常
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
        private String msg;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
