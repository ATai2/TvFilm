package com.tuojin.tvfilm.modules.main.filmdetail;

import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.utils.Constant;

/**
 * 文 件 名: Controller
 * 创 建 人: Administrator
 * 创建日期: 2016/11/21 17:12
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class Controller {



    TvFilmNetWorkWS mNetWorkWS=new TvFilmNetWorkWS();

    public void listByArea(int id, final String movie_country) {
        mNetWorkWS.sendMsg(Constant.PADMAC +
                "|getFilmList|terminalCode=" +
                Constant.TERMINAL_CODE +
                "&searchMode=&keyword=&movieTypeStr=&placeId=" +
                id +
                "&yearId=&actorId=&directorId=&startIndex=0&endIndex=100",2);
    }
}
