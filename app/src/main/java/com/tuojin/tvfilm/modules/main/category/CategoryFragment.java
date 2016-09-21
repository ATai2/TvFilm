package com.tuojin.tvfilm.modules.main.category;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.base.BaseView;
import com.tuojin.tvfilm.bean.CategoryInfo;
import com.tuojin.tvfilm.contract.CategoryContract;
import com.tuojin.tvfilm.presenter.CategoryPresenterImpl;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseFragment<CategoryContract.View, CategoryPresenterImpl> implements CategoryContract.View, BaseView {

    public static final String TAG = "CategoryFragment";

    @BindView(R.id.mRvCategory)
    RecyclerView mMRvCategory;

    CategoryAdapter mCategoryAdapter;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initView() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        mMRvCategory.setHasFixedSize(true);
        mMRvCategory.setLayoutManager(staggeredGridLayoutManager);
    }

    @Override
    protected CategoryPresenterImpl initPresenter() {
        return new CategoryPresenterImpl();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mMRvCategory.getChildCount() == 0) {
            mPresenter.onResume();
        }
    }

    @Override
    public void setRecycleCategoryList(List<CategoryInfo> datas) {
        mCategoryAdapter = new CategoryAdapter(mActivity, datas);
        mMRvCategory.setAdapter(mCategoryAdapter);

    }
}
