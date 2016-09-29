package com.tuojin.tvfilm.bean;

import java.util.List;

/**
 * 文 件 名: FilmSearchKeyWordBean
 * 创 建 人: Administrator
 * 创建日期: 2016/9/27 18:32
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class FilmSearchKeyWordBean {
    /**
     * timeStamp :
     * status : 3
     * data : {"data":[{"score":"3.7","expiredate":"","publishdate":"2009","type":"动作 / 科幻 / 惊悚 / 奇幻 / 冒险","createtime":null,"filmlength":"85","movie_clickcount":0,"video":"","movie_name":"七龙珠-国语版(中文版)","audio":"","filmhlength":"","status":0,"kdm_addr":"","director":"黄毅瑜","moviecountry":"美国 / 香港 / 英国","file_desc":"","max_playtimes":0,"modifytime":null,"movieCountry":"美国 / 香港 / 英国","duration":"","movie_ename":"","cast":"贾斯汀\u2022查特文 / 詹姆斯\u2022马斯特斯 / 杰米\u2022钟 / 周润发 / 埃米\u2022罗森 / 朴俊亨 / 田村英里子","poster":"/BESTV151105155645001713/POSTER/BESTV151105155645001713.jpg","filename":"七龙珠-国语版","mid":"BESTV151105155645001713","uuid":"cbc9adba-bc6d-47d1-b19f-b1b8566ce953","brief":""}],"user":"PAD1467167092363"}
     * msgType :  getFilmList
     * code : 3
     * user : PAD1467167092363
     */

    private String timeStamp;
    private int status;
    /**
     * data : [{"score":"3.7","expiredate":"","publishdate":"2009","type":"动作 / 科幻 / 惊悚 / 奇幻 / 冒险","createtime":null,"filmlength":"85","movie_clickcount":0,"video":"","movie_name":"七龙珠-国语版(中文版)","audio":"","filmhlength":"","status":0,"kdm_addr":"","director":"黄毅瑜","moviecountry":"美国 / 香港 / 英国","file_desc":"","max_playtimes":0,"modifytime":null,"movieCountry":"美国 / 香港 / 英国","duration":"","movie_ename":"","cast":"贾斯汀\u2022查特文 / 詹姆斯\u2022马斯特斯 / 杰米\u2022钟 / 周润发 / 埃米\u2022罗森 / 朴俊亨 / 田村英里子","poster":"/BESTV151105155645001713/POSTER/BESTV151105155645001713.jpg","filename":"七龙珠-国语版","mid":"BESTV151105155645001713","uuid":"cbc9adba-bc6d-47d1-b19f-b1b8566ce953","brief":""}]
     * user : PAD1467167092363
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
         * score : 3.7
         * expiredate :
         * publishdate : 2009
         * type : 动作 / 科幻 / 惊悚 / 奇幻 / 冒险
         * createtime : null
         * filmlength : 85
         * movie_clickcount : 0
         * video :
         * movie_name : 七龙珠-国语版(中文版)
         * audio :
         * filmhlength :
         * status : 0
         * kdm_addr :
         * director : 黄毅瑜
         * moviecountry : 美国 / 香港 / 英国
         * file_desc :
         * max_playtimes : 0
         * modifytime : null
         * movieCountry : 美国 / 香港 / 英国
         * duration :
         * movie_ename :
         * cast : 贾斯汀•查特文 / 詹姆斯•马斯特斯 / 杰米•钟 / 周润发 / 埃米•罗森 / 朴俊亨 / 田村英里子
         * poster : /BESTV151105155645001713/POSTER/BESTV151105155645001713.jpg
         * filename : 七龙珠-国语版
         * mid : BESTV151105155645001713
         * uuid : cbc9adba-bc6d-47d1-b19f-b1b8566ce953
         * brief :
         */

        private List<FilmBean> data;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public List<FilmBean> getData() {
            return data;
        }

        public void setData(List<FilmBean> data) {
            this.data = data;
        }
    }
}
