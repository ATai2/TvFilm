package com.tuojin.tvfilm.presenter;

import com.tuojin.tvfilm.base.BasePresenter;
import com.tuojin.tvfilm.bean.CategoryInfo;
import com.tuojin.tvfilm.contract.CategoryContract;
import com.tuojin.tvfilm.model.CategoryModelImpl;

import java.util.List;

/**
* Created by MVPHelper on 2016/09/21
*/

public class CategoryPresenterImpl extends BasePresenter<CategoryContract.View> implements CategoryContract.Presenter {
    @Override
    public void onResume() {
        CategoryModelImpl model = new CategoryModelImpl();
        List<CategoryInfo> categoryInfos = model.initCategory();
        mView.setRecycleCategoryList(categoryInfos);
    }

}