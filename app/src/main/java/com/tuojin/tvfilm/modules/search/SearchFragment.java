package com.tuojin.tvfilm.modules.search;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.litesuits.orm.LiteOrm;
import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseApplication;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.LiteFilmBean;
import com.tuojin.tvfilm.bean.RecommBean;
import com.tuojin.tvfilm.contract.SearchContract;
import com.tuojin.tvfilm.event.FilmPlayRefreshSearchEvent;
import com.tuojin.tvfilm.event.KeyWordEvent;
import com.tuojin.tvfilm.event.SearchHotEvent;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.OnItemClickListener;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.modules.main.FilmDetailActivity;
import com.tuojin.tvfilm.presenter.SearchPresenterImpl;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

/**
 * 文 件 名: SearchFragment
 * 创 建 人: Administrator
 * 创建日期: 2016/10/9 14:56
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class SearchFragment extends BaseFragment<SearchContract.View, SearchPresenterImpl> implements SearchContract.View {

    @BindView(R.id.first_search_text)
    TextView mFirstSearchText;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.edit_search)
    EditText mEditSearch;
    @BindView(R.id.left_menu)
    RelativeLayout mLeftMenu;
    @BindView(R.id.tv_history)
    TextView mTvHistory;
    @BindView(R.id.rv_history)
    RecyclerView mRvHistory;
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.tv_history_clear)
    TextView mTvHistoryClear;
    @BindView(R.id.ll_search_whole)
    LinearLayout mLlSearchWhole;
    @BindView(R.id.iv_left_history)
    ImageView mIvLeftHistory;
    @BindView(R.id.iv_right_history)
    ImageView mIvRightHistory;
    @BindView(R.id.iv_back)
    ImageButton mIvBack;
    @BindView(R.id.title_topbar)
    TextView mTitleTopbar;

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


    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        mTitleTopbar.setText("搜索");
//        EventBus.getDefault().register(this);
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
//        mLlSearchWhole.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
//            @Override
//            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
//                if (oldFocus instanceof TextView)
//            }
//        });

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SearchHotEvent event) {
        String msg = event.msg;
        List<FilmBean> mDatas = new Gson().fromJson(msg, RecommBean.class).getData().getData();
        fillList(mDatas);
    }

    private void fillList(List<FilmBean> mDatas) {
        mList = mDatas;
        mOtherAdapter = new CommonAdapter<FilmBean>(mActivity, R.layout.item_other_recomm, mList, 2) {
            @Override
            public void convert(ViewHolder holder, FilmBean bean) {
                holder.setText(R.id.movie_title_other, bean.getMovie_name());
                holder.setText(R.id.movie_title_other_score, bean.getScore());
                holder.setImageResource(R.id.movie_image_other, bean.getPoster());
                holder.setScaleAnimation(R.id.movie_title_other);

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

            mAdapter = new CommonAdapter<FilmBean>(mActivity, R.layout.item_other_linear, mList, 2) {
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
            mRecyclerview.setAdapter(mAdapter);
        }
        hideLoading();
    }


    @Override
    protected SearchPresenterImpl initPresenter() {
        return new SearchPresenterImpl();
    }

    /**
     * 处理activity的onKeyUp事件
     *
     * @param keyCode
     * @param event
     */
    public void onKeyUp(int keyCode, KeyEvent event) {

        if (mRvHistory.hasFocus() && (((LinearLayoutManager) mRecyclerview.getLayoutManager()).findFirstVisibleItemPosition() == 0)) {

        }
        if (mList != null && mList.size() != 0) {
            if ((((LinearLayoutManager) mRecyclerview.getLayoutManager()).findFirstVisibleItemPosition() == 0
            )) {
                mIvLeft.setVisibility(View.INVISIBLE);
            } else {
                mIvLeft.setVisibility(View.VISIBLE);

            }

            if ((((LinearLayoutManager) mRecyclerview.getLayoutManager()).findLastVisibleItemPosition() == mList.size() - 1
            )) {
                mIvRight.setVisibility(View.INVISIBLE);
            } else {
                mIvRight.setVisibility(View.VISIBLE);
            }

        }
        if (mFilmBeanListHistory != null && mFilmBeanListHistory.size() != 0) {
            if ((((LinearLayoutManager) mRvHistory.getLayoutManager()).findFirstVisibleItemPosition() == 0
            )) {
                mIvLeftHistory.setVisibility(View.INVISIBLE);
            } else {
                mIvLeftHistory.setVisibility(View.VISIBLE);

            }

            if ((((LinearLayoutManager) mRvHistory.getLayoutManager()).findLastVisibleItemPosition() == mFilmBeanListHistory.size() - 1
            )) {
                mIvRightHistory.setVisibility(View.INVISIBLE);
            } else {
                mIvRightHistory.setVisibility(View.VISIBLE);
            }

        }

    }

    @Override
    public void refreshUI(List<FilmBean> beanList) {
    }


    public void onKeyDown(int keyCode, KeyEvent event) {
//        if (mTvHistoryClear.hasFocus() && keyCode == KeyEvent.KEYCODE_DPAD_UP) {
//            mRvHistory.requestFocus();
//        }
//        if (mRvHistory.hasFocus() && keyCode == KeyEvent.KEYCODE_DPAD_UP) {
//            mEditSearch.requestFocus();
//        }
    }

}
