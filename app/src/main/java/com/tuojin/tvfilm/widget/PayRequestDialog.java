package com.tuojin.tvfilm.widget;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tuojin.tvfilm.R;

import butterknife.OnClick;

/**
 * 文 件 名: PayRequestDialog
 * 创 建 人: Administrator
 * 创建日期: 2016/11/10 11:52
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class PayRequestDialog extends DialogFragment {

    Button mBtnPay;
    Button mBtnLater;
    Button mBtnCancle;
    OnbtnClickListener1 mClickListener1;
    OnbtnClickListener2 mClickListener2;
    OnbtnClickListener3 mClickListener3;

    public void setClickListener1(OnbtnClickListener1 clickListener1) {
        mClickListener1 = clickListener1;
    }

    public void setClickListener2(OnbtnClickListener2 clickListener2) {
        mClickListener2 = clickListener2;
    }

    public void setClickListener3(OnbtnClickListener3 clickListener3) {
        mClickListener3 = clickListener3;
    }

   public interface OnbtnClickListener1 {
        void click();
    }
    public  interface OnbtnClickListener2 {
        void click();
    }

    public  interface OnbtnClickListener3 {
        void click();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_pay, container, false);
        mBtnCancle = (Button) view.findViewById(R.id.btn_cancle);
        mBtnLater = (Button) view.findViewById(R.id.btn_later);
        mBtnPay = (Button) view.findViewById(R.id.btn_pay);
        mBtnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener1.click();
            }
        });
        mBtnLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener2.click();
            }
        });
        mBtnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener3.click();
            }
        });

        return view;
    }

    @OnClick({R.id.btn_pay, R.id.btn_later, R.id.btn_cancle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pay:
                break;
            case R.id.btn_later:
                break;
            case R.id.btn_cancle:
                break;
        }
    }
}
