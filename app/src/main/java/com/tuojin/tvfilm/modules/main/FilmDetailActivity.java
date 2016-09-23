package com.tuojin.tvfilm.modules.main;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.base.BaseView;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.FilmDetailBean;
import com.tuojin.tvfilm.contract.FilmDetailContract;
import com.tuojin.tvfilm.modules.main.filmdetail.FilmDetailFragment;
import com.tuojin.tvfilm.presenter.FilmDetailPresenterImpl;
import com.tuojin.tvfilm.utils.ImageLoaderUtils;

import butterknife.BindView;

/**
 * 文 件 名: FilmDetailActivity
 * 创 建 人: Administrator
 * 创建日期: 2016/9/22 13:17
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class FilmDetailActivity extends BaseActivity<FilmDetailContract.View, FilmDetailPresenterImpl> implements FilmDetailContract.View,BaseView {

    FilmDetailFragment mDetailFragment;
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
    private FilmBean mFilm;

    @Override
    protected FilmDetailPresenterImpl initPresenter() {
        return new FilmDetailPresenterImpl();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mFilm = intent.getParcelableExtra("film");
    }

    @Override
    protected void initView() {


    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_film_detail;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume(mFilm.getMid(), mFilm.getUuid());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String msg) {

    }

    //刷新界面
    @Override
    public void refresh(FilmDetailBean.DataBean.FilmDetailDataBean bean) {
        ImageLoaderUtils.showRecommIcom(this,bean.getPoster(),mIvFilmpicDetail);
        mTvFilmnameDetail.setText(bean.getMovie_name());
        mTvActorsDetail.setText("演员："+bean.getCast());
        mTvDirectorDetail.setText("导演："+bean.getDirector());
        mTvDescDetail.setText(bean.getBrief());
    }

}
