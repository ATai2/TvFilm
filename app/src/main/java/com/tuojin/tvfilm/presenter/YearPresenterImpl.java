package com.tuojin.tvfilm.presenter;
import com.tuojin.tvfilm.base.BasePresenter;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.YearBean;
import com.tuojin.tvfilm.contract.YearContract;
import com.tuojin.tvfilm.model.YearModelImpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
* Created by MVPHelper on 2016/10/11
*/

public class YearPresenterImpl extends BasePresenter<YearContract.View> implements YearContract.Presenter{

    YearModelImpl mModel=new YearModelImpl(this);
    @Override
    public void menu() {
        mModel.menu();
    }

    @Override
    public void initMenu(List<YearBean> list) {

        Collections.sort(list, new Comparator<YearBean>() {
            @Override
            public int compare(YearBean lhs, YearBean rhs) {
                int i=Integer.parseInt(lhs.getMovie_year());
                int j=Integer.parseInt(rhs.getMovie_year());
                return j-i;
            }
        });
        mView.initMenu(list);
    }

    @Override
    public void list(int i) {
        mModel.list(i);
    }

    @Override
    public void initList(List<FilmBean> list) {
        mView.initList(list);
    }
}