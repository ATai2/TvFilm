package com.tuojin.tvfilm.bean;

/**
 * 文 件 名: FilmPauseBean
 * 创 建 人: Administrator
 * 创建日期: 2016/11/14 09:52
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class FilmPauseBean {
    /**
     * timeStamp : 7258089599000
     * status : 3
     * data : {"data":{"errorMessage":"","firmware":"","os":"","model":"","isexist":"","status":"OK","version":"2","currentTime":"2016-08-05 15:54:14","software":"","playBackMode":"2D","isoDateTime":"","user":"","confirm":"","serial":"","obStatus":{"paidFlag":"","cplPostionIndex":"0","state":"PAUSED","previewOverFlag":"","cplPostionTotalDuration":"6825","showPostionTotalDuration":"6825","showName":"龙虾-原声版","cplUuid":"0fbba868-ae64-4580-aa1f-111c24c6d7e7","cplName":"LongXia-En","chargeFlag":"","showPostionPlayedDuration":"737","cplPostionPlayedDuration":"737","showUuid":"d60441eb-6d51-41e7-b90c-3d5157ca57c1"}}}
     * msgType : previewConfirm
     * code : 3
     * user : PAD1470377342730
     */

    private String timeStamp;
    private int status;
    /**
     * data : {"errorMessage":"","firmware":"","os":"","model":"","isexist":"","status":"OK","version":"2","currentTime":"2016-08-05 15:54:14","software":"","playBackMode":"2D","isoDateTime":"","user":"","confirm":"","serial":"","obStatus":{"paidFlag":"","cplPostionIndex":"0","state":"PAUSED","previewOverFlag":"","cplPostionTotalDuration":"6825","showPostionTotalDuration":"6825","showName":"龙虾-原声版","cplUuid":"0fbba868-ae64-4580-aa1f-111c24c6d7e7","cplName":"LongXia-En","chargeFlag":"","showPostionPlayedDuration":"737","cplPostionPlayedDuration":"737","showUuid":"d60441eb-6d51-41e7-b90c-3d5157ca57c1"}}
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
        /**
         * errorMessage :
         * firmware :
         * os :
         * model :
         * isexist :
         * status : OK
         * version : 2
         * currentTime : 2016-08-05 15:54:14
         * software :
         * playBackMode : 2D
         * isoDateTime :
         * user :
         * confirm :
         * serial :
         * obStatus : {"paidFlag":"","cplPostionIndex":"0","state":"PAUSED","previewOverFlag":"","cplPostionTotalDuration":"6825","showPostionTotalDuration":"6825","showName":"龙虾-原声版","cplUuid":"0fbba868-ae64-4580-aa1f-111c24c6d7e7","cplName":"LongXia-En","chargeFlag":"","showPostionPlayedDuration":"737","cplPostionPlayedDuration":"737","showUuid":"d60441eb-6d51-41e7-b90c-3d5157ca57c1"}
         */

        private PauseBean data;

        public PauseBean getData() {
            return data;
        }

        public void setData(PauseBean data) {
            this.data = data;
        }

        public static class PauseBean {
            private String errorMessage;
            private String firmware;
            private String os;
            private String model;
            private String isexist;
            private String status;
            private String version;
            private String currentTime;
            private String software;
            private String playBackMode;
            private String isoDateTime;
            private String user;
            private String confirm;
            private String serial;
            /**
             * paidFlag :
             * cplPostionIndex : 0
             * state : PAUSED
             * previewOverFlag :
             * cplPostionTotalDuration : 6825
             * showPostionTotalDuration : 6825
             * showName : 龙虾-原声版
             * cplUuid : 0fbba868-ae64-4580-aa1f-111c24c6d7e7
             * cplName : LongXia-En
             * chargeFlag :
             * showPostionPlayedDuration : 737
             * cplPostionPlayedDuration : 737
             * showUuid : d60441eb-6d51-41e7-b90c-3d5157ca57c1
             */

            private ObStatusBean obStatus;

            public String getErrorMessage() {
                return errorMessage;
            }

            public void setErrorMessage(String errorMessage) {
                this.errorMessage = errorMessage;
            }

            public String getFirmware() {
                return firmware;
            }

            public void setFirmware(String firmware) {
                this.firmware = firmware;
            }

            public String getOs() {
                return os;
            }

            public void setOs(String os) {
                this.os = os;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getIsexist() {
                return isexist;
            }

            public void setIsexist(String isexist) {
                this.isexist = isexist;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getCurrentTime() {
                return currentTime;
            }

            public void setCurrentTime(String currentTime) {
                this.currentTime = currentTime;
            }

            public String getSoftware() {
                return software;
            }

            public void setSoftware(String software) {
                this.software = software;
            }

            public String getPlayBackMode() {
                return playBackMode;
            }

            public void setPlayBackMode(String playBackMode) {
                this.playBackMode = playBackMode;
            }

            public String getIsoDateTime() {
                return isoDateTime;
            }

            public void setIsoDateTime(String isoDateTime) {
                this.isoDateTime = isoDateTime;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public String getConfirm() {
                return confirm;
            }

            public void setConfirm(String confirm) {
                this.confirm = confirm;
            }

            public String getSerial() {
                return serial;
            }

            public void setSerial(String serial) {
                this.serial = serial;
            }

            public ObStatusBean getObStatus() {
                return obStatus;
            }

            public void setObStatus(ObStatusBean obStatus) {
                this.obStatus = obStatus;
            }

            public static class ObStatusBean {
                private String paidFlag;
                private String cplPostionIndex;
                private String state;
                private String previewOverFlag;
                private String cplPostionTotalDuration;
                private String showPostionTotalDuration;
                private String showName;
                private String cplUuid;
                private String cplName;
                private String chargeFlag;
                private String showPostionPlayedDuration;
                private String cplPostionPlayedDuration;
                private String showUuid;

                public String getPaidFlag() {
                    return paidFlag;
                }

                public void setPaidFlag(String paidFlag) {
                    this.paidFlag = paidFlag;
                }

                public String getCplPostionIndex() {
                    return cplPostionIndex;
                }

                public void setCplPostionIndex(String cplPostionIndex) {
                    this.cplPostionIndex = cplPostionIndex;
                }

                public String getState() {
                    return state;
                }

                public void setState(String state) {
                    this.state = state;
                }

                public String getPreviewOverFlag() {
                    return previewOverFlag;
                }

                public void setPreviewOverFlag(String previewOverFlag) {
                    this.previewOverFlag = previewOverFlag;
                }

                public String getCplPostionTotalDuration() {
                    return cplPostionTotalDuration;
                }

                public void setCplPostionTotalDuration(String cplPostionTotalDuration) {
                    this.cplPostionTotalDuration = cplPostionTotalDuration;
                }

                public String getShowPostionTotalDuration() {
                    return showPostionTotalDuration;
                }

                public void setShowPostionTotalDuration(String showPostionTotalDuration) {
                    this.showPostionTotalDuration = showPostionTotalDuration;
                }

                public String getShowName() {
                    return showName;
                }

                public void setShowName(String showName) {
                    this.showName = showName;
                }

                public String getCplUuid() {
                    return cplUuid;
                }

                public void setCplUuid(String cplUuid) {
                    this.cplUuid = cplUuid;
                }

                public String getCplName() {
                    return cplName;
                }

                public void setCplName(String cplName) {
                    this.cplName = cplName;
                }

                public String getChargeFlag() {
                    return chargeFlag;
                }

                public void setChargeFlag(String chargeFlag) {
                    this.chargeFlag = chargeFlag;
                }

                public String getShowPostionPlayedDuration() {
                    return showPostionPlayedDuration;
                }

                public void setShowPostionPlayedDuration(String showPostionPlayedDuration) {
                    this.showPostionPlayedDuration = showPostionPlayedDuration;
                }

                public String getCplPostionPlayedDuration() {
                    return cplPostionPlayedDuration;
                }

                public void setCplPostionPlayedDuration(String cplPostionPlayedDuration) {
                    this.cplPostionPlayedDuration = cplPostionPlayedDuration;
                }

                public String getShowUuid() {
                    return showUuid;
                }

                public void setShowUuid(String showUuid) {
                    this.showUuid = showUuid;
                }
            }
        }
    }
}
