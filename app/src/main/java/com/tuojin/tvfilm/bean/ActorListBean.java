package com.tuojin.tvfilm.bean;

import java.util.List;

/**
 * 文 件 名: ActorListBean
 * 创 建 人: Administrator
 * 创建日期: 2016/10/13 11:08
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class ActorListBean {
    /**
     * timeStamp :
     * status : 3
     * data : {"data":[{"id":1,"img":"\\pad\\actor\\1.png","movie_actor":"成龙"},{"id":2,"img":"\\pad\\actor\\2.png","movie_actor":"葛优"},{"id":3,"img":"\\pad\\actor\\3.png","movie_actor":"章子怡"},{"id":4,"img":"\\pad\\actor\\4.png","movie_actor":"尼古拉斯\u2022凯奇"},{"id":5,"img":"\\pad\\actor\\5.png","movie_actor":"汤姆\u2022克鲁斯"},{"id":6,"img":"\\pad\\actor\\6.png","movie_actor":"周星驰"},{"id":8,"img":"1.jpg","movie_actor":"123"},{"id":5749,"img":"1.jpg","movie_actor":"131"},{"id":2063,"img":"1.jpg","movie_actor":"50分"},{"id":2357,"img":"1.jpg","movie_actor":"A Martinez"},{"id":2538,"img":"1.jpg","movie_actor":"A.J.巴克利"},{"id":5682,"img":"1.jpg","movie_actor":"Aaron Jeffery"},{"id":4776,"img":"1.jpg","movie_actor":"Aaron Kissiov"},{"id":86,"img":"1.jpg","movie_actor":"Aaron Poole"},{"id":2143,"img":"1.jpg","movie_actor":"Adam Leese"},{"id":4971,"img":"1.jpg","movie_actor":"Adam MacDonald"},{"id":4773,"img":"1.jpg","movie_actor":"Adi Shankar"},{"id":3337,"img":"1.jpg","movie_actor":"Adrian Martinez"},{"id":5361,"img":"1.jpg","movie_actor":"Adrian Scarborough"},{"id":198,"img":"1.jpg","movie_actor":"Adriana Caselotti"},{"id":5139,"img":"1.jpg","movie_actor":"Aidan Shipley"},{"id":2541,"img":"1.jpg","movie_actor":"Aiden Flowers"},{"id":3667,"img":"1.jpg","movie_actor":"Ako"},{"id":5080,"img":"1.jpg","movie_actor":"Al Thompson"},{"id":4968,"img":"1.jpg","movie_actor":"Alain Moussi"},{"id":5083,"img":"1.jpg","movie_actor":"Alan Davis"}],"user":"PAD1467167092363"}
     * msgType : getActorList
     * code : 3
     * user : PAD1467167092363
     */

    private String timeStamp;
    private int status;
    /**
     * data : [{"id":1,"img":"\\pad\\actor\\1.png","movie_actor":"成龙"},{"id":2,"img":"\\pad\\actor\\2.png","movie_actor":"葛优"},{"id":3,"img":"\\pad\\actor\\3.png","movie_actor":"章子怡"},{"id":4,"img":"\\pad\\actor\\4.png","movie_actor":"尼古拉斯\u2022凯奇"},{"id":5,"img":"\\pad\\actor\\5.png","movie_actor":"汤姆\u2022克鲁斯"},{"id":6,"img":"\\pad\\actor\\6.png","movie_actor":"周星驰"},{"id":8,"img":"1.jpg","movie_actor":"123"},{"id":5749,"img":"1.jpg","movie_actor":"131"},{"id":2063,"img":"1.jpg","movie_actor":"50分"},{"id":2357,"img":"1.jpg","movie_actor":"A Martinez"},{"id":2538,"img":"1.jpg","movie_actor":"A.J.巴克利"},{"id":5682,"img":"1.jpg","movie_actor":"Aaron Jeffery"},{"id":4776,"img":"1.jpg","movie_actor":"Aaron Kissiov"},{"id":86,"img":"1.jpg","movie_actor":"Aaron Poole"},{"id":2143,"img":"1.jpg","movie_actor":"Adam Leese"},{"id":4971,"img":"1.jpg","movie_actor":"Adam MacDonald"},{"id":4773,"img":"1.jpg","movie_actor":"Adi Shankar"},{"id":3337,"img":"1.jpg","movie_actor":"Adrian Martinez"},{"id":5361,"img":"1.jpg","movie_actor":"Adrian Scarborough"},{"id":198,"img":"1.jpg","movie_actor":"Adriana Caselotti"},{"id":5139,"img":"1.jpg","movie_actor":"Aidan Shipley"},{"id":2541,"img":"1.jpg","movie_actor":"Aiden Flowers"},{"id":3667,"img":"1.jpg","movie_actor":"Ako"},{"id":5080,"img":"1.jpg","movie_actor":"Al Thompson"},{"id":4968,"img":"1.jpg","movie_actor":"Alain Moussi"},{"id":5083,"img":"1.jpg","movie_actor":"Alan Davis"}]
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
         * id : 1
         * img : \pad\actor\1.png
         * movie_actor : 成龙
         */

        private List<ActorBean> data;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public List<ActorBean> getData() {
            return data;
        }

        public void setData(List<ActorBean> data) {
            this.data = data;
        }


    }
}
