package com.tuojin.tvfilm.modules.search;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.TextView;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.contract.SearchContract;
import com.tuojin.tvfilm.presenter.SearchPresenterImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

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
public class SearchActivity extends BaseActivity<SearchContract.View, SearchPresenterImpl> implements SearchContract.View {


    @BindView(R.id.tv_time)
    TextView mTvTime;
    private SimpleDateFormat mDf;
    private Fragment mSearchFrag;

    @Override
    protected SearchPresenterImpl initPresenter() {
        return new SearchPresenterImpl();
    }

    @Override
    protected void initData() {
        mDf = new SimpleDateFormat("MM-dd EEEE HH:mm:ss");
        Timer timer = new Timer();
        timer.schedule(new MyTimer(), 1000, 1000);

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        mSearchFrag = fragments.get(0);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (mSearchFrag != null) {
            ((SearchFragment) mSearchFrag).onKeyUp(keyCode, event);
        }

        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mSearchFrag != null) {
            ((SearchFragment) mSearchFrag).onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
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
    protected void initView() {


    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_search_frag;
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
