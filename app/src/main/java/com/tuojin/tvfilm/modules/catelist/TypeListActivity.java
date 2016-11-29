package com.tuojin.tvfilm.modules.catelist;

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
import com.tuojin.tvfilm.bean.FilmTypeBean;
import com.tuojin.tvfilm.contract.FilmTypeContract;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.type.OtherFragmentType;
import com.tuojin.tvfilm.presenter.FilmTypePresenterImpl;
import com.tuojin.tvfilm.utils.IConstantType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tuojin.tvfilm.R.id.main_fragment;

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
        implements FilmTypeContract.View, IConstantType {

    @BindView(R.id.index_type)
    TextView mIndexType;
    @BindView(R.id.main_fragment)
    FrameLayout mMainFragment;
    @BindView(R.id.iv_back)
    ImageButton mTabIndicatorBack;
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
    OtherFragmentType mFragAction, mFragFiction, mFragEmothion, mFragSuspense, mFragComedy, mFragAnimal;
    private int mType = 5;
    private FragmentManager fragmentManager;
    private List<OtherFragmentType> mFragments = new ArrayList<>();

    @Override
    protected FilmTypePresenterImpl initPresenter() {
        return new FilmTypePresenterImpl();
    }

    @Override
    protected void initData() {
        showFragment(0);
        mTabIndicatorAction.requestFocus();
    }

    @Override
    protected void initView() {
        fragmentManager=getSupportFragmentManager();
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_filmtype;
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
                fragInit(ft, mFragAction, bundle, 5, TYPE_ACTION);
                break;
            case 1:
                fragInit(ft, mFragFiction, bundle, 3, TYPE_FICTION);
                break;
            case 2:
                fragInit(ft, mFragAnimal, bundle, 4, TYPE_ANIMATION);
                break;
            case 3:
                fragInit(ft, mFragSuspense, bundle, 13, TYPE_SUSPENSE);
                break;
            case 4:
                fragInit(ft, mFragEmothion, bundle, 1, TYPE_EMOTION);
                break;
            case 5:
                fragInit(ft, mFragComedy, bundle, 2, TYPE_COMEDY);
                break;
        }
        ft.commit();
    }

    /**
     * 如果不存在Fragment初始化
     *
     * @param ft
     * @param frag
     * @param bundle
     * @param i
     * @param flag
     */
    private void fragInit(FragmentTransaction ft, OtherFragmentType frag, Bundle bundle, int i, String flag) {
        if (frag == null) {
            frag = new OtherFragmentType();
            mFragments.add(frag);
            bundle.putInt("filmType", i);
            frag.setArguments(bundle);
            ft.add(main_fragment, frag, flag);
        } else {
            ft.show(frag);
        }
    }

    private void firstInit(OtherFragmentType frag, Bundle bundle, int i, String flag) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (frag == null) {
            frag = new OtherFragmentType();
            mFragments.add(frag);
            bundle.putInt("filmType", i);
            frag.setArguments(bundle);
            ft.add(main_fragment, frag, flag);
            ft.commit();
        }
        return;
    }

    /**
     * 隐藏Fragment
     *
     * @param ft
     */
    private void hideFragment(FragmentTransaction ft) {
        for (OtherFragmentType fragment :
                mFragments) {
            if (fragment != null) {
                ft.hide(fragment);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mMainFragment.hasFocus() && keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            //判断哪个获得焦点
            isRefresh = false;
            setHoverRight(ONE);
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.iv_back, R.id.tab_indicator_action, R.id.tab_indicator_fiction, R.id.tab_indicator_animation, R.id.tab_indicator_suspense, R.id.tab_indicator_emotional, R.id.tab_indicator_comedy})
    public void onClick(View view) {
        String index = "";
        int position = 0;
        if (view.getId()==R.id.iv_back){
            this.finish();
            return;
        }
        switch (view.getId()) {
            case R.id.tab_indicator_action:
                mType = 5;
                index = "动作";
                position = 0;
                break;
            case R.id.tab_indicator_fiction:
                mType = 3;
                position = 1;
                index = "科幻";
                break;
            case R.id.tab_indicator_animation:
                mType = 4;
                position = 2;
                index = "动画";
                break;
            case R.id.tab_indicator_suspense:
                mType = 13;
                position = 3;
                index = "悬疑";
                break;
            case R.id.tab_indicator_emotional:
                mType = 1;
                index = "情感";
                position = 4;
                break;
            case R.id.tab_indicator_comedy:
                mType = 2;
                index = "喜剧";
                position = 5;
                break;
        }
        showFragment(position);
        mIndexType.setText(index);
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
                    if (fragment.getTag() == TYPE_ACTION) {
                        setLeftButtonFocus(R.id.tab_indicator_action, type);
                    } else if (fragment.getTag() == TYPE_ANIMATION) {
                        setLeftButtonFocus(R.id.tab_indicator_animation, type);
                    } else if (fragment.getTag() == TYPE_COMEDY) {
                        setLeftButtonFocus(R.id.tab_indicator_comedy, type);
                    } else if (fragment.getTag() == TYPE_EMOTION) {
                        setLeftButtonFocus(R.id.tab_indicator_emotional, type);
                    } else if (fragment.getTag() == TYPE_FICTION) {
                        setLeftButtonFocus(R.id.tab_indicator_fiction, type);
                    } else if (fragment.getTag() == TYPE_SUSPENSE) {
                        setLeftButtonFocus(R.id.tab_indicator_suspense, type);
                    }
                }
            }
        }
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

    public static boolean isRefresh;   //判断RadioButton获取焦点时是否刷新

    @Override
    public void initFilmFragment(List<FilmBean> data) {

    }

    @Override
    public void initMenu(List<FilmTypeBean> list) {

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
