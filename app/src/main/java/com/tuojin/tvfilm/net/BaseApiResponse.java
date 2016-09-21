package com.tuojin.tvfilm.net;

/**
 * 文 件 名: BaseApiResponse
 * 创 建 人: Administrator
 * 创建日期: 2016/9/19 09:28
 * 文件描述： 网络请求泛型封装类，对响应统一处理
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注："msgType":"bindTerminal ","code":23,"user":"PAD1465889962927"
 */
public class BaseApiResponse<T> {
//    时间戳
    private String timeStamp;
//    返回状态 3--成功；2错误
    private int status;
    private T data;
    private String msgType;
    private int code;
    private String user;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
