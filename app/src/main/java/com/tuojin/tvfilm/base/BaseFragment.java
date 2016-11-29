package com.tuojin.tvfilm.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuojin.tvfilm.event.EmptyEvent;
import com.tuojin.tvfilm.utils.LogUtils;
import com.tuojin.tvfilm.widget.CustomProgressDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * 文 件 名: BaseFragment
 * 创 建 人: Administrator
 * 创建日期: 2016/9/20 18:15
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment implements BaseView {
    public static final String TAG = "BaseFragment";
    public View mView;
    public T mPresenter;
    public CustomProgressDialog progressDialog;
    protected BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(EmptyEvent event) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG, "oncreate");
//        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = initPresenter();
        mView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, mView);
        EventBus.getDefault().register(this);
        initView();
        return mView;
    }

    /**
     * mvp模式，Fragment继承view，presenter做绑定。
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.attach((V) this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
        if (mPresenter != null) {
            mPresenter.dettach();
        }
    }

    public void startProgressDialog() {
        if (progressDialog == null) {
            progressDialog = CustomProgressDialog.createDialog(mActivity);
            progressDialog.setMessage("");
        }
        progressDialog.show();
    }

    public void stopProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract T initPresenter();

    @Override
    public void showLoading() {
        startProgressDialog();
    }

    @Override
    public void hideLoading() {
        stopProgressDialog();
    }

    @Override
    public void showMessage(String msg) {
        stopProgressDialog();
//        Toast.makeText(mActivity, msg, Toast.LENGTH_LONG).show();
    }
}
