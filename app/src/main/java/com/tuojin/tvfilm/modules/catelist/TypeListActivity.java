package com.tuojin.tvfilm.modules.catelist;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.contract.FilmTypeContract;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.presenter.FilmTypePresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.R.attr.type;

/**
 * 文 件 名: TypeListActivity
 * 创 建 人: Administrator
 * 创建日期: 2016/9/29 13:57
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class TypeListActivity extends BaseActivity<FilmTypeContract.View, FilmTypePresenterImpl>
        implements FilmTypeContract.View {


    @BindView(R.id.index_type)
    TextView mIndexType;
    @BindView(R.id.main_fragment)
    RecyclerView mMainFragment;
    @BindView(R.id.tab_indicator_search)
    RadioButton mTabIndicatorSearch;
    @BindView(R.id.tab_indicator_action)
    RadioButton mTabIndicatorAction;
    @BindView(R.id.tab_indicator_fiction)
    RadioButton mTabIndicatorFiction;
    @BindView(R.id.tab_indicator_animation)
    RadioButton mTabIndicatorAnimation;
    @BindView(R.id.tab_indicator_suspense)
    RadioButton mTabIndicatorSuspense;
    @BindView(R.id.tab_indicator_emotional)
    RadioButton mTabIndicatorEmotional;
    @BindView(R.id.tab_indicator_comedy)
    RadioButton mTabIndicatorComedy;
    @BindView(R.id.tab_container)
    LinearLayout mTabContainer;
    private boolean aBoolean = true;
    private List<FilmBean> mData;
    private CommonAdapter<FilmBean> mAdapter;
    private int mType=5;

    @Override
    protected FilmTypePresenterImpl initPresenter() {
        return new FilmTypePresenterImpl();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
//        fragmentManager = getSupportFragmentManager();
//        showFragment(1);
        mPresenter.attach(this);
        mMainFragment.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));

        mData = new ArrayList<>();

        mTabIndicatorAction.requestFocus();
        mPresenter.onClick(5);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_filmtype;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mMainFragment.hasFocus()&&keyCode==event.KEYCODE_DPAD_LEFT){
            switch (type){
                case 5:
                    mMainFragment.findFocus().setNextFocusLeftId(R.id.tab_indicator_action);
                    break;
                case 3:
                    mMainFragment.findFocus().setNextFocusLeftId(R.id.tab_indicator_fiction);
                    break;
                case 4:
                    mMainFragment.findFocus().setNextFocusLeftId(R.id.tab_indicator_animation);
                    break;
            }
        }

        return super.onKeyDown(keyCode, event);
    }


    @OnClick({R.id.tab_indicator_search, R.id.tab_indicator_action, R.id.tab_indicator_fiction, R.id.tab_indicator_animation, R.id.tab_indicator_suspense, R.id.tab_indicator_emotional, R.id.tab_indicator_comedy})
    public void onClick(View view) {
        String index = "";
        switch (view.getId()) {
            case R.id.tab_indicator_action:
                mType = 5;
                index = "动作";
                break;
            case R.id.tab_indicator_fiction:
                mType = 3;
                index = "科幻";
                break;
            case R.id.tab_indicator_animation:
                mType = 4;
                index = "动画";
                break;
            case R.id.tab_indicator_suspense:
                mType = 13;
                index = "悬疑";
                break;
            case R.id.tab_indicator_emotional:
                mType = 1;
                index = "情感";
                break;
            case R.id.tab_indicator_comedy:
                mType = 2;
                index = "喜剧";
                break;
        }
        mIndexType.setText(index);
        mPresenter.onClick(mType);
//        mHandler.sendEmptyMessage(200);
//        mPresenter.
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 200:
                    mAdapter = new CommonAdapter<FilmBean>(TypeListActivity.this, R.layout.item_other, mData, 0) {
                        @Override
                        public void convert(ViewHolder holder, FilmBean bean) {
                            holder.setText(R.id.movie_title_other, bean.getMovie_name());
                            holder.setImageResource(R.id.movie_image_other, bean.getPoster());
                            holder.setScaleAnimation(R.id.movie_title_other);
                        }
                    };
                    mMainFragment.setAdapter(mAdapter);
            }
        }
    };


    public static boolean isRefresh;   //判断RadioButton获取焦点时是否刷新

    @Override
    public void initFilmFragment(List<FilmBean> data) {
        mData = data;
        mHandler.sendEmptyMessage(200);
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
}
