package com.tuojin.tvfilm.modules.catelist.live;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.bean.LiveBean;
import com.tuojin.tvfilm.contract.LiveContract;
import com.tuojin.tvfilm.presenter.LivePresenterImpl;
import com.tuojin.tvfilm.utils.ImageLoaderUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * 文 件 名: LiveActivity
 * 创 建 人: Administrator
 * 创建日期: 2016/10/13 11:20
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class LiveActivity extends BaseActivity<LiveContract.View, LivePresenterImpl>implements LiveContract.View {


    @BindView(R.id.iv_back)
    ImageButton mIvBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.jc_video)
    JCVideoPlayerStandard mJcVideo;
    @BindView(R.id.btn_start)
    Button mBtnStart;
    @BindView(R.id.btn_stop)
    Button mBtnStop;
    @BindView(R.id.tv_live_content)
    TextView mTvLiveContent;
    @BindView(R.id.tv_menutitle)
    TextView mTvMenutitle;
    @BindView(R.id.rv_menu)
    RecyclerView mRvMenu;
    @BindView(R.id.tab_container)
    LinearLayout mTabContainer;
    private List<LiveBean> mMenuList;
    TextView btn;
    @Override
    protected LivePresenterImpl initPresenter() {
        return new LivePresenterImpl();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTitle.setText("直播");

        mPresenter.menu();



        mJcVideo.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
        ImageLoaderUtils.showPictureForJCVideo(this, "http://cos.myqcloud.com/1000264/qcloud_video_attachment/842646334/vod_cover/cover1458036374.jpg", mJcVideo.thumbImageView);

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mJcVideo.onClick(mJcVideo.startButton);
            }
        });
        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mJcVideo.release();
            }
        });
//        mBtnFull.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mJcVideo.onClick(mJcVideo.fullscreenButton);
//            }
//        });
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_live;
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String msg) {

    }


    @OnClick(R.id.iv_back)
    public void onClick() {
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayerStandard.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();

    }

    @Override
    public void initMenu(List<LiveBean> mDatas) {
        mMenuList = mDatas;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRvMenu.setAdapter(new AtoZAdapter());
            }
        });
    }

    //菜单适配器
    class AtoZAdapter extends RecyclerView.Adapter<AtoZAdapter.ViewHolder> {
        @Override
        public AtoZAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(LiveActivity.this).inflate(R.layout.item_radbtn, parent, false);
            AtoZAdapter.ViewHolder holder = new AtoZAdapter.ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(AtoZAdapter.ViewHolder holder, int position) {

            holder.mRadbtnItem.setText(mMenuList.get(position).getLcname());
            if (position == 0) {
                holder.mRadbtnItem.requestFocus();
                btn = holder.mRadbtnItem;
//                mPresenter.list("A");
            }
            holder.mRadbtnItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn = (TextView) v;
//                    mPresenter.list(btn.getText().toString());
                }
            });
        }

        @Override
        public int getItemCount() {
            return mMenuList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.radbtn_item)
            TextView mRadbtnItem;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
