package com.tuojin.tvfilm.presenter;
import com.tuojin.tvfilm.base.BasePresenter;
import com.tuojin.tvfilm.contract.AreaContract;
import com.tuojin.tvfilm.model.AreaModelImpl;

/**
* Created by MVPHelper on 2016/10/09
*/

public class AreaPresenterImpl extends BasePresenter<AreaContract.View> implements AreaContract.Presenter{
    AreaModelImpl mModel=new AreaModelImpl(this);
    @Override
    public void list(String a) {
        mModel.list(a);
    }

    @Override
    public void listByArea(int id, String movie_country) {
        mModel.listByArea(id,movie_country);
    }

}