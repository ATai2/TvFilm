package com.tuojin.tvfilm.modules.main;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.contract.HotRecommContract;
import com.tuojin.tvfilm.modules.main.album.AlbumFragment;
import com.tuojin.tvfilm.modules.main.category.CategoryFragment;
import com.tuojin.tvfilm.modules.main.hotrecomm.RecommFragment;
import com.tuojin.tvfilm.modules.main.search.SearchFragment;
import com.tuojin.tvfilm.modules.main.sortlist.SortListFragment;
import com.tuojin.tvfilm.modules.search.SearchActivity;
import com.tuojin.tvfilm.presenter.HotRecommPresenterImpl;
import com.tuojin.tvfilm.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文 件 名: CategoryContract
 * 创 建 人: Administrator
 * 创建日期: 2016/9/21 13:50
 * 文件描述：主Activity，通过不同的Fragment
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class MainActivity extends BaseActivity<HotRecommContract.View, HotRecommPresenterImpl> implements
        View.OnFocusChangeListener {

    public static final String TAG = "MainActivity";

    @BindView(R.id.rab_hotrecomm)
    RadioButton mRbHotRecom;
    @BindView(R.id.rab_category)
    RadioButton mRbCatgory;
    @BindView(R.id.rab_sortlist)
    RadioButton mRabSortlist;
    @BindView(R.id.rab_album)
    RadioButton mRabAlbum;
    @BindView(R.id.rab_search)
    RadioButton mRabSearch;
    @BindView(R.id.group)
    RadioGroup mGroup;
    @BindView(R.id.mVpContainer)
    ViewPager mVpContainer;

    BaseFragment mHotRecommFrag, mCategoryFrag, mSortListFrag, mAlbumFrag, mSearchFrag;
    List<BaseFragment> mFragmentList;
    int mPressedCount = 0;
    int type;
    private int[] mIntArray;


    @Override
    protected HotRecommPresenterImpl initPresenter() {
        return new HotRecommPresenterImpl();
    }

    @Override
    protected void initData() {
//        mIntArray = getResources().getIntArray(R.array.category_pic);
    }

    @Override
    protected void initView() {
        mVpContainer.setOffscreenPageLimit(5);
        mVpContainer.setCurrentItem(0);
        mVpContainer.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View view, View view1) {

            }
        });

        //设置焦点改变监听
        mRbHotRecom.setOnFocusChangeListener(this);
        mRbCatgory.setOnFocusChangeListener(this);
        mRabSortlist.setOnFocusChangeListener(this);
        mRabAlbum.setOnFocusChangeListener(this);
        mRabSearch.setOnFocusChangeListener(this);

        mFragmentList = new ArrayList<>();
        mHotRecommFrag = new RecommFragment();
        mCategoryFrag = new CategoryFragment();
        mSortListFrag = new SortListFragment();
        mAlbumFrag = new AlbumFragment();
        mSearchFrag = new SearchFragment();

        mFragmentList.add(mHotRecommFrag);
        mFragmentList.add(mCategoryFrag);
        mFragmentList.add(mSortListFrag);
        mFragmentList.add(mAlbumFrag);
        mFragmentList.add(mSearchFrag);

        MainAdapter adapter = new MainAdapter(getSupportFragmentManager(), mFragmentList);
        mVpContainer.setAdapter(adapter);
        mVpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                type = position;
                LogUtils.d("TAG", position + "");
                switch (position) {
                    case 0:
                        setBackground(mRbHotRecom, mRbCatgory, mRabSortlist, mRabAlbum, mRabSearch);
                        break;
                    case 1:
                        setBackground(mRbCatgory, mRabSortlist, mRabAlbum, mRabSearch, mRbHotRecom);
                        break;
                    case 2:
                        setBackground(mRabSortlist, mRabAlbum, mRabSearch, mRbHotRecom, mRbCatgory);
                        break;
                    case 3:
                        setBackground(mRabAlbum, mRabSearch, mRbHotRecom, mRbCatgory, mRabSortlist);
                        break;
                    case 4:
                        setBackground(mRabSearch, mRbHotRecom, mRbCatgory, mRabSortlist, mRabAlbum);
                        break;
                }
                mVpContainer.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mRbHotRecom.setFocusable(true);
        mRbHotRecom.setChecked(true);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    //tv的方向按键响应
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mVpContainer.hasFocus() && keyCode == event.KEYCODE_DPAD_UP) {
            switch (type) {
                case 0:
                    mVpContainer.findFocus().setNextFocusUpId(R.id.rab_hotrecomm);
                    break;
                case 1:
                    mVpContainer.findFocus().setNextFocusUpId(R.id.rab_category);
                    break;
                case 2:
                    mVpContainer.findFocus().setNextFocusUpId(R.id.rab_sortlist);
                    break;
                case 3:
                    mVpContainer.findFocus().setNextFocusUpId(R.id.rab_album);
                    break;
                case 4:
                    mVpContainer.findFocus().setNextFocusUpId(R.id.rab_search);
                    break;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.rab_hotrecomm, R.id.rab_category, R.id.rab_sortlist, R.id.rab_album, R.id.rab_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rab_hotrecomm:
                break;
            case R.id.rab_category:
                break;
            case R.id.rab_sortlist:
                break;
            case R.id.rab_album:
                break;
            case R.id.rab_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }
    }

    //返回键按2次，
    @Override
    public void onBackPressed() {
        this.mPressedCount++;
        if (mPressedCount == 2) {
            super.onBackPressed();
            return;
        }
        Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPressedCount = 0;
            }
        }, 2000L);
    }

    //  RadioButton控制viewpager
    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()) {
            case R.id.rab_hotrecomm:
                LogUtils.d("11", "hot");
//                mRbHotRecom.setTextColor(Color.WHITE);
                mVpContainer.setCurrentItem(0);
                break;
            case R.id.rab_category:
                LogUtils.d("11", "rab_category");
//                mRbCatgory.setTextColor(Color.WHITE);
                mVpContainer.setCurrentItem(1);
                break;
            case R.id.rab_sortlist:
                LogUtils.d("11", "rab_sortlist");
//                mRabSortlist.setTextColor(Color.WHITE);
                mVpContainer.setCurrentItem(2);
                break;
            case R.id.rab_album:
                LogUtils.d("11", "rab_album");
//                mRabAlbum.setTextColor(Color.WHITE);
                mVpContainer.setCurrentItem(3);
                break;
            case R.id.rab_search:
                LogUtils.d("11", "rab_search");
//                mRabSearch.setTextColor(Color.WHITE);
//                mVpContainer.setCurrentItem(4);
                break;
        }
    }
}
