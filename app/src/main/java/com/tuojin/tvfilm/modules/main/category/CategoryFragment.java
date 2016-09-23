package com.tuojin.tvfilm.modules.main.category;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.base.BaseView;
import com.tuojin.tvfilm.bean.CategoryInfo;
import com.tuojin.tvfilm.contract.CategoryContract;
import com.tuojin.tvfilm.presenter.CategoryPresenterImpl;

import java.util.List;

import butterknife.BindView;

public class CategoryFragment extends BaseFragment<CategoryContract.View, CategoryPresenterImpl> implements CategoryContract.View, BaseView {

    public static final String TAG = "CategoryFragment";
    @BindView(R.id.rv_category)
    RecyclerView mRvCategory;
    private CategoryAdapter mCategoryAdapter;


    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initView() {
        StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        mRvCategory.setLayoutManager(layout);
    }

    @Override
    protected CategoryPresenterImpl initPresenter() {
        return new CategoryPresenterImpl();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mRvCategory.getChildCount() == 0) {
            mPresenter.onResume();
        }
    }

    @Override
    public void setRecycleCategoryList(List<CategoryInfo> datas) {
        mCategoryAdapter = new CategoryAdapter(mActivity, datas);
        mRvCategory.setAdapter(mCategoryAdapter);
    }


}
