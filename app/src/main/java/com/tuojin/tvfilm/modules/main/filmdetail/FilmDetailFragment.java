package com.tuojin.tvfilm.modules.main.filmdetail;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.FilmDetailBean;
import com.tuojin.tvfilm.contract.FilmDetailContract;
import com.tuojin.tvfilm.presenter.FilmDetailPresenterImpl;
import com.tuojin.tvfilm.utils.ImageLoaderUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmDetailFragment extends BaseFragment<FilmDetailContract.View, FilmDetailPresenterImpl> implements
        FilmDetailContract.View {

    FilmBean mFilm;
    @BindView(R.id.iv_filmpic_detail)
    ImageView mIvFilmpicDetail;
    @BindView(R.id.tv_filmname_detail)
    TextView mTvFilmnameDetail;
    @BindView(R.id.tv_filmtype_detail)
    TextView mTvFilmtypeDetail;
    @BindView(R.id.tv_director_detail)
    TextView mTvDirectorDetail;
    @BindView(R.id.tv_actors_detail)
    TextView mTvActorsDetail;
    @BindView(R.id.tv_desc_detail)
    TextView mTvDescDetail;
    @BindView(R.id.rv_film_detail)
    RecyclerView mRvFilmDetail;
    @BindView(R.id.appdetail_fragment)
    LinearLayout mAppdetailFragment;
    @BindView(R.id.btn_play)
    ImageButton mBtnPlay;
    private FilmDetailBean.DataBean.FilmDetailDataBean mBean;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_film_detail;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        mFilm = arguments.getParcelable("film");

    }

    @Override
    protected FilmDetailPresenterImpl initPresenter() {
        return new FilmDetailPresenterImpl();
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                ImageLoaderUtils.showRecommIcom(mActivity, mBean.getPoster(), mIvFilmpicDetail);
                mTvFilmnameDetail.setText(mBean.getMovie_name());
                mTvFilmtypeDetail.setText("电影类型：" + mBean.getType());
                mTvActorsDetail.setText("演员：" + mBean.getCast());
                mTvDirectorDetail.setText("导演：" + mBean.getDirector());
                mTvDescDetail.setText(mBean.getBrief());
                mBtnPlay.setFocusable(true);
            }
        }
    };

    //刷新界面
    @Override
    public void refresh(FilmDetailBean.DataBean.FilmDetailDataBean bean) {
        mBean = bean;
        mHandler.sendEmptyMessage(0);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume(mFilm.getMid(), mFilm.getUuid());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.btn_play)
    public void onClick() {
    }
}
