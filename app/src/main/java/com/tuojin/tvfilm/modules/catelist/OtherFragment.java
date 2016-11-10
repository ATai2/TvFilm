package com.tuojin.tvfilm.modules.catelist;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.RecommBean;
import com.tuojin.tvfilm.contract.SortListContract;
import com.tuojin.tvfilm.event.FilmAdEvent;
import com.tuojin.tvfilm.event.FilmBigEvent;
import com.tuojin.tvfilm.event.FilmDoubanEvent;
import com.tuojin.tvfilm.event.FilmNewEvent;
import com.tuojin.tvfilm.event.HotRecommEvent;
import com.tuojin.tvfilm.keybord.FocusGridLayoutManager;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.OnItemClickListener;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.modules.main.FilmDetailActivity;
import com.tuojin.tvfilm.presenter.SortListPresenterImpl;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 * 文 件 名: OtherFragment
 * 创 建 人: Administrator
 * 创建日期: 2016/9/27 12:57
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class OtherFragment extends BaseFragment<SortListContract.View, SortListPresenterImpl> implements SortListContract.View {
    @BindView(R.id.recyclerview)
    public RecyclerView mRecyclerview;
    private FocusGridLayoutManager mGridLayoutManager;
    private List<FilmBean> mFilmBeen;
    private static CommonAdapter mOtherAdapter;
    private int mSortType;
    private FilmBean mValue;
    private boolean aBoolean = true;
    private boolean mBig;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_other;
    }

    @Override
    protected void initView() {
        mSortType = getArguments().getInt("sortType", 0);
        mBig = getArguments().getBoolean("big", false);
        mGridLayoutManager = new FocusGridLayoutManager(mActivity, 5);
        mGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(mGridLayoutManager);

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume(mSortType, 0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FilmNewEvent event) {
        String msg = event.msg;
        initList(msg);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FilmBigEvent event) {
        String msg = event.msg;
        initList(msg);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FilmAdEvent event) {
        String msg = event.msg;
        initList(msg);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FilmDoubanEvent event) {
        String msg = event.msg;
        initList(msg);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(HotRecommEvent event) {
        String msg = event.msg;
        initList(msg);
    }

    private void initList(String msg) {
        List<FilmBean> beanList = new Gson().fromJson(msg, RecommBean.class).getData().getData();
        mOtherAdapter = new CommonAdapter<FilmBean>(mActivity, R.layout.item_other, beanList, 1) {
            @Override
            public void convert(ViewHolder holder, FilmBean bean) {
                holder.setText(R.id.movie_title_other, bean.getMovie_name());
                holder.setImageResource(R.id.movie_image_other, bean.getPoster());
                holder.setScaleAnimation(R.id.movie_title_other);
            }
        };
        mOtherAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Intent intent = new Intent(mActivity, FilmDetailActivity.class);
                mValue = mFilmBeen.get(position);
                intent.putExtra("film", mValue);
                intent.putExtra("big", mBig);
                startActivity(intent);
            }
        });
        mRecyclerview.setAdapter(mOtherAdapter);
    }


    @Override
    protected SortListPresenterImpl initPresenter() {
        return new SortListPresenterImpl();
    }

}
