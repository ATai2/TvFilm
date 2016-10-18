package com.tuojin.tvfilm.model;

import com.google.gson.Gson;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.FilmSearchKeyWordBean;
import com.tuojin.tvfilm.bean.FilmTypeBean;
import com.tuojin.tvfilm.bean.FilmTypeListBean;
import com.tuojin.tvfilm.contract.FilmTypeContract;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.FilmTypePresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

import java.util.List;

/**
 * Created by MVPHelper on 2016/09/29
 */

public class FilmTypeModelImpl implements FilmTypeContract.Model {

    FilmTypePresenterImpl mPresenter;
    TvFilmNetWorkWS netWorkWS = new TvFilmNetWorkWS();

    public FilmTypeModelImpl(FilmTypePresenterImpl presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(int type) {
        netWorkWS.sendMsg(Constant.PADMAC +
                        "|getFilmList|terminalCode=" +
                Constant.TERMINAL_CODE +
                "&searchMode=&keyword=&movieTypeStr=" +
                        type
                        + "&placeId=&yearId=&actorId=&directorId=&startIndex=0&endIndex=40"
                , new TvFilmNetWorkWS.Success() {
                    @Override
                    public void excute(String data) {
                        List<FilmBean> data1 = new Gson().fromJson(data, FilmSearchKeyWordBean.class).getData().getData();
                        mPresenter.initFilmFragment(data1);
                    }
                }, new TvFilmNetWorkWS.Failure() {
                    @Override
                    public void excute(String data) {

                    }
                });

    }

    @Override
    public void menu() {
        String data="{\"timeStamp\":\"\",\"status\":3,\"data\":{\"data\":[{\"id\":1,\"movieType\":\"爱情\",\"img\":\"\\\\pad\\\\type\\\\1.png\"},{\"id\":2,\"movieType\":\"喜剧\",\"img\":\"\\\\pad\\\\type\\\\2.png\"},{\"id\":3,\"movieType\":\"科幻\",\"img\":\"\\\\pad\\\\type\\\\3.png\"},{\"id\":4,\"movieType\":\"动画\",\"img\":\"\\\\pad\\\\type\\\\4.png\"},{\"id\":5,\"movieType\":\"动作\",\"img\":\"\\\\pad\\\\type\\\\5.png\"},{\"id\":6,\"movieType\":\"古装\",\"img\":\"\\\\pad\\\\type\\\\6.png\"},{\"id\":7,\"movieType\":\"奇幻\",\"img\":\"1.jpg\"},{\"id\":8,\"movieType\":\"冒险\",\"img\":\"1.jpg\"},{\"id\":9,\"movieType\":\"犯罪\",\"img\":\"1.jpg\"},{\"id\":10,\"movieType\":\"剧情\",\"img\":\"1.jpg\"},{\"id\":11,\"movieType\":\"战争\",\"img\":\"1.jpg\"},{\"id\":12,\"movieType\":\"惊悚\",\"img\":\"1.jpg\"},{\"id\":13,\"movieType\":\"悬疑\",\"img\":\"1.jpg\"},{\"id\":14,\"movieType\":\"历史\",\"img\":\"1.jpg\"},{\"id\":15,\"movieType\":\"传记\",\"img\":\"1.jpg\"},{\"id\":16,\"movieType\":\"灾难\",\"img\":\"1.jpg\"},{\"id\":17,\"movieType\":\"歌舞\",\"img\":\"1.jpg\"},{\"id\":18,\"movieType\":\"家庭\",\"img\":\"1.jpg\"},{\"id\":19,\"movieType\":\"恐怖\",\"img\":\"1.jpg\"},{\"id\":20,\"movieType\":\"武侠\",\"img\":\"1.jpg\"},{\"id\":21,\"movieType\":\"儿童\",\"img\":\"1.jpg\"},{\"id\":22,\"movieType\":\"音乐\",\"img\":\"1.jpg\"},{\"id\":23,\"movieType\":\"同性\",\"img\":\"1.jpg\"},{\"id\":24,\"movieType\":\"西部\",\"img\":\"1.jpg\"},{\"id\":25,\"movieType\":\"\",\"img\":\"1.jpg\"},{\"id\":26,\"movieType\":\"运动\",\"img\":\"1.jpg\"}],\"user\":\"PAD1465889962927\"},\"msgType\":\"getFilmTypeList\",\"code\":3,\"user\":\"PAD1465889962927\"} ";
        List<FilmTypeBean> list = new Gson().fromJson(data, FilmTypeListBean.class).getData().getData();

        mPresenter.initMenu(list);
    }
}