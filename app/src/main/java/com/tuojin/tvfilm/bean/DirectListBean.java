package com.tuojin.tvfilm.bean;

import java.util.List;

/**
 * 文 件 名: DirectListBean
 * 创 建 人: Administrator
 * 创建日期: 2016/9/26 14:17
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class DirectListBean {
    /**
     * timeStamp :
     * status : 3
     * data : {"data":[{"id":1,"img":"\\pad\\director\\1.png","movie_director":"张艺谋"},{"id":2,"img":"\\pad\\director\\2.png","movie_director":"冯小刚"},{"id":3,"img":"\\pad\\director\\3.png","movie_director":"陈凯歌"},{"id":5,"img":"\\pad\\director\\4.png","movie_director":"斯皮尔\u2022伯格"},{"id":4,"img":"\\pad\\director\\5.png","movie_director":"詹姆斯\u2022卡梅隆"},{"id":6,"img":"\\pad\\director\\6.png","movie_director":"李安"},{"id":7,"img":"1.jpg","movie_director":"杜琪峰"},{"id":8,"img":"1.jpg","movie_director":"王晶"},{"id":9,"img":"1.jpg","movie_director":"123"},{"id":10,"img":"1.jpg","movie_director":"陈庆嘉,秦小珍"},{"id":11,"img":"1.jpg","movie_director":"王子鸣"},{"id":12,"img":"1.jpg","movie_director":"曹义锡,金丙书,aaa"},{"id":13,"img":"1.jpg","movie_director":"王晶,钟少雄"},{"id":14,"img":"1.jpg","movie_director":"斯图尔特\u2022奥玛"},{"id":15,"img":"1.jpg","movie_director":"布雷特\u2022西蒙"},{"id":16,"img":"1.jpg","movie_director":"乔治\u2022诺非"},{"id":17,"img":"1.jpg","movie_director":"诺姆\u2022穆罗"},{"id":18,"img":"1.jpg","movie_director":"迈克尔\u2022贝"},{"id":19,"img":"1.jpg","movie_director":"钮承泽"},{"id":20,"img":"1.jpg","movie_director":"阿托姆\u2022伊戈扬"},{"id":21,"img":"1.jpg","movie_director":"成龙"},{"id":22,"img":"1.jpg","movie_director":"罗永昌"},{"id":23,"img":"1.jpg","movie_director":"李\u2022丹尼尔斯"},{"id":24,"img":"1.jpg","movie_director":"罗兰\u2022艾默里奇"},{"id":25,"img":"1.jpg","movie_director":"卡洛斯\u2022沙尔丹哈"},{"id":26,"img":"1.jpg","movie_director":"戴维\u2022汉德"}],"user":"PAD1465889962927"}
     * msgType : getDoctorList
     * code : 3
     * user : PAD1465889962927
     */

    private String timeStamp;
    private int status;
    /**
     * data : [{"id":1,"img":"\\pad\\director\\1.png","movie_director":"张艺谋"},{"id":2,"img":"\\pad\\director\\2.png","movie_director":"冯小刚"},{"id":3,"img":"\\pad\\director\\3.png","movie_director":"陈凯歌"},{"id":5,"img":"\\pad\\director\\4.png","movie_director":"斯皮尔\u2022伯格"},{"id":4,"img":"\\pad\\director\\5.png","movie_director":"詹姆斯\u2022卡梅隆"},{"id":6,"img":"\\pad\\director\\6.png","movie_director":"李安"},{"id":7,"img":"1.jpg","movie_director":"杜琪峰"},{"id":8,"img":"1.jpg","movie_director":"王晶"},{"id":9,"img":"1.jpg","movie_director":"123"},{"id":10,"img":"1.jpg","movie_director":"陈庆嘉,秦小珍"},{"id":11,"img":"1.jpg","movie_director":"王子鸣"},{"id":12,"img":"1.jpg","movie_director":"曹义锡,金丙书,aaa"},{"id":13,"img":"1.jpg","movie_director":"王晶,钟少雄"},{"id":14,"img":"1.jpg","movie_director":"斯图尔特\u2022奥玛"},{"id":15,"img":"1.jpg","movie_director":"布雷特\u2022西蒙"},{"id":16,"img":"1.jpg","movie_director":"乔治\u2022诺非"},{"id":17,"img":"1.jpg","movie_director":"诺姆\u2022穆罗"},{"id":18,"img":"1.jpg","movie_director":"迈克尔\u2022贝"},{"id":19,"img":"1.jpg","movie_director":"钮承泽"},{"id":20,"img":"1.jpg","movie_director":"阿托姆\u2022伊戈扬"},{"id":21,"img":"1.jpg","movie_director":"成龙"},{"id":22,"img":"1.jpg","movie_director":"罗永昌"},{"id":23,"img":"1.jpg","movie_director":"李\u2022丹尼尔斯"},{"id":24,"img":"1.jpg","movie_director":"罗兰\u2022艾默里奇"},{"id":25,"img":"1.jpg","movie_director":"卡洛斯\u2022沙尔丹哈"},{"id":26,"img":"1.jpg","movie_director":"戴维\u2022汉德"}]
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
         * id : 1
         * img : \pad\director\1.png
         * movie_director : 张艺谋
         */

        private List<DirectorBean> data;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public List<DirectorBean> getData() {
            return data;
        }

        public void setData(List<DirectorBean> data) {
            this.data = data;
        }

        public static class DirectorBean {
            private int id;
            private String img;
            private String movie_director;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getMovie_director() {
                return movie_director;
            }

            public void setMovie_director(String movie_director) {
                this.movie_director = movie_director;
            }
        }
    }
}
