package com.tuojin.tvfilm.bean;

/**
 * 文 件 名: PayAliBean
 * 创 建 人: Administrator
 * 创建日期: 2016/10/24 17:55
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class PayAliBean {
    /**
     * timeStamp : 1470624935908
     * status : 3
     * data : {"qrCode":"https://qr.alipay.com/bax07330ogaqtfsicdv12039","user":"PAD1470623000653"}
     * msgType : payAli
     * code : 3
     * user : PAD1470623000653
     */

    private String timeStamp;
    private int status;
    /**
     * qrCode : https://qr.alipay.com/bax07330ogaqtfsicdv12039
     * user : PAD1470623000653
     */

    private PayBean data;
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

    public PayBean getData() {
        return data;
    }

    public void setData(PayBean data) {
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

    public static class PayBean {
        private String qrCode;
        private String user;

        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
    }
}
