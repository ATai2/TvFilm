package com.tuojin.tvfilm.modules.catelist.time;

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
import com.tuojin.tvfilm.keybord.FocusGridLayoutManager;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.OnItemClickListener;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.modules.main.FilmDetailActivity;
import com.tuojin.tvfilm.presenter.SortListPresenterImpl;

import java.util.List;

import butterknife.BindView;

/**
 * 文 件 名: FilmListFragment
 * 创 建 人: Administrator
 * 创建日期: 2016/10/11 18:10
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class FilmListFragment  extends BaseFragment<SortListContract.View, SortListPresenterImpl> implements SortListContract.View {
    @BindView(R.id.recyclerview)
    public RecyclerView mRecyclerview;
    private FocusGridLayoutManager mGridLayoutManager;
    private List<FilmBean> mFilmBeen;
    private static CommonAdapter mOtherAdapter;
    private FilmBean mValue;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_other;
    }

    @Override
    protected void initView() {
        String json = getArguments().getString("json");
        mFilmBeen = new Gson().fromJson(json, RecommBean.class).getData().getData();

        mGridLayoutManager = new FocusGridLayoutManager(mActivity, 5);
        mGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(mGridLayoutManager);



        mOtherAdapter = new CommonAdapter<FilmBean>(mActivity, R.layout.item_other, mFilmBeen, 1) {
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
                startActivity(intent);
            }
        });
        mRecyclerview.setAdapter(mOtherAdapter);
    }

    @Override
    protected SortListPresenterImpl initPresenter() {
        return new SortListPresenterImpl();
    }

    @Override
    public void setRecyclerItem(List<FilmBean> mList) {

    }

    @Override
    public void refreshUI() {

    }
}
