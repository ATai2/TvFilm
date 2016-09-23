package com.tuojin.tvfilm.presenter;

import com.tuojin.tvfilm.base.BasePresenter;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.contract.HotRecommContract;
import com.tuojin.tvfilm.model.HotRecommModelImpl;

import java.util.List;

/**
* Created by MVPHelper on 2016/09/20
*/

public class HotRecommPresenterImpl extends BasePresenter<HotRecommContract.View> implements HotRecommContract.Presenter {

    @Override
    public void onResume() {
        HotRecommModelImpl model=new HotRecommModelImpl(this);
        //之后传入页数参数用于，数据加载
        model.getRecommList(0);
    }

    @Override
    public void loadData(List<FilmBean> mDatas) {
        mView.setRecycleList(mDatas);
    }
}