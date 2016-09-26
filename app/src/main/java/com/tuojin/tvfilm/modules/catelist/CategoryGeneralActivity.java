package com.tuojin.tvfilm.modules.catelist;

import android.os.Handler;
import android.os.Message;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.bean.DirectListBean;
import com.tuojin.tvfilm.contract.CateListContract;
import com.tuojin.tvfilm.presenter.CateListPresenterImpl;

import java.util.List;

/**
 * 文 件 名: CategoryGeneralActivity
 * 创 建 人: Administrator
 * 创建日期: 2016/9/26 12:50
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class CategoryGeneralActivity extends BaseActivity<CateListContract.View,CateListPresenterImpl>
implements CateListContract.View{


    private int mPosition;
    private List<DirectListBean.DataBean.DirectorBean> mDirectorBeanList;

    @Override
    protected CateListPresenterImpl initPresenter() {
        return new CateListPresenterImpl();
    }

    @Override
    protected void initData() {
        mPosition = getIntent().getIntExtra("position",0);
    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_catelist_general;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.initRadioButton(mPosition);

    }

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                //将得到的列表初始化

            }
        }
    };
    @Override
    public void initViewRadioButton(List<DirectListBean.DataBean.DirectorBean> directorBeanList) {
        mDirectorBeanList = directorBeanList;
    }
}
