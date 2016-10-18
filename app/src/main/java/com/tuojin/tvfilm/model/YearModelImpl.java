package com.tuojin.tvfilm.model;

import android.util.Log;

import com.google.gson.Gson;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.RecommBean;
import com.tuojin.tvfilm.bean.YearBean;
import com.tuojin.tvfilm.bean.YearListBean;
import com.tuojin.tvfilm.contract.YearContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.YearPresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

import java.util.List;

/**
 * Created by MVPHelper on 2016/10/11
 */

public class YearModelImpl implements YearContract.Model {
    YearPresenterImpl mYearPresenter;
    TvFilmNetWorkWS mNetWorkWS = new TvFilmNetWorkWS();

    public YearModelImpl(YearPresenterImpl yearPresenter) {
        mYearPresenter = yearPresenter;
    }

    @Override
    public void menu() {
        String data = "{\"timeStamp\":\"\",\"status\":3,\"data\":{\"data\":[{\"id\":47,\"movie_year\":\"2016\"},{\"id\":1,\"movie_year\":\"2015\"},{\"id\":2,\"movie_year\":\"2014\"},{\"id\":3,\"movie_year\":\"2013\"},{\"id\":4,\"movie_year\":\"2012\"},{\"id\":5,\"movie_year\":\"2011\"},{\"id\":6,\"movie_year\":\"2010\"},{\"id\":7,\"movie_year\":\"2009\"},{\"id\":8,\"movie_year\":\"2008\"},{\"id\":9,\"movie_year\":\"2007\"},{\"id\":10,\"movie_year\":\"2006\"},{\"id\":11,\"movie_year\":\"2005\"},{\"id\":18,\"movie_year\":\"2004\"},{\"id\":19,\"movie_year\":\"2003\"},{\"id\":15,\"movie_year\":\"2002\"},{\"id\":21,\"movie_year\":\"2001\"},{\"id\":28,\"movie_year\":\"2000\"},{\"id\":13,\"movie_year\":\"1999\"},{\"id\":25,\"movie_year\":\"1998\"},{\"id\":20,\"movie_year\":\"1997\"},{\"id\":41,\"movie_year\":\"1996\"},{\"id\":23,\"movie_year\":\"1995\"},{\"id\":17,\"movie_year\":\"1994\"},{\"id\":16,\"movie_year\":\"1993\"},{\"id\":22,\"movie_year\":\"1992\"},{\"id\":27,\"movie_year\":\"1991\"},{\"id\":26,\"movie_year\":\"1990\"},{\"id\":24,\"movie_year\":\"1989\"},{\"id\":29,\"movie_year\":\"1988\"},{\"id\":12,\"movie_year\":\"1987\"},{\"id\":40,\"movie_year\":\"1986\"},{\"id\":36,\"movie_year\":\"1985\"},{\"id\":32,\"movie_year\":\"1984\"},{\"id\":43,\"movie_year\":\"1983\"},{\"id\":37,\"movie_year\":\"1982\"},{\"id\":34,\"movie_year\":\"1981\"},{\"id\":35,\"movie_year\":\"1979\"},{\"id\":44,\"movie_year\":\"1977\"},{\"id\":42,\"movie_year\":\"1971\"},{\"id\":33,\"movie_year\":\"1970\"},{\"id\":38,\"movie_year\":\"1962\"},{\"id\":39,\"movie_year\":\"1959\"},{\"id\":46,\"movie_year\":\"1955\"},{\"id\":31,\"movie_year\":\"1950\"},{\"id\":45,\"movie_year\":\"1944\"},{\"id\":30,\"movie_year\":\"1940\"},{\"id\":14,\"movie_year\":\"1937\"}],\"user\":\"PAD1465889962927\"},\"msgType\":\"getYearList\",\"code\":3,\"user\":\"PAD1465889962927\"}";
        List<YearBean> list = new Gson().fromJson(data, YearListBean.class).getData().getData();
        mYearPresenter.initMenu(list);
    }

    @Override
    public void list(int i) {
        mNetWorkWS.sendMsg(Constant.PADMAC +
                "|getFilmList|terminalCode=" +
                Constant.TERMINAL_CODE +
                "&searchMode=&keyword=&movieTypeStr=&placeId=&yearId=" +
                i +
                "&actorId=&directorId=&startIndex=0&endIndex=100", new TvFilmNetWorkWS.Success() {
            @Override
            public void excute(String data) {
                Log.d("list", data);
                List<FilmBean> list = new Gson().fromJson(data, RecommBean.class).getData().getData();
                mYearPresenter.initList(list);
            }
        }, new TvFilmNetWorkWS.Failure() {
            @Override
            public void excute(String data) {

            }
        });
    }
}