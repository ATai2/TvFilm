package com.tuojin.tvfilm.modules.catelist.live;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.bean.LiveBean;
import com.tuojin.tvfilm.bean.LiveContentBean;
import com.tuojin.tvfilm.contract.LiveContract;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.presenter.LivePresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

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
public class LiveActivity extends BaseActivity<LiveContract.View, LivePresenterImpl> implements LiveContract.View {


    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.video_container)
    LinearLayout mVideoContainer;
    @BindView(R.id.tv_menutitle)
    TextView mTvMenutitle;
    @BindView(R.id.rv_menu)
    RecyclerView mRvMenu;
    @BindView(R.id.tab_container)
    LinearLayout mTabContainer;
    @BindView(R.id.iv_back)
    ImageButton mIvBack;
    @BindView(R.id.title_topbar)
    TextView mTitleTopbar;
    private List<LiveBean> mMenuList;
    TextView btn;
    private String[] mTnames;
    private String[] mUrls;
    LiveContentBean mLiveContentBean;
    boolean flag;

    @Override
    protected LivePresenterImpl initPresenter() {
        return new LivePresenterImpl();
    }

    @Override
    protected void initData() {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mRecyclerview.hasFocus() && keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            if (btn != null) {
                btn.requestFocus();
            }
        }
        if (mRvMenu.hasFocus() && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            mRecyclerview.requestFocus();
        }
//        if (mBtnStop.hasFocus() && keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
//            //判断哪个获得焦点
//            if (btn != null)
//                btn.requestFocus();
//        }
//        if (mRvMenu.hasFocus() && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
//            mBtnStart.requestFocus();
//        }
//        if ((mBtnStart.hasFocus() || mBtnStop.hasFocus()) && keyCode == KeyEvent.KEYCODE_DPAD_UP) {
//            mIvBack.requestFocus();
//        }
//        if (mIvBack.hasFocus() && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
//            mBtnStart.requestFocus();
//        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void initView() {
        mPresenter.attach(this);
        mTitleTopbar.setText("直播");
        mTvMenutitle.setText("直播列表");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        LinearLayoutManager layoutManagerContent = new LinearLayoutManager(this);
        layoutManagerContent.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(layoutManagerContent);

        mRvMenu.setLayoutManager(layoutManager);
//        mJcVideo.setFocusable(false);
        mPresenter.menu();





//        mTnames = new String[]{
//                "NBA",
//                "英超",
//                "欧冠",
//                "西甲",
//                "中超",
//                "法网",
//                "CBA",
//                "测试"
//        };
//        mUrls = new String[]{
//                "http://live01.hebtv.com/channels/hebtv/video_channel_04/ts:800k/live",
//                "http://zv.3gv.ifeng.com/live/zhongwen800k.m3u8",
//                "http://112.253.38.62:80/livehls1-cnc.wasu.cn/hd_zjws/z.m3u8",
//                "http://112.253.38.62:80/livehls1-cnc.wasu.cn/hd_dfws/z.m3u8",
//                "http://112.253.38.62:80/livehls1-cnc.wasu.cn/hd_jsws/z.m3u8",
//                "http://zv.3gv.ifeng.com/live/zhongwen800k.m3u8",
//                "http://zv.3gv.ifeng.com/live/zhongwen800k.m3u8",
//                "http://zv.3gv.ifeng.com/live/zhongwen800k.m3u8"
//        };

//        mJcVideo.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"
//                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
//        mJcVideo.WIFI_TIP_DIALOG_SHOWED = true;
//        ImageLoaderUtils.showPictureForJCVideo(this, "http://cos.myqcloud.com/1000264/qcloud_video_attachment/842646334/vod_cover/cover1458036374.jpg", mJcVideo.thumbImageView);
//
//        mBtnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               mJcVideo.onClick(mJcVideo.startButton);
//            }
//        });
//        mBtnFullScreen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LiveActivity.this, FullScreenLiveActivity.class);
//                intent.putExtra("url", mLiveContentBean.getLturl());
//                intent.putExtra("pic", mLiveContentBean.getLcimgurl());
//                startActivityForResult(intent,0);
//            }
//        });
//        mBtnFullScreen2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                MyJCVideoPlayerStandard.startFullscreen(LiveActivity.this,MyJCVideoPlayerStandard.class,mLiveContentBean.getLturl());
//                mJcVideo.startWindowFullscreen();
//            }
//        });
//        mBtnStop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mJcVideo.release();
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == 1) {
            flag = true;
        }
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
//        JCVideoPlayerStandard.releaseAllVideos();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (mLiveContentBean!=null&&mJcVideo!=null&&flag){
//            mJcVideo.onClick(mJcVideo.startButton);
//        }
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

    @Override
    public void initList(final List<LiveContentBean> mDatas) {
//        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) mDatas);
//        LiveDialogFragment dialogFragment = new LiveDialogFragment();
//        dialogFragment.setArguments(bundle);
//        dialogFragment.show(getFragmentManager(), "直播列表");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CommonAdapter<LiveContentBean> adapter = new CommonAdapter<LiveContentBean>(LiveActivity.this,
                        R.layout.item_live_content2, mDatas, 1) {
                    @Override
                    public void convert(ViewHolder holder, final LiveContentBean bean) {
                        holder.setText(R.id.tv_title, bean.getLivecontent());
                        holder.setText(R.id.tv_time, bean.getOpentime().substring(5, bean.getOpentime().length()));
                        holder.setText(R.id.tv_timeend, bean.getEndtime().substring(5, bean.getOpentime().length()));
                        holder.setOnClickListener(R.id.btn_playlive, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(LiveActivity.this,FullScreenLiveActivity.class);
                                intent.putExtra("url", bean.getLturl());
                                startActivity(intent);
                            }
                        });
                    }
                };
                mRecyclerview.setAdapter(adapter);
            }
        });
    }

    //菜单适配器
    class AtoZAdapter extends RecyclerView.Adapter<AtoZAdapter.ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(LiveActivity.this).inflate(R.layout.item_radbtn, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final int itempos = position;
            holder.mRadbtnItem.setText(mMenuList.get(position).getLcname());
//            holder.mRadbtnItem.findFocus().setNextFocusRightId(R.id.btn_start);
            if (position == 0) {
                holder.mRadbtnItem.requestFocus();
                btn = holder.mRadbtnItem;
                mPresenter.list(btn.getText().toString());
            }
            holder.mRadbtnItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn = (TextView) v;
                    final String text = btn.getText().toString();
                    mTitleTopbar.setText(text);

                    mPresenter.list(text);
//                    LiveDialogFragment dialogFragment = new LiveDialogFragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("key", text);
//                    dialogFragment.setArguments(bundle);
//                    dialogFragment.show(getFragmentManager(), "直播列表");
//                    dialogFragment.setOnBtnStartClick(new LiveDialogFragment.OnBtnStartClick() {
//                        @Override
//                        public void onClick(LiveContentBean bean) {
//                            mLiveContentBean = bean;
//                            mJcVideo.setUp(bean.getLturl()
//                                    , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, text);
//                            ImageLoaderUtils.showPictureForJCVideo(LiveActivity.this, "http://cos.myqcloud.com/1000264/qcloud_video_attachment/842646334/vod_cover/cover1458036374.jpg", mJcVideo.thumbImageView);
//                            mJcVideo.onClick(mJcVideo.startButton);
//                            mBtnStart.requestFocus();
//                        }
//                    });
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
