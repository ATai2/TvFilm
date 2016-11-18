package com.tuojin.tvfilm.utils;

/**
 * 文 件 名: Constant
 * 创 建 人: Administrator
 * 创建日期: 2016/9/23 12:10
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class Constant {
//    int TYPE = 100;
//    int YEAR = 102;
//    int DIRECTOR = 103;
//    int AREA = 104;
//    int LIVE = 105;
//    int ACTOR = 106;
//    //    CateFilmListActivty更新电影列表
//    int CFLA_INITLIST = 201;

    //   拓锦v1
//    String PADMAC = "PAD1473665892285";
//    String IP_TERMINAL = "192.168.1.96";
//    String TERMINAL_CODE = "SMET14017428";

    //    测试1
//    String PADMAC = "PAD1478490888830";
    public static String PADMAC = "PAD1470022778326";
    //    String PADMAC = "PAD1470000000001";
    public static String IP_TERMINAL = "192.168.1.34";
    public static String TERMINAL_CODE = "SMET15128361";
    //    测试
//    String PADMAC = "PAD1477886153491";
//    String IP_TERMINAL = "192.168.1.34";
//    String TERMINAL_CODE = "SMET15128361";

   public static final String WSSITE = "ws://192.168.1.243:8081/bestv-cinema-player/message?mac=" + PADMAC;

   public static final String GETTERMINAL = "getTerminal";
   public static final String GETFILMLISTORDERBYHOTEST = "getFilmListOrderByHotest";
   public static final String GET_FILMLIST_ORDER_BYNEWEST = "getFilmListOrderByNewest";
   public static final String GET_FILMLIST_ORDER_BYPAYMOVIE = "getFilmListOrderByPaymovie";
   public static final String GET_FILMLIST_ORDER_BYHOTEST = "getFilmListOrderByHotest";
   public static final String GET_FILMLIST_ORDER_BYDOUBAN = "getFilmListOrderByScore";
   public static final String GET_FILMLIST_ORDER_BYDIRECTOR = "getDoctorList";
   public static final String GET_FILMLIST_ORDER_BYACTOR = "getActorList";
   public static final String PLACELIST = "getPlaceList";
   public static final String TYPELIST = "getFilmTypeList";
   public static final String YEARLIST = "getYearList";
   public static final String SCORELIST = "getFilmListOrderByScore";
   public static final String DETAIL = "getFilmDetail";
   public static final String ALIPAY = "payAli";
   public static final String FILMLIST = "getFilmList";
   public static final String STARTPLAY = "startPlay";
   public static final String PAY_PREVIEWOVER = "payPreviewOver";
   public static final String PAYSUCCESS = "paySuccess";
   public static final String STOPPLAY = "stopPlay";
   public static final String PAUSEPLAY = "pausePlay";
   public static final String CONTINUEPLAY = "continuePlay";
   public static final String PLAYSTATUS = "playStatus";
   public static final String GOTOPOSITION = "goToPosition";
   public static final String FIRSTKEY = "getFilmListByFirstKey";

   public static final String BIGPAUSE = "previewConfirm";
   public static final String BIGPAUSECONTINUE = "previewConfirm";
   public static final String BIGSTOP = "previewConfirm";
   public static final String BIGPREVIEWPAUSE = "previewLimit";
   public static final String LIVELIST = "getLiveCategoryList";
   public static final String LIVECONTENTLIST = "getLiveContentList";
   public static final String BIGPREVEWSTOPCONFIRM = "previewLimit";
}
