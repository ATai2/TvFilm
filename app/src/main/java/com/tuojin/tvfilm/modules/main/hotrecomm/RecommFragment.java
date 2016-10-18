package com.tuojin.tvfilm.modules.main.hotrecomm;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.contract.HotRecommContract;
import com.tuojin.tvfilm.keybord.FocusGridLayoutManager;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.OnItemClickListener;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
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
    private List<FilmBean> mList;
    private CommonAdapter<FilmBean> mOtherAdapter;
    private FilmBean mValue;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recomm;
    }

    @Override
    protected void initView() {
        FocusGridLayoutManager focusGridLayoutManager = new FocusGridLayoutManager(mActivity, 2);
        focusGridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvRecomm.setFocusable(true);
        mRvRecomm.stopScroll();
        mRvRecomm.setHasFixedSize(true);
        mRvRecomm.setLayoutManager(focusGridLayoutManager);
        mPresenter.onResume();
    }

    @Override
    protected HotRecommPresenterImpl initPresenter() {
        return new HotRecommPresenterImpl();
    }


//    @Override
//    public void onResume() {
//        super.onResume();
//        if (mRvRecomm.getChildCount() == 0) {
//
//        }
//    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                changerView();
            }
        }
    };

    public void changerView() {
        mOtherAdapter = new CommonAdapter<FilmBean>(mActivity, R.layout.item_other_recomm, mList, 2) {
            @Override
            public void convert(ViewHolder holder, FilmBean bean) {
                holder.setText(R.id.movie_title_other, bean.getMovie_name());
                holder.setText(R.id.movie_title_other_score, bean.getScore());
                holder.setImageResource(R.id.movie_image_other, bean.getPoster());
                holder.setPropertyAnimation(R.id.movie_title_other);
            }
        };
//        mOtherAdapter.
        mOtherAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Intent intent = new Intent(mActivity, FilmDetailActivity.class);
                mValue = mList.get(position);
                intent.putExtra("film", mValue);
                startActivity(intent);
            }
        });
//        mAdapter = new RecommAdapter(mActivity, mList);
        mRvRecomm.setAdapter(mOtherAdapter);
//        mAdapter.setListener(new RecommAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(FilmBean bean) {
//                Log.d(TAG, "onclick" + bean.toString());
//                Intent intent = new Intent(mActivity, FilmDetailActivity.class);
//                intent.putExtra("film", bean);
//                mActivity.startActivity(intent);
//            }
//        });
//        mHandler.sendEmptyMessageDelayed(1,200);
    }

    @Override
    public void setRecycleList(List<FilmBean> mDatas) {
        mList = mDatas;
        mHandler.sendEmptyMessage(0);
    }
}
