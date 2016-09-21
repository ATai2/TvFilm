package com.tuojin.tvfilm.net;

/**
 * 文 件 名: WsResponse
 * 创 建 人: Administrator
 * 创建日期: 2016/9/20 16:27
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class WsResponse<T> {
    private String timeStamp;
    private int status;
    private T data;
    private String msgType;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

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
}
