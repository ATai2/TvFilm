package com.tuojin.tvfilm.modules.main.category;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.base.BaseView;
import com.tuojin.tvfilm.bean.CategoryInfo;
import com.tuojin.tvfilm.contract.CategoryContract;
import com.tuojin.tvfilm.modules.catelist.actor.ActorActivity;
import com.tuojin.tvfilm.modules.catelist.area.AreaActivity;
import com.tuojin.tvfilm.modules.catelist.director.DirectorActivity;
import com.tuojin.tvfilm.modules.catelist.live.LiveActivity;
import com.tuojin.tvfilm.modules.catelist.time.YearActivity;
import com.tuojin.tvfilm.modules.catelist.type.TypeActivity;
import com.tuojin.tvfilm.presenter.CategoryPresenterImpl;

import java.util.List;

import butterknife.BindView;

public class CategoryFragment extends BaseFragment<CategoryContract.View, CategoryPresenterImpl> implements CategoryContract.View, BaseView {

    public static final String TAG = "CategoryFragment";
    @BindView(R.id.rv_category)
    RecyclerView mRvCategory;
    private CategoryAdapter mCategoryAdapter;

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
        mCategoryAdapter.setListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int bean) {
                Intent intent = new Intent();
                switch (bean) {
                    case 0:
//                        intent.setClass(mActivity, TypeListActivity.class);
                        intent.setClass(mActivity, TypeActivity.class);
                        break;
                    case 3:
                        intent.setClass(mActivity, AreaActivity.class);
                        break;
                    case 4:
                        intent.setClass(mActivity, DirectorActivity.class);
                        break;
                    case 1:
                        intent.setClass(mActivity, YearActivity.class);
                        break;
                    case 2:
                        intent.setClass(mActivity, LiveActivity.class);
                        break;
                    case 5:
                        intent.setClass(mActivity, ActorActivity.class);
                }

                startActivity(intent);

//                Intent intent=new Intent(mActivity, CateFilmListActivty.class);
//                intent.putExtra("position",bean);
//                startActivity(intent);
            }
        });
    }


}
