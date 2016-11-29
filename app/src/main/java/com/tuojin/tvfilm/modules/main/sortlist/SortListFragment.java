package com.tuojin.tvfilm.modules.main.sortlist;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.base.BaseView;
import com.tuojin.tvfilm.contract.SortListContract;
import com.tuojin.tvfilm.presenter.SortListPresenterImpl;

import butterknife.BindView;

/**
 * 文 件 名: SortListFragment
 * 创 建 人: Administrator
 * 创建日期: 2016/9/21 15:53
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class SortListFragment extends BaseFragment<SortListContract.View, SortListPresenterImpl> implements SortListContract.View, BaseView {


    @BindView(R.id.rv_sort)
    RecyclerView mRvSort;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sortlist;
    }

    @Override
    protected void initView() {
        StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mRvSort.setLayoutManager(layout);
        SortAdapter adapter=new SortAdapter(mActivity);
        mRvSort.setAdapter(adapter);
        adapter.setListener(new SortAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int bean) {
                Intent intent=new Intent(mActivity,SortActivity.class);
                intent.putExtra("position",bean);
                mActivity.startActivity(intent);
            }
        });
    }

    @Override
    protected SortListPresenterImpl initPresenter() {
        return null;
    }

}
