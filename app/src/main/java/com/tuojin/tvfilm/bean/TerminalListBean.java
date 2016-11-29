package com.tuojin.tvfilm.bean;

import java.util.List;

/**
 * 文 件 名: TerminalListBean
 * 创 建 人: Administrator
 * 创建日期: 2016/11/9 13:49
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class TerminalListBean {
    /**
     * timeStamp :
     * status : 3
     * data : {"data":[{"terminal_name":"测试1","position":"","port":0,"loglevel":"","padmac":"","status":0,"hardcode":"","mac":"","type":0,"videodownloadmode":"","cinema_code":"","playerrortime":null,"ouput_port":"","active_status":0,"terminal_ip":"10.1.3.32","refreshtime":0,"volume":0,"playpausetime":null,"asp":"","terminal_code":"SMET15128361","uuid":"","playstarttime":null}],"user":"PAD1465889962927"}
     * msgType : getTerminal
     * code : 3
     * user : PAD1465889962927
     */

    private String timeStamp;
    private int status;
    /**
     * data : [{"terminal_name":"测试1","position":"","port":0,"loglevel":"","padmac":"","status":0,"hardcode":"","mac":"","type":0,"videodownloadmode":"","cinema_code":"","playerrortime":null,"ouput_port":"","active_status":0,"terminal_ip":"10.1.3.32","refreshtime":0,"volume":0,"playpausetime":null,"asp":"","terminal_code":"SMET15128361","uuid":"","playstarttime":null}]
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
         * terminal_name : 测试1
         * position :
         * port : 0
         * loglevel :
         * padmac :
         * status : 0
         * hardcode :
         * mac :
         * type : 0
         * videodownloadmode :
         * cinema_code :
         * playerrortime : null
         * ouput_port :
         * active_status : 0
         * terminal_ip : 10.1.3.32
         * refreshtime : 0
         * volume : 0
         * playpausetime : null
         * asp :
         * terminal_code : SMET15128361
         * uuid :
         * playstarttime : null
         */

        private List<TerminalBean> data;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public List<TerminalBean> getData() {
            return data;
        }

        public void setData(List<TerminalBean> data) {
            this.data = data;
        }


    }
}
