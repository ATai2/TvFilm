package com.tuojin.tvfilm.bean;

/**
 * 文 件 名: FilmDetailBean
 * 创 建 人: Administrator
 * 创建日期: 2016/9/23 16:35
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class FilmDetailBean {
    /**
     * timeStamp :
     * status : 3
     * data : {"data":{"score":"7.2","expiredate":"2017-03-31 23:59:59.0","type":"","publishdate":"2016-02-08","createtime":null,"filmlength":"93","movie_clickcount":0,"video":"/opt/video/nasbak/BESTV160422133926003485/DCP/023416ed-c7ed-48db-b1b3-78262fe7348e/wav_MeiRenYu_ac3_aud.mxf","movie_name":"美人鱼","filmhlength":"","audio":"/opt/video/nasbak/BESTV160422133926003485/DCP/023416ed-c7ed-48db-b1b3-78262fe7348e/avc_MeiRenYu_ac3_vid.mxf","status":0,"kdm_addr":"/opt/video/nasbak/BESTV160422133926003485/KDM/SMET15128361/023416ed-c7ed-48db-b1b3-78262fe7348e_SMET15128361_170331235959_9999.xml","director":"周星驰","moviecountry":"","file_desc":"","max_playtimes":0,"modifytime":null,"movieCountry":"","duration":"","movie_ename":"","cast":"邓超 / 罗志祥 / 张雨绮 / 林允 / 徐克 / 吴亦凡 / 李尚正 / 卢正雨 / 白客 / 孔连顺 / 田启文 / 文章 / 杨能 / 张美娥 / 李叶青 / 林子聪","poster":"/BESTV160422133926003485/POSTER/BESTV160422133926003485.jpg","filename":"美人鱼","uuid":"023416ed-c7ed-48db-b1b3-78262fe7348e","mid":"BESTV160422133926003485","brief":"白手起家的富豪刘轩（邓超 饰）新拍下了一块地皮，并联合了女强人李若兰（张雨绮 饰）使用恐怖的声纳技术驱赶鱼类，用于填海造地。人鱼一族长期居住在附近区域的海里，为了继续生存，带头大哥章鱼八哥（罗志祥 饰）派出了美人鱼珊珊（林允 饰）\u201c色诱\u201d刘轩，准备刺杀。没想到珊珊在卧底过程中与刘轩暗生情愫，一次次破坏暗杀计划，而李若兰却在准备一个更丧心病狂的邪恶计划\u2026\u2026"},"user":"PAD1465889962927"}
     * msgType : getFilmDetail
     * code : 3
     * user : PAD1465889962927
     */

    private String timeStamp;
    private int status;
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
        /**
         * score : 7.2
         * expiredate : 2017-03-31 23:59:59.0
         * type :
         * publishdate : 2016-02-08
         * createtime : null
         * filmlength : 93
         * movie_clickcount : 0
         * video : /opt/video/nasbak/BESTV160422133926003485/DCP/023416ed-c7ed-48db-b1b3-78262fe7348e/wav_MeiRenYu_ac3_aud.mxf
         * movie_name : 美人鱼
         * filmhlength :
         * audio : /opt/video/nasbak/BESTV160422133926003485/DCP/023416ed-c7ed-48db-b1b3-78262fe7348e/avc_MeiRenYu_ac3_vid.mxf
         * status : 0
         * kdm_addr : /opt/video/nasbak/BESTV160422133926003485/KDM/SMET15128361/023416ed-c7ed-48db-b1b3-78262fe7348e_SMET15128361_170331235959_9999.xml
         * director : 周星驰
         * moviecountry :
         * file_desc :
         * max_playtimes : 0
         * modifytime : null
         * movieCountry :
         * duration :
         * movie_ename :
         * cast : 邓超 / 罗志祥 / 张雨绮 / 林允 / 徐克 / 吴亦凡 / 李尚正 / 卢正雨 / 白客 / 孔连顺 / 田启文 / 文章 / 杨能 / 张美娥 / 李叶青 / 林子聪
         * poster : /BESTV160422133926003485/POSTER/BESTV160422133926003485.jpg
         * filename : 美人鱼
         * uuid : 023416ed-c7ed-48db-b1b3-78262fe7348e
         * mid : BESTV160422133926003485
         * brief : 白手起家的富豪刘轩（邓超 饰）新拍下了一块地皮，并联合了女强人李若兰（张雨绮 饰）使用恐怖的声纳技术驱赶鱼类，用于填海造地。人鱼一族长期居住在附近区域的海里，为了继续生存，带头大哥章鱼八哥（罗志祥 饰）派出了美人鱼珊珊（林允 饰）“色诱”刘轩，准备刺杀。没想到珊珊在卧底过程中与刘轩暗生情愫，一次次破坏暗杀计划，而李若兰却在准备一个更丧心病狂的邪恶计划……
         */

        private FilmDetailDataBean data;
        private String user;

        public FilmDetailDataBean getData() {
            return data;
        }

        public void setData(FilmDetailDataBean data) {
            this.data = data;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public static class FilmDetailDataBean {
            private String score;
            private String expiredate;
            private String type;
            private String publishdate;
            private Object createtime;
            private String filmlength;
            private int movie_clickcount;
            private String video;
            private String movie_name;
            private String filmhlength;
            private String audio;
            private int status;
            private String kdm_addr;
            private String director;
            private String moviecountry;
            private String file_desc;
            private int max_playtimes;
            private Object modifytime;
            private String movieCountry;
            private String duration;
            private String movie_ename;
            private String cast;
            private String poster;
            private String filename;
            private String uuid;
            private String mid;
            private String brief;

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getExpiredate() {
                return expiredate;
            }

            public void setExpiredate(String expiredate) {
                this.expiredate = expiredate;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPublishdate() {
                return publishdate;
            }

            public void setPublishdate(String publishdate) {
                this.publishdate = publishdate;
            }

            public Object getCreatetime() {
                return createtime;
            }

            public void setCreatetime(Object createtime) {
                this.createtime = createtime;
            }

            public String getFilmlength() {
                return filmlength;
            }

            public void setFilmlength(String filmlength) {
                this.filmlength = filmlength;
            }

            public int getMovie_clickcount() {
                return movie_clickcount;
            }

            public void setMovie_clickcount(int movie_clickcount) {
                this.movie_clickcount = movie_clickcount;
            }

            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
                this.video = video;
            }

            public String getMovie_name() {
                return movie_name;
            }

            public void setMovie_name(String movie_name) {
                this.movie_name = movie_name;
            }

            public String getFilmhlength() {
                return filmhlength;
            }

            public void setFilmhlength(String filmhlength) {
                this.filmhlength = filmhlength;
            }

            public String getAudio() {
                return audio;
            }

            public void setAudio(String audio) {
                this.audio = audio;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getKdm_addr() {
                return kdm_addr;
            }

            public void setKdm_addr(String kdm_addr) {
                this.kdm_addr = kdm_addr;
            }

            public String getDirector() {
                return director;
            }

            public void setDirector(String director) {
                this.director = director;
            }

            public String getMoviecountry() {
                return moviecountry;
            }

            public void setMoviecountry(String moviecountry) {
                this.moviecountry = moviecountry;
            }

            public String getFile_desc() {
                return file_desc;
            }

            public void setFile_desc(String file_desc) {
                this.file_desc = file_desc;
            }

            public int getMax_playtimes() {
                return max_playtimes;
            }

            public void setMax_playtimes(int max_playtimes) {
                this.max_playtimes = max_playtimes;
            }

            public Object getModifytime() {
                return modifytime;
            }

            public void setModifytime(Object modifytime) {
                this.modifytime = modifytime;
            }

            public String getMovieCountry() {
                return movieCountry;
            }

            public void setMovieCountry(String movieCountry) {
                this.movieCountry = movieCountry;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getMovie_ename() {
                return movie_ename;
            }

            public void setMovie_ename(String movie_ename) {
                this.movie_ename = movie_ename;
            }

            public String getCast() {
                return cast;
            }

            public void setCast(String cast) {
                this.cast = cast;
            }

            public String getPoster() {
                return poster;
            }

            public void setPoster(String poster) {
                this.poster = poster;
            }

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }
        }
    }
}
