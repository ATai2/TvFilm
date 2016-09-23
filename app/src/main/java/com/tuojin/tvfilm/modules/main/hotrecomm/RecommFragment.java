package com.tuojin.tvfilm.modules.main.hotrecomm;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.contract.HotRecommContract;
import com.tuojin.tvfilm.modules.main.FilmDetailActivity;
import com.tuojin.tvfilm.presenter.HotRecommPresenterImpl;

import java.util.List;

import butterknife.BindView;

/**
 * 文 件 名: CategoryContract
 * 创 建 人: Administrator
 * 创建日期: 2016/9/21 13:50
 * 文件描述：热门推荐位
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class RecommFragment extends BaseFragment<HotRecommContract.View, HotRecommPresenterImpl> implements HotRecommContract.View {


    @BindView(R.id.rv_recomm)
    RecyclerView mRvRecomm;
    private RecommAdapter mAdapter;

    @Override
    protected int getLayoutId() {
//        return R.layout.test_main;
        return R.layout.fragment_recomm;
    }

    @Override
    protected void initView() {
        StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
        mRvRecomm.setHasFixedSize(true);
        mRvRecomm.setLayoutManager(layout);
        mRvRecomm.stopScroll();
        mRvRecomm.setAdapter(mAdapter);

    }

    @Override
    protected HotRecommPresenterImpl initPresenter() {
        return new HotRecommPresenterImpl();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mRvRecomm.getChildCount()==0){
            mPresenter.onResume();
        }
    }

    @Override
    public void setRecycleList(List<FilmBean> mDatas) {
        mAdapter=new RecommAdapter(mActivity,mDatas);
        mRvRecomm.setAdapter(mAdapter);
        mAdapter.setListener(new RecommAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FilmBean bean) {
                Log.d(TAG, "onclick"+bean.toString());
                Intent intent = new Intent(mActivity, FilmDetailActivity.class);
                intent.putExtra("film", bean);
                mActivity.startActivity(intent);
            }
        });
//        mAdapter.notifyDataSetChanged();
    }
}
