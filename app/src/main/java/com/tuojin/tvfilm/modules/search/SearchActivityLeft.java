package com.tuojin.tvfilm.modules.search;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.litesuits.orm.LiteOrm;
import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.base.BaseApplication;
import com.tuojin.tvfilm.bean.ErrorBean;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.LiteFilmBean;
import com.tuojin.tvfilm.bean.RecommBean;
import com.tuojin.tvfilm.contract.SearchContract;
import com.tuojin.tvfilm.event.FilmPlayRefreshSearchEvent;
import com.tuojin.tvfilm.event.KeyWordEvent;
import com.tuojin.tvfilm.event.SearchHotEvent;
import com.tuojin.tvfilm.event.SearchNoListEvent;
import com.tuojin.tvfilm.keybord.FocusGridLayoutManager;
import com.tuojin.tvfilm.keybord.KeyboardIME;
import com.tuojin.tvfilm.keybord.SimpleKeyBoardView;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.OnItemClickListener;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.modules.main.FilmDetailActivity;
import com.tuojin.tvfilm.presenter.SearchPresenterImpl;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 文 件 名: SearchActivity
 * 创 建 人: Administrator
 * 创建日期: 2016/9/23 13:08
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class SearchActivityLeft extends BaseActivity<SearchContract.View, SearchPresenterImpl> implements SearchContract.View {


    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.iv_back)
    ImageButton mIvBack;
    @BindView(R.id.title_topbar)
    TextView mTitleTopbar;
    @BindView(R.id.tv_history)
    TextView mTvHistory;
    @BindView(R.id.tv_history_clear)
    TextView mTvHistoryClear;
    @BindView(R.id.iv_left_history)
    ImageView mIvLeftHistory;
    @BindView(R.id.rv_history)
    RecyclerView mRvHistory;
    @BindView(R.id.iv_right_history)
    ImageView mIvRightHistory;
    @BindView(R.id.rl_search)
    RelativeLayout mRlSearch;
    @BindView(R.id.first_search_text)
    TextView mFirstSearchText;
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.ll_init)
    LinearLayout mLlInit;
    @BindView(R.id.tv_result)
    TextView mTvResult;
    @BindView(R.id.recyclerview_search)
    RecyclerView mRecyclerviewSearch;
    @BindView(R.id.ll_search)
    LinearLayout mLlSearch;
    @BindView(R.id.edit_search)
    EditText mEditSearch;
    @BindView(R.id.keyboard_view)
    SimpleKeyBoardView mKeyboardView;
    @BindView(R.id.left_menu)
    LinearLayout mLeftMenu;
    @BindView(R.id.rl_container)
    LinearLayout mRlContainer;
    @BindView(R.id.ll_search_whole)
    FrameLayout mLlSearchWhole;
    private SimpleDateFormat mDf;
    //    private Fragment mSearchFrag;
    private SearchFragmentLeft mSearchFrag;

    private String mKey = "";
    private List<FilmBean> mList = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private CommonAdapter<FilmBean> mAdapter;
    private SharedPreferences mHistory;
    private SharedPreferences.Editor mEdit;
    private Set<String> mHistoryList1;
    private CommonAdapter<String> mCommonAdapter;
    private LinearLayoutManager mLayoutManagerHistory;
    private LiteOrm mLiteOrm;
    private List<FilmBean> mFilmBeanListHistory;
    private CommonAdapter<FilmBean> mHistoryAdapter;
    private CommonAdapter<FilmBean> mOtherAdapter;
    private FilmBean mValue;
    private List<String> mMenuList;
    private LinearLayoutManager mLayoutManagerl;

    @Override
    protected SearchPresenterImpl initPresenter() {
        return new SearchPresenterImpl();
    }

    @Override
    protected void initData() {
        mDf = new SimpleDateFormat("MM-dd EEEE HH:mm:ss");
        Timer timer = new Timer();
        timer.schedule(new MyTimer(), 1000, 1000);

//        List<Fragment> fragments = getSupportFragmentManager().getFragments();
//        mSearchFrag = fragments.get(0);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (mSearchFrag != null) {
            mSearchFrag.onKeyUp(keyCode, event);
        }

        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mSearchFrag != null) {
            mSearchFrag.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
    }

    class MyTimer extends TimerTask {

        @Override
        public void run() {
            final String time = mDf.format(new Date());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTvTime.setText(time);
                }
            });

        }
    }



    @Override
    public int getLayoutID() {
        return R.layout.activity_search_frag;
    }

    @Override
    protected void initView() {
        mTitleTopbar.setText("搜索");
//        EventBus.getDefault().register(this);

        InputMethodManager imm = (InputMethodManager)mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditSearch.getWindowToken(), 0);
        new KeyboardIME(mActivity,mActivity,mEditSearch,mRecyclerview,mRecyclerviewSearch);


        mEditSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mFirstSearchText.setText("搜索影片");
                mEnterOnTextChanged(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mLayoutManager = new LinearLayoutManager(mActivity);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(mLayoutManager);

        FocusGridLayoutManager layoutManager = new FocusGridLayoutManager(mActivity, 5);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerviewSearch.setHasFixedSize(true);
        mRecyclerviewSearch.setLayoutManager(layoutManager);

        mLayoutManagerHistory = new LinearLayoutManager(mActivity);
        mLayoutManagerHistory.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvHistory.setHasFixedSize(true);
        mRvHistory.setLayoutManager(mLayoutManagerHistory);

        mLiteOrm = ((BaseApplication) mActivity.getApplication()).mLiteOrm;
        mFilmBeanListHistory = new ArrayList<>();

        mTvHistoryClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLiteOrm.deleteAll(LiteFilmBean.class);
                dblist();
            }
        });

        dblist();
        mPresenter.hotSearch();
        addListner();
        mEditSearch.requestFocus();
    }

    private void addListner() {
        mKeyboardView.setOnLitener(new SimpleKeyBoardView.OnLitener() {
            @Override
            public void onClick(Editable editable) {
                setE(editable);
            }
        });
//        mBtnClear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditSearch.setText("");
//            }
//        });
//        mLlSearchWhole.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
//            @Override
//            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
//                if (oldFocus instanceof TextView)
//            }
//        });

    }

    private void setE(Editable editable) {
        mLlSearch.setVisibility(View.VISIBLE);
        mLlInit.setVisibility(View.GONE);
        if (editable.toString().equals("")) {
//            mPresenter.onResume(code, ONE);
        } else {
            mPresenter.search(editable.toString());
        }
        mTvResult.setText("\"");
        mTvResult.append(editable);
        mTvResult.append("\"");
        mTvResult.append("  的搜索结果有");


    }


    /**
     * 点击播放后，更新播放列表
     *
     * @param event
     */
    @Subscribe
    public void onMessageEvent(FilmPlayRefreshSearchEvent event) {
        Log.d("play", event.msg.toString());
        // mFilmBeanListHistory.add(0, event.msg);
        dblist();
    }

    /**
     * 热门搜索
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SearchHotEvent event) {
        String msg = event.msg;
        List<FilmBean> mDatas = new Gson().fromJson(msg, RecommBean.class).getData().getData();
        fillList(mDatas);
    }

    private void fillList(List<FilmBean> mDatas) {
        mList = mDatas;
        mOtherAdapter = new CommonAdapter<FilmBean>(mActivity, R.layout.item_other_linear, mList, 2) {
            @Override
            public void convert(ViewHolder holder, FilmBean bean) {
                holder.setText(R.id.movie_title_other, bean.getMovie_name());
                holder.setImageResource(R.id.movie_image_other, bean.getPoster());
                holder.setScaleAnimation(R.id.movie_title_other);
                holder.setOnTextFocusChangeListner(R.id.rl_container, R.id.movie_title_other, R.id.movie_image_other, new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            v.setVisibility(View.VISIBLE);
                        } else {
                            v.setVisibility(View.INVISIBLE);
                        }
                    }
                });

            }
        };
        mOtherAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Intent intent = new Intent(mActivity, FilmDetailActivity.class);
                mValue = mList.get(position);
                intent.putExtra("film", mValue);
                startActivity(intent);
            }
        });
        mRecyclerview.setAdapter(mOtherAdapter);
        mRecyclerview.requestFocus();
    }

    private void dblist() {
        mFilmBeanListHistory.clear();
        List<LiteFilmBean> list = mLiteOrm.query(LiteFilmBean.class);
        if (list != null) {
            for (LiteFilmBean bean :
                    list) {
                String filmBean = bean.getFilmBean();
                FilmBean bean1 = new Gson().fromJson(filmBean, FilmBean.class);
                mFilmBeanListHistory.add(bean1);

//                mFilmBeanListHistory.
            }
            Collections.reverse(mFilmBeanListHistory);
        }
        mHistoryAdapter = new CommonAdapter<FilmBean>(mActivity, R.layout.item_other_linear, mFilmBeanListHistory, 1) {
            @Override
            public void convert(ViewHolder holder, FilmBean bean) {
//                holder.setText(R.id.movie_title_other, bean.getMovie_name());
//                holder.setImageResource(R.id.movie_image_other, bean.getPoster());
//                holder.setScaleAnimation(R.id.movie_title_other);
                holder.setText(R.id.movie_title_other, bean.getMovie_name());
                holder.setImageResource(R.id.movie_image_other, bean.getPoster());
                holder.setScaleAnimation(R.id.movie_title_other);
                holder.setOnTextFocusChangeListner(R.id.rl_container, R.id.movie_title_other, R.id.movie_image_other, new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            v.setVisibility(View.VISIBLE);
                        } else {
                            v.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        };
        mHistoryAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Intent intent = new Intent(mActivity, FilmDetailActivity.class);
                FilmBean bean = mFilmBeanListHistory.get(position);
                intent.putExtra("film", bean);
                startActivity(intent);
            }
        });
        mRvHistory.setAdapter(mHistoryAdapter);
    }

    /**
     * 关键字搜索
     *
     * @param s
     */
    private void mEnterOnTextChanged(CharSequence s) {
        mKey = s.toString();
        if (!TextUtils.isEmpty(s)) {
            mList.clear();
            mPresenter.search(mKey);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SearchNoListEvent event) {
        String msg = new Gson().fromJson(event.msg, ErrorBean.class).getData().getMsg();
        if (msg.equals("无数据")) {
            mFirstSearchText.setText(msg);
            mRecyclerview.removeAllViews();
            mList = new ArrayList<>();
            mAdapter.notifyDataSetChanged();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(KeyWordEvent event) {
        String msg = event.msg;
        List<FilmBean> beanList = new Gson().fromJson(msg, RecommBean.class).getData().getData();
        mList = beanList;
        if (mList == null || mList.size() == 0) {
            Toast.makeText(mActivity, "查无结果！", Toast.LENGTH_SHORT).show();
            mFirstSearchText.setText("搜索影片");
            hideLoading();
            return;
        } else {
            mFirstSearchText.setText("搜索到" + mList.size() + "部影片");

            mAdapter = new CommonAdapter<FilmBean>(mActivity, R.layout.item_other_linear, mList, 1) {
                @Override
                public void convert(ViewHolder holder, FilmBean bean) {
//                    holder.setText(R.id.movie_title_other, bean.getMovie_name());
//                    holder.setImageResource(R.id.movie_image_other, bean.getPoster());
//                    holder.setScaleAnimation(R.id.movie_title_other);
                    holder.setText(R.id.movie_title_other, bean.getMovie_name());
                    holder.setImageResource(R.id.movie_image_other, bean.getPoster());
                    holder.setScaleAnimation(R.id.movie_title_other);
                    holder.setOnTextFocusChangeListner(R.id.rl_container, R.id.movie_title_other, R.id.movie_image_other, new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (hasFocus) {
                                v.setVisibility(View.VISIBLE);
                            } else {
                                v.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
            };
            mAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                    Intent intent = new Intent(mActivity, FilmDetailActivity.class);
                    FilmBean bean = mList.get(position);
                    intent.putExtra("film", bean);
                    startActivity(intent);
                }
            });
            mRecyclerviewSearch.setAdapter(mAdapter);
        }
        hideLoading();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void refreshUI(List<FilmBean> beanList) {

    }
}
