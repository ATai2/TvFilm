package com.tuojin.tvfilm.modules.main.sortlist;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.RecommBean;
import com.tuojin.tvfilm.contract.SortListContract;
import com.tuojin.tvfilm.event.FilmAdEvent;
import com.tuojin.tvfilm.event.FilmBigEvent;
import com.tuojin.tvfilm.event.FilmDoubanEvent;
import com.tuojin.tvfilm.event.FilmHotEvent;
import com.tuojin.tvfilm.event.FilmNewEvent;
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

public class SortActivity extends BaseActivity<SortListContract.View, SortListPresenterImpl> implements SortListContract.View, View.OnFocusChangeListener {

    @BindView(R.id.index_type)
    TextView mIndexType;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.tab_indicator_new)
    RadioButton mTabIndicatorNew;
    @BindView(R.id.tab_indicator_hot)
    RadioButton mTabIndicatorHot;
    @BindView(R.id.tab_indicator_big)
    RadioButton mTabIndicatorBig;
    @BindView(R.id.tab_indicator_ad)
    RadioButton mTabIndicatorAd;
    @BindView(R.id.tab_indicator_douban)
    RadioButton mTabIndicatorDouban;
    @BindView(R.id.tab_container)
    LinearLayout mTabContainer;
    @BindView(R.id.iv_back)
    ImageButton mIvBack;

    public static boolean isRefresh;   //判断RadioButton获取焦点时是否刷新
    private RadioButton mCurrentRadioButton;
    private FocusGridLayoutManager mGridLayoutManager;
    private CommonAdapter<FilmBean> mOtherAdapter;
    private FilmBean mValue;
    private List<FilmBean> mFilmBeen;


    @Override
    protected SortListPresenterImpl initPresenter() {
        return new SortListPresenterImpl();
    }

    public SortActivity getInstance() {
        return new SortActivity();
    }

    @Override
    protected void initData() {
        int position = getIntent().getIntExtra("position", 0);
        String str = "";
        switch (position) {
            case 0:
                mTabIndicatorNew.requestFocus();
                str = "最新";
                break;
            case 1:
                mTabIndicatorHot.requestFocus();
                str = "最热";
                break;
            case 2:
                mTabIndicatorBig.requestFocus();
                str = "大片";
                break;
            case 3:
                mTabIndicatorAd.requestFocus();
                str = "广告";
                break;
            case 4:
                mTabIndicatorDouban.requestFocus();
                str = "豆瓣";
                break;
        }
        showList(position);
        mIndexType.setText(str);
    }

    @Override
    protected void initView() {
        mPresenter.attach(this);
        mGridLayoutManager = new FocusGridLayoutManager(mActivity, 5);
        mGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(mGridLayoutManager);

        getOnFocus();

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortActivity.this.finish();
            }
        });
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
    public void onMessageEvent(FilmHotEvent event) {
        String msg = event.msg;
        initList(msg);
    }

    private void initList(String msg) {
        List<FilmBean> beanList = new Gson().fromJson(msg, RecommBean.class).getData().getData();
        mFilmBeen=beanList;
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
                intent.putExtra("big", mCurrentRadioButton.getText().toString().equals("大片") ? true : false);
                startActivity(intent);
            }
        });
        mRecyclerview.setAdapter(mOtherAdapter);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_sort;
    }

    @Override
    public void showLoading() {
//        mActivity.showLoading();
    }

    @Override
    public void hideLoading() {
//        mActivity.hideLoading();
    }

    @Override
    public void showMessage(String msg) {

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        View focusedChild = mRecyclerview.getFocusedChild();
        int childLayoutPosition = mRecyclerview.getChildLayoutPosition(focusedChild);
        if (mRecyclerview.hasFocus() && keyCode == KeyEvent.KEYCODE_DPAD_LEFT&& (childLayoutPosition % 5 == 0 || childLayoutPosition % 5 == 5)) {
            //判断哪个获得焦点
            mCurrentRadioButton.requestFocus();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void getOnFocus() {
        mTabIndicatorNew.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mCurrentRadioButton = (RadioButton) v;
                    showList(0);
                    mIndexType.setText("最新");
                }
            }
        });
        mTabIndicatorHot.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showList(1);
                    mCurrentRadioButton = (RadioButton) v;
                    mIndexType.setText("最热");

                }
            }
        });
        mTabIndicatorBig.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showList(2);
                    mCurrentRadioButton = (RadioButton) v;
                    mIndexType.setText("大片");

                }
            }
        });
        mTabIndicatorAd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showList(3);
                    mCurrentRadioButton = (RadioButton) v;
                    mIndexType.setText("广告");
                }
            }
        });
        mTabIndicatorDouban.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showList(4);
                    mCurrentRadioButton = (RadioButton) v;
                    mIndexType.setText("豆瓣");
                }
            }
        });

    }

    private void showList(int i) {
        mPresenter.onResume(i, 0);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            mCurrentRadioButton = (RadioButton) v;
        }
    }
}
