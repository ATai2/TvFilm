package com.tuojin.tvfilm.modules.main.sortlist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.contract.SortListContract;
import com.tuojin.tvfilm.modules.catelist.OtherFragment;
import com.tuojin.tvfilm.presenter.SortListPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tuojin.tvfilm.R.id.main_fragment;

public class SortActivity extends BaseActivity<SortListContract.View, SortListPresenterImpl> implements SortListContract.View, View.OnFocusChangeListener{


    OtherFragment mFilmNew, mFilmHot, mFilmBig, mFilmAd, mFilmDouban;
    @BindView(R.id.index_type)
    TextView mIndexType;
    @BindView(main_fragment)
    FrameLayout mMainFragment;
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
    View view;

    public static final String FRAGMENT_TAG_C = "ft_tag_c";
    public static final String FRAGMENT_TAG_D = "ft_tag_d";
    public static final String FRAGMENT_TAG_E = "ft_tag_e";
    public static final String FRAGMENT_TAG_F = "ft_tag_f";
    public static final String FRAGMENT_TAG_G = "ft_tag_g";
    //    private FragmentManager ft;
    public FragmentManager fragmentManager;
    private List<OtherFragment> mFragments = new ArrayList<>();
    public static boolean isRefresh;   //判断RadioButton获取焦点时是否刷新
    private RadioButton mCurrentRadioButton;
    public static boolean rightFirst = false;

    @Override
    protected SortListPresenterImpl initPresenter() {
        return null;
    }
    public  SortActivity getInstance(){
        return new SortActivity();
    }

    @Override
    protected void initData() {
        int position = getIntent().getIntExtra("position", 0);
        switch (position) {
            case 0:
                mTabIndicatorNew.requestFocus();
                break;
            case 1:
                mTabIndicatorHot.requestFocus();
                break;
            case 2:
                mTabIndicatorBig.requestFocus();
                break;
            case 3:
                mTabIndicatorAd.requestFocus();
                break;
            case 4:
                mTabIndicatorDouban.requestFocus();
                break;
        }
        showFragment(position);
    }

