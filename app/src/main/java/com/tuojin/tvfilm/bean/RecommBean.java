package com.tuojin.tvfilm.bean;

import java.util.List;

/**
 * 文 件 名: RecommBean
 * 创 建 人: Administrator
 * 创建日期: 2016/9/22 11:54
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class RecommBean {
    /**
     * timeStamp :
     * status : 3
     * data : {"data":[{"score":"7.9","expiredate":"","publishdate":"","type":"","createtime":null,"filmlength":"","movie_clickcount":0,"video":"","movie_name":"荒野猎人-原声版","audio":"","filmhlength":"","status":0,"kdm_addr":"","director":"","moviecountry":"","file_desc":"","max_playtimes":0,"modifytime":null,"movieCountry":"","duration":"","movie_ename":"","cast":"","poster":"/BESTV160415135330003355/POSTER/BESTV160415135330003355.jpg","filename":"荒野猎人-原声版","mid":"BESTV160415135330003355","uuid":"e93095a8-58b5-46bd-bef4-a0e510102dd9","brief":""},{"score":"4.7","expiredate":"","publishdate":"","type":"","createtime":null,"filmlength":"","movie_clickcount":0,"video":"","movie_name":"消失爱人","audio":"","filmhlength":"","status":0,"kdm_addr":"","director":"","moviecountry":"","file_desc":"","max_playtimes":0,"modifytime":null,"movieCountry":"","duration":"","movie_ename":"","cast":"","poster":"/BESTV160415140123003360/POSTER/BESTV160415140123003360.jpg","filename":"消失爱人","mid":"BESTV160415140123003360","uuid":"477b2f5d-1fb8-47cd-8e2d-0aec954a818d","brief":""},{"score":"5.4","expiredate":"","publishdate":"","type":"","createtime":null,"filmlength":"","movie_clickcount":0,"video":"","movie_name":"高跟鞋先生","audio":"","filmhlength":"","status":0,"kdm_addr":"","director":"","moviecountry":"","file_desc":"","max_playtimes":0,"modifytime":null,"movieCountry":"","duration":"","movie_ename":"","cast":"","poster":"/BESTV160318132953002879/POSTER/BESTV160318132953002879.jpg","filename":"高跟鞋先生","mid":"BESTV160318132953002879","uuid":"dc998aea-6338-4dcc-96d3-ebbca7cbf16d","brief":""},{"score":"7.2","expiredate":"","publishdate":"","type":"","createtime":null,"filmlength":"","movie_clickcount":0,"video":"","movie_name":"圣诞颂歌-3D国语版","audio":"","filmhlength":"","status":0,"kdm_addr":"","director":"","moviecountry":"","file_desc":"","max_playtimes":0,"modifytime":null,"movieCountry":"","duration":"","movie_ename":"","cast":"","poster":"/BESTV151022163124001390/POSTER/BESTV151022163124001390.jpg","filename":"圣诞颂歌-3D国语版","mid":"BESTV151022163124001390","uuid":"9070a4ec-ac90-41d0-bf7a-c389b4560e23","brief":""}],"user":"PAD1465889962927"}
     * msgType : getFilmListOrderByHotest
     * code : 3
     * user : PAD1465889962927
     */

    private String timeStamp;
    private int status;
    /**
     * data : [{"score":"7.9","expiredate":"","publishdate":"","type":"","createtime":null,"filmlength":"","movie_clickcount":0,"video":"","movie_name":"荒野猎人-原声版","audio":"","filmhlength":"","status":0,"kdm_addr":"","director":"","moviecountry":"","file_desc":"","max_playtimes":0,"modifytime":null,"movieCountry":"","duration":"","movie_ename":"","cast":"","poster":"/BESTV160415135330003355/POSTER/BESTV160415135330003355.jpg","filename":"荒野猎人-原声版","mid":"BESTV160415135330003355","uuid":"e93095a8-58b5-46bd-bef4-a0e510102dd9","brief":""},{"score":"4.7","expiredate":"","publishdate":"","type":"","createtime":null,"filmlength":"","movie_clickcount":0,"video":"","movie_name":"消失爱人","audio":"","filmhlength":"","status":0,"kdm_addr":"","director":"","moviecountry":"","file_desc":"","max_playtimes":0,"modifytime":null,"movieCountry":"","duration":"","movie_ename":"","cast":"","poster":"/BESTV160415140123003360/POSTER/BESTV160415140123003360.jpg","filename":"消失爱人","mid":"BESTV160415140123003360","uuid":"477b2f5d-1fb8-47cd-8e2d-0aec954a818d","brief":""},{"score":"5.4","expiredate":"","publishdate":"","type":"","createtime":null,"filmlength":"","movie_clickcount":0,"video":"","movie_name":"高跟鞋先生","audio":"","filmhlength":"","status":0,"kdm_addr":"","director":"","moviecountry":"","file_desc":"","max_playtimes":0,"modifytime":null,"movieCountry":"","duration":"","movie_ename":"","cast":"","poster":"/BESTV160318132953002879/POSTER/BESTV160318132953002879.jpg","filename":"高跟鞋先生","mid":"BESTV160318132953002879","uuid":"dc998aea-6338-4dcc-96d3-ebbca7cbf16d","brief":""},{"score":"7.2","expiredate":"","publishdate":"","type":"","createtime":null,"filmlength":"","movie_clickcount":0,"video":"","movie_name":"圣诞颂歌-3D国语版","audio":"","filmhlength":"","status":0,"kdm_addr":"","director":"","moviecountry":"","file_desc":"","max_playtimes":0,"modifytime":null,"movieCountry":"","duration":"","movie_ename":"","cast":"","poster":"/BESTV151022163124001390/POSTER/BESTV151022163124001390.jpg","filename":"圣诞颂歌-3D国语版","mid":"BESTV151022163124001390","uuid":"9070a4ec-ac90-41d0-bf7a-c389b4560e23","brief":""}]
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

    public  class DataBean {
        private String user;
        /**
         * score : 7.9
         * expiredate :
         * publishdate :
         * type :
         * createtime : null
         * filmlength :
         * movie_clickcount : 0
         * video :
         * movie_name : 荒野猎人-原声版
         * audio :
         * filmhlength :
         * status : 0
         * kdm_addr :
         * director :
         * moviecountry :
         * file_desc :
         * max_playtimes : 0
         * modifytime : null
         * movieCountry :
         * duration :
         * movie_ename :
         * cast :
         * poster : /BESTV160415135330003355/POSTER/BESTV160415135330003355.jpg
         * filename : 荒野猎人-原声版
         * mid : BESTV160415135330003355
         * uuid : e93095a8-58b5-46bd-bef4-a0e510102dd9
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
