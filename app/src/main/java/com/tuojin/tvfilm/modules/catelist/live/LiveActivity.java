package com.tuojin.tvfilm.modules.catelist.live;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.bean.LiveBean;
import com.tuojin.tvfilm.bean.LiveContentBean;
import com.tuojin.tvfilm.bean.LiveContentListBean;
import com.tuojin.tvfilm.bean.LiveListBean;
import com.tuojin.tvfilm.contract.LiveContract;
import com.tuojin.tvfilm.event.LiveContentEvent;
import com.tuojin.tvfilm.event.LiveListEvent;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.presenter.LivePresenterImpl;
import com.tuojin.tvfilm.utils.LiveUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        mPresenter.menu();
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
//        if (JCVideoPlayer.backPress()) {
//            return;
//        }
        super.onBackPressed();

    }

    @Override
    public void initMenu(List<LiveBean> mDatas) {
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LiveListEvent event) {
        List<LiveBean> list = new Gson().fromJson(event.msg, LiveListBean.class).getData().getData();
        mMenuList = list;
        mRvMenu.setAdapter(new AtoZAdapter());
    }

    /**
     * 直播内容
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LiveContentEvent event) {
        List<LiveContentBean> liveContentBeen = new Gson().fromJson(event.msg, LiveContentListBean.class).getData().getData();
        CommonAdapter<LiveContentBean> adapter = new CommonAdapter<LiveContentBean>(LiveActivity.this,
                R.layout.item_live_content2, liveContentBeen, 1) {
            @Override
            public void convert(ViewHolder holder, final LiveContentBean bean) {
                holder.setText(R.id.tv_title, bean.getLivecontent());
                holder.setText(R.id.tv_time, bean.getOpentime().substring(5, bean.getOpentime().length()));
                holder.setText(R.id.tv_timeend, bean.getEndtime().substring(5, bean.getOpentime().length()));
                holder.setOnClickListener(R.id.btn_playlive, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        mPresenter.playLive(bean);
//                        Intent intent=new Intent(LiveActivity.this,FullScreenLiveActivity.class);
                        Intent intent=new Intent(LiveActivity.this,MP4Activity.class);
                        String lturl = bean.getLturl();
                        Log.d("abs", lturl);
                        String key = "k2hrwtgk0wybzdysm2sbl8";
                        String url = LiveUtil.getLiveFinalUrl(lturl, key);
                        Log.d("abs", url);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                });
            }
        };
        mRecyclerview.setAdapter(adapter);
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
