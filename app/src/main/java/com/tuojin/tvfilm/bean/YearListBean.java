package com.tuojin.tvfilm.bean;

import java.util.List;

/**
 * 文 件 名: YearListBean
 * 创 建 人: Administrator
 * 创建日期: 2016/10/11 17:00
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class YearListBean {
    /**
     * timeStamp :
     * status : 3
     * data : {"data":[{"id":47,"movie_year":"2016"},{"id":1,"movie_year":"2015"},{"id":2,"movie_year":"2014"},{"id":3,"movie_year":"2013"},{"id":4,"movie_year":"2012"},{"id":5,"movie_year":"2011"},{"id":6,"movie_year":"2010"},{"id":7,"movie_year":"2009"},{"id":8,"movie_year":"2008"},{"id":9,"movie_year":"2007"},{"id":10,"movie_year":"2006"},{"id":11,"movie_year":"2005"},{"id":18,"movie_year":"2004"},{"id":19,"movie_year":"2003"},{"id":15,"movie_year":"2002"},{"id":21,"movie_year":"2001"},{"id":28,"movie_year":"2000"},{"id":13,"movie_year":"1999"},{"id":25,"movie_year":"1998"},{"id":20,"movie_year":"1997"},{"id":41,"movie_year":"1996"},{"id":23,"movie_year":"1995"},{"id":17,"movie_year":"1994"},{"id":16,"movie_year":"1993"},{"id":22,"movie_year":"1992"},{"id":27,"movie_year":"1991"},{"id":26,"movie_year":"1990"}],"user":"PAD1465889962927"}
     * msgType : getYearList
     * code : 3
     * user : PAD1465889962927
     */

    private String timeStamp;
    private int status;
    /**
     * data : [{"id":47,"movie_year":"2016"},{"id":1,"movie_year":"2015"},{"id":2,"movie_year":"2014"},{"id":3,"movie_year":"2013"},{"id":4,"movie_year":"2012"},{"id":5,"movie_year":"2011"},{"id":6,"movie_year":"2010"},{"id":7,"movie_year":"2009"},{"id":8,"movie_year":"2008"},{"id":9,"movie_year":"2007"},{"id":10,"movie_year":"2006"},{"id":11,"movie_year":"2005"},{"id":18,"movie_year":"2004"},{"id":19,"movie_year":"2003"},{"id":15,"movie_year":"2002"},{"id":21,"movie_year":"2001"},{"id":28,"movie_year":"2000"},{"id":13,"movie_year":"1999"},{"id":25,"movie_year":"1998"},{"id":20,"movie_year":"1997"},{"id":41,"movie_year":"1996"},{"id":23,"movie_year":"1995"},{"id":17,"movie_year":"1994"},{"id":16,"movie_year":"1993"},{"id":22,"movie_year":"1992"},{"id":27,"movie_year":"1991"},{"id":26,"movie_year":"1990"}]
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
         * id : 47
         * movie_year : 2016
         */

        private List<YearBean> data;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public List<YearBean> getData() {
            return data;
        }

        public void setData(List<YearBean> data) {
            this.data = data;
        }


    }
}
