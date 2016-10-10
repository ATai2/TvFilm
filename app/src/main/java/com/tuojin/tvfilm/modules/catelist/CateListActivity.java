package com.tuojin.tvfilm.modules.catelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.contract.CateListContract;
//import com.tuojin.tvfilm.modules.search.SearchActivity;
import com.tuojin.tvfilm.presenter.CateListPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 文 件 名: CateListActivity
 * 创 建 人: Administrator
 * 创建日期: 2016/9/22 15:27
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class CateListActivity<T> extends BaseActivity<CateListContract.View<T>, CateListPresenterImpl<T>> {


    @BindView(R.id.tab_indicator_search)
    RadioButton mTabIndicatorSearch;
    @BindView(R.id.tab_indicator_screening)
    RadioButton mTabIndicatorFilter;
    @BindView(R.id.tab_container_category)
    LinearLayout mTabContainerCategory;
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
    @BindView(R.id.tab_indicator_all)
    RadioButton mTabIndicatorAll;

    private int mType;
    private FragmentManager mFragmentManager;

    @Override
    protected CateListPresenterImpl initPresenter() {
        return new CateListPresenterImpl();
    }

    @Override
    protected void initData() {
        mPresenter.initRadioButton(mType);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mType = intent.getIntExtra("type", 0);


        mFragmentManager = getSupportFragmentManager();
        showFragment(0);
    }

    private void showFragment(int i) {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_category;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 不同radiobutton开启不同的
     * @param view
     */
    @OnClick({R.id.tab_indicator_search, R.id.tab_indicator_screening, R.id.tab_indicator_action, R.id.tab_indicator_fiction, R.id.tab_indicator_animation, R.id.tab_indicator_suspense, R.id.tab_indicator_emotional, R.id.tab_indicator_comedy, R.id.tab_indicator_all})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_indicator_search:
//                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.tab_indicator_screening:
//                筛选
                break;
            case R.id.tab_indicator_action:
                break;
            case R.id.tab_indicator_fiction:
                break;
            case R.id.tab_indicator_animation:
                break;
            case R.id.tab_indicator_suspense:
                break;
            case R.id.tab_indicator_emotional:
                break;
            case R.id.tab_indicator_comedy:
                break;
            case R.id.tab_indicator_all:
                break;
        }
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String msg) {

    }
}