    @Override
    protected void initView() {
        fragmentManager = getSupportFragmentManager();
        mTabIndicatorDouban.setOnFocusChangeListener(this);
        mTabIndicatorAd.setOnFocusChangeListener(this);
        mTabIndicatorBig.setOnFocusChangeListener(this);
        mTabIndicatorHot.setOnFocusChangeListener(this);
        mTabIndicatorNew.setOnFocusChangeListener(this);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortActivity.this.finish();
            }
        });
    }

    private void initFragments() {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        hideFragment(ft);
        Bundle bundle = new Bundle();
        mFilmNew = new OtherFragment();
        mFragments.add(mFilmNew);
        bundle.putInt("sortType", 0);
        mFilmNew.setArguments(bundle);
        ft.add(main_fragment, mFilmNew, FRAGMENT_TAG_C);

        mFilmHot = new OtherFragment();
        mFragments.add(mFilmHot);
        bundle.putInt("sortType", 1);
        mFilmHot.setArguments(bundle);
        ft.add(main_fragment, mFilmHot, FRAGMENT_TAG_D);

        mFilmBig = new OtherFragment();
        mFragments.add(mFilmBig);
        bundle.putInt("sortType", 2);
        mFilmBig.setArguments(bundle);
        ft.add(main_fragment, mFilmBig, FRAGMENT_TAG_E);

        mFilmAd = new OtherFragment();
        mFragments.add(mFilmAd);
        bundle.putInt("sortType", 3);
        mFilmAd.setArguments(bundle);
        ft.add(main_fragment, mFilmAd, FRAGMENT_TAG_F);

        mFilmDouban = new OtherFragment();
        mFragments.add(mFilmDouban);
        bundle.putInt("sortType", 4);
        mFilmDouban.setArguments(bundle);
        ft.add(main_fragment, mFilmDouban, FRAGMENT_TAG_G);
        ft.commit();
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_sort;
    }

    @Override
    public void showLoading() {
        mActivity.showLoading();
    }

    @Override
    public void hideLoading() {
        mActivity.hideLoading();
    }

    @Override
    public void showMessage(String msg) {

    }

    @OnClick({R.id.tab_indicator_new, R.id.tab_indicator_hot, R.id.tab_indicator_big, R.id.tab_indicator_ad, R.id.tab_indicator_douban})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_indicator_new:
                break;
            case R.id.tab_indicator_hot:
                break;
            case R.id.tab_indicator_big:
                break;
            case R.id.tab_indicator_ad:
                break;
            case R.id.tab_indicator_douban:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            isRefresh = true;
            getOnFocus();
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            isRefresh = true;
            getOnFocus();
        }

        if (mMainFragment.hasFocus() && keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            //判断哪个获得焦点
            isRefresh = false;
            setHoverRight(ONE);
        }
        //通过焦点的获得
        if (mTabIndicatorNew.hasFocus() && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {

        }

        return super.onKeyDown(keyCode, event);
    }

    private void getOnFocus() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        mTabIndicatorNew.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showFragment(0);
                }
            }
        });
        mTabIndicatorHot.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showFragment(1);
                }
            }
        });
        mTabIndicatorBig.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showFragment(2);
                }
            }
        });
        mTabIndicatorAd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showFragment(3);
                }
            }
        });
        mTabIndicatorDouban.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showFragment(4);
                }
            }
        });

    }

    //当焦点移动到右侧时 当前RadioButton的状态
    private void setHoverButton(RadioButton btn1, RadioButton btn2, RadioButton btn3) {
        btn1.setBackground(null);
        btn2.setBackgroundResource(R.drawable.btn_bg);
        btn3.setBackground(null);
    }

    public static final int ONE = 0;
    public static final int TWO = 1;

    //当焦点frgment移动至RadioGroup时 指明移动到哪一个RadioButton ID上
    private void setLeftButtonFocus(int id, int type) {
        if (type == ONE) {
            mMainFragment.findFocus().setNextFocusLeftId(id);
        } else if (type == TWO) {
            mMainFragment.findFocus().setNextFocusUpId(id);
        }
    }

    /**
     * 焦点设置
     *
     * @param type
     */
    private void setHoverRight(int type) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                if (fragment.isVisible()) {
                    if (fragment.getTag() == FRAGMENT_TAG_C) {
                        setLeftButtonFocus(R.id.tab_indicator_new, type);
                    } else if (fragment.getTag() == FRAGMENT_TAG_D) {
                        setLeftButtonFocus(R.id.tab_indicator_hot, type);
                    } else if (fragment.getTag() == FRAGMENT_TAG_E) {
                        setLeftButtonFocus(R.id.tab_indicator_big, type);
                    } else if (fragment.getTag() == FRAGMENT_TAG_F) {
                        setLeftButtonFocus(R.id.tab_indicator_ad, type);
                    } else if (fragment.getTag() == FRAGMENT_TAG_G) {
                        setLeftButtonFocus(R.id.tab_indicator_douban, type);
                    }
                }
            }
        }
    }

    /**
     * 根据序号显示Fragment
     *
     * @param i
     */
    private void showFragment(int i) {

        FragmentTransaction ft = fragmentManager.beginTransaction();
        hideFragment(ft);
        Bundle bundle = new Bundle();
        switch (i) {
            case 0:
                if (mFilmNew == null) {
                    mFilmNew = new OtherFragment();
                    mFragments.add(mFilmNew);
                    bundle.putInt("sortType", 0);
                    mFilmNew.setArguments(bundle);
                    ft.add(main_fragment, mFilmNew, FRAGMENT_TAG_C);
                } else {
                    ft.show(mFilmNew);
                }
                break;
            case 1:
                if (mFilmHot == null) {
                    mFilmHot = new OtherFragment();
                    mFragments.add(mFilmHot);
                    bundle.putInt("sortType", 1);
                    mFilmHot.setArguments(bundle);
                    ft.add(main_fragment, mFilmHot, FRAGMENT_TAG_D);
                } else {
                    ft.show(mFilmHot);
                }
                break;
            case 2:
                if (mFilmBig == null) {
                    mFilmBig = new OtherFragment();
                    mFragments.add(mFilmBig);
                    bundle.putInt("sortType", 2);
                    mFilmBig.setArguments(bundle);
                    ft.add(main_fragment, mFilmBig, FRAGMENT_TAG_E);
                } else {

                    ft.show(mFilmBig);
                }
                break;

            case 3:
                if (mFilmAd == null) {
                    mFilmAd = new OtherFragment();
                    mFragments.add(mFilmAd);
                    bundle.putInt("sortType", 3);
                    mFilmAd.setArguments(bundle);
                    ft.add(main_fragment, mFilmAd, FRAGMENT_TAG_F);
                } else {
                    ft.show(mFilmAd);
                }
                break;
            case 4:
                if (mFilmDouban == null) {
                    mFilmDouban = new OtherFragment();
                    mFragments.add(mFilmDouban);
                    bundle.putInt("sortType", 4);
                    mFilmDouban.setArguments(bundle);
                    ft.add(main_fragment, mFilmDouban, FRAGMENT_TAG_G);
                } else {
                    ft.show(mFilmDouban);
                }
                break;
        }
        ft.commit();
    }

    /**
     * 隐藏Fragment
     *
     * @param ft
     */
    private void hideFragment(FragmentTransaction ft) {
        for (OtherFragment fragment :
                mFragments) {
            if (fragment != null) {
                ft.hide(fragment);
            }
        }
    }

    @Override
    public void setRecyclerItem(List<FilmBean> mList) {

    }

    @Override
    public void refreshUI() {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            mCurrentRadioButton = (RadioButton) v;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
