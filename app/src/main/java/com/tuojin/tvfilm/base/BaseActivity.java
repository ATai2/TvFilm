package com.tuojin.tvfilm.base;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.bean.ErrorBean;
import com.tuojin.tvfilm.event.EmptyEvent;
import com.tuojin.tvfilm.event.ErrorEvent;
import com.tuojin.tvfilm.service.AutoBahnService;
import com.tuojin.tvfilm.widget.CustomProgressDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * 文 件 名: BaseActivity
 * 创 建 人: Administrator
 * 创建日期: 2016/9/18 15:08
 * 文件描述：基类
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity implements BaseView {
    String type;
    protected T mPresenter;
    protected BaseActivity mActivity;
    public AutoBahnService mService;
    protected String TAG = "abs";
    public Dialog dialog;
    public CustomProgressDialog progressDialog;
    private ServiceConnection mConn;

    public void setPresenter(T presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);

        mConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mService = ((AutoBahnService.MyBinder) service).getInstance();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected: ");
            }
        };
        bindService(new Intent(this, AutoBahnService.class), mConn, BIND_AUTO_CREATE);
        //设置无标题
        mActivity = this;
        mInflater = LayoutInflater.from(mActivity);
        setContentView(getLayoutID());
        ButterKnife.bind(this);
        mPresenter = initPresenter();
        initView();
        initData();

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(EmptyEvent event) {

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(ErrorEvent event) {
        Toast.makeText(mActivity, new Gson().fromJson(event.msg, ErrorBean.class).getData().getMsg(), Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConn);
        EventBus.getDefault().unregister(this);
    }

    protected abstract T initPresenter();

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected abstract void initData();

    protected abstract void initView();

    public abstract int getLayoutID();

    public void setBackground(RadioButton radOne, RadioButton radTwo, RadioButton radThree, RadioButton radFour, RadioButton radFive) {
        //  radOne.setBackgroundResource(R.drawable.title_bg);
//        radOne.setBackground(null);
//        radTwo.setBackground(null);
//        radThree.setBackground(null);
//        radFour.setBackground(null);
//        radFive.setBackground(null);

        radOne.setSelected(true);
        radTwo.setSelected(false);
        radThree.setSelected(false);
        radFour.setSelected(false);
        radFive.setSelected(false);


    }

    //RadioButton 选中时的背景
    public void setButtonFocus(RadioButton btn) {
        btn.setBackgroundResource(R.drawable.btn_bg);
    }

    //RadioButton为null
    public void setButtonNull(RadioButton btn) {
        btn.setBackground(null);
    }

    //RadioButton获得焦点时背景颜色
    public void setOnFocusChange(final RadioButton btn) {
        btn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    setButtonFocus(btn);
                }
            }
        });
    }

    @Override
    public void showLoading() {
        startProgressDialog();
    }

    private void startProgressDialog() {
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

    private RelativeLayout layout;
    private LayoutInflater mInflater;

    //Dialog
    public void setDialog(int layoutId) {
        layout = (RelativeLayout) mInflater.inflate(layoutId, null);
        dialog = new AlertDialog.Builder(mActivity).create();
        dialog.show();
        dialog.getWindow().setContentView(layout);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = 1000;
        params.height = 600;
        dialog.getWindow().setAttributes(params);

    }


}
