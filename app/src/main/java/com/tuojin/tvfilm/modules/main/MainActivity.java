package com.tuojin.tvfilm.modules.main;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
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
import com.tuojin.tvfilm.presenter.HotRecommPresenterImpl;

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
        View.OnFocusChangeListener{

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

    BaseFragment mHotRecommFrag,mCategoryFrag,mSortListFrag,mAlbumFrag,mSearchFrag;
    List<BaseFragment> mFragmentList;
    int mPressedCount = 0;
    int type;


    @Override
    protected void initData() {


//        mFragmentList.add(mHotRecommFrag);
//        mFragmentList.add(mCategoryFrag);
//        mFragmentList.add(mSortListFrag);
//        mFragmentList.add(mAlbumFrag);
//        mFragmentList.add(mSearchFrag);


    }

    @Override
    protected void initView() {

        mFragmentList=new ArrayList<>();
        mHotRecommFrag=new RecommFragment();
        mCategoryFrag=new CategoryFragment();
        mSortListFrag=new SortListFragment();
        mAlbumFrag=new AlbumFragment();
        mSearchFrag=new SearchFragment();


        mFragmentList.add(mHotRecommFrag);
        mFragmentList.add(mCategoryFrag);
        mFragmentList.add(mSortListFrag);
        mFragmentList.add(mAlbumFrag);
        mFragmentList.add(mSearchFrag);

        MainAdapter adapter = new MainAdapter(getSupportFragmentManager(),mFragmentList);
        mVpContainer.setAdapter(adapter);
        mVpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                type=position;
                //背景切换？
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    //tv的方向按键响应
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mVpContainer.hasFocus() && keyCode == event.KEYCODE_DPAD_UP) {
            switch (type){
                case 0:
                    mVpContainer.findFocus().setNextFocusUpId(R.id.rab_hotrecomm);
                    break;
                case 1:
                    mVpContainer.findFocus().setNextFocusUpId(R.id.rab_hotrecomm);
                    break;
                case 2:
                    mVpContainer.findFocus().setNextFocusUpId(R.id.rab_hotrecomm);
                    break;case 3:
                    mVpContainer.findFocus().setNextFocusUpId(R.id.rab_hotrecomm);
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
                mPressedCount=0;
            }
        },2000L);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()){
            case R.id.rab_hotrecomm:
                mVpContainer.setCurrentItem(0);
                break;
            case R.id.rab_category:
                mVpContainer.setCurrentItem(1);
                break;
            case R.id.rab_sortlist:
                mVpContainer.setCurrentItem(2);
                break;
            case R.id.rab_album:
                mVpContainer.setCurrentItem(3);
                break;
            case R.id.rab_search:
                mVpContainer.setCurrentItem(4);
                break;
        }
    }
}
