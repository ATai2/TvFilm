package com.tuojin.tvfilm.presenter;

import com.tuojin.tvfilm.base.BasePresenter;
import com.tuojin.tvfilm.contract.CateListContract;
import com.tuojin.tvfilm.model.CateListModelImpl;

import java.util.List;

/**
 * Created by MVPHelper on 2016/09/22
 */

public class CateListPresenterImpl<T> extends BasePresenter<CateListContract.View<T>> implements CateListContract.Presenter<T> {
    CateListModelImpl mModel = new CateListModelImpl(this);

    @Override
    public void initRadioButton(int type) {
        mModel.initRadioButton(type);
    }

    @Override
    public void initViewRadioButton(List directorBeanList) {
        mView.initViewRadioButton(directorBeanList);
    }
}