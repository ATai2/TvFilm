package com.tuojin.tvfilm.modules.main.filmdetail;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.FilmDetailBean;
import com.tuojin.tvfilm.contract.FilmDetailContract;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.OnItemClickListener;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.presenter.FilmDetailPresenterImpl;
import com.tuojin.tvfilm.utils.ImageLoaderUtils;

import java.util.List;

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
    Button mBtnPlay;
    @BindView(R.id.btn_stop)
    ImageButton mBtnStop;
    @BindView(R.id.tv_dbscore_detail)
    TextView mTvDbscoreDetail;
    @BindView(R.id.tv_dbscore_scor_detail)
    TextView mTvDbscoreScorDetail;
    @BindView(R.id.iv_back)
    ImageButton mIvBack;
    @BindView(R.id.title)
    TextView mTitle;
    private FilmDetailBean.DataBean.FilmDetailDataBean mBean;
    private Boolean isPlaying=false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_film_detail;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        mFilm = arguments.getParcelable("film");

        mTitle.setText("电影详情");
        LinearLayoutManager layout = new LinearLayoutManager(mActivity);
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvFilmDetail.setLayoutManager(layout);


        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPlaying){
                    Toast.makeText(mActivity, "播放影片", Toast.LENGTH_SHORT).show();
                    mPresenter.play(mBean);
                    mBtnPlay.setText("停止");
                }else {
                    Toast.makeText(mActivity, "停止播放影片", Toast.LENGTH_SHORT).show();
                    mPresenter.stop(mBean);
                    mBtnPlay.setText("播放");
                }
                isPlaying=!isPlaying;
            }
        });
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
                ImageLoaderUtils.showRecommIcom(mActivity, "/MID" + mBean.getPoster(), mIvFilmpicDetail);
                mTvFilmnameDetail.setText(mBean.getMovie_name());
                mTvFilmtypeDetail.setText("电影类型：" + mBean.getType() + "  地区：" + mBean.getMovieCountry() + "    年份：" + mBean.getPublishdate());
                mTvActorsDetail.setText("演员：" + mBean.getCast());
                mTvDirectorDetail.setText("导演：" + mBean.getDirector());
                mTvDbscoreScorDetail.setText(mBean.getScore());
                mTvDescDetail.setText(mBean.getBrief());
                mBtnPlay.setFocusable(true);

            } else if (msg.what == 1) {

                CommonAdapter<FilmBean> adapter = new CommonAdapter<FilmBean>(mActivity, R.layout.item_other, mList, 0) {
                    @Override
                    public void convert(ViewHolder holder, FilmBean bean) {
                        holder.setText(R.id.movie_title_other, bean.getMovie_name());
                        holder.setImageResource(R.id.movie_image_other, bean.getPoster());
                        holder.setScaleAnimation(R.id.movie_title_other);
                    }
                };
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                        FilmBean bean = mList.get(position);
                        mPresenter.onResume(bean.getMid(), bean.getUuid());
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    sleep(200L);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                mPresenter.initList();
                            }
                        }.start();
                    }
                });
                mRvFilmDetail.setAdapter(adapter);
            }
        }
    };

    //刷新界面
    @Override
    public void refresh(FilmDetailBean.DataBean.FilmDetailDataBean bean) {
        mBean = bean;
        mHandler.sendEmptyMessage(0);
//        mPresenter.
    }

    List<FilmBean> mList;

    @Override
    public void initListUI(List<FilmBean> mDatas) {
        mList = mDatas;
        mHandler.sendEmptyMessage(1);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume(mFilm.getMid(), mFilm.getUuid());
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(200L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mPresenter.initList();
            }
        }.start();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    boolean begin;

    @OnClick(R.id.iv_back)
    public void onClick() {
        mActivity.finish();
    }


}
