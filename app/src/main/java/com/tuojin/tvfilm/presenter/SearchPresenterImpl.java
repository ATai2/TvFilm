package com.tuojin.tvfilm.presenter;
import com.tuojin.tvfilm.base.BasePresenter;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.contract.SearchContract;
import com.tuojin.tvfilm.model.SearchModelImpl;

import java.util.List;

/**
* Created by MVPHelper on 2016/09/21
*/

public class SearchPresenterImpl extends BasePresenter<SearchContract.View> implements SearchContract.Presenter{
    SearchModelImpl mModel=new SearchModelImpl(this);
    @Override
    public void search(String key) {
        mModel.search(key);
    }

    @Override
    public void refreshUI(List<FilmBean> beanList) {
        mView.refreshUI(beanList);
    }
}