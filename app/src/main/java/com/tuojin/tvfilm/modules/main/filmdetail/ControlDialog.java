package com.tuojin.tvfilm.modules.main.filmdetail;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.tuojin.tvfilm.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 文 件 名: ControlDialog
 * 创 建 人: Administrator
 * 创建日期: 2016/11/21 11:50
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class ControlDialog extends DialogFragment {

    @BindView(R.id.seeker)
    SeekBar mSeeker;
    @BindView(R.id.position)
    TextView mPosition;
    @BindView(R.id.duration)
    TextView mDuration;
    @BindView(R.id.btnLeft)
    ImageButton mBtnLeft;
    @BindView(R.id.btnRetry)
    TextView mBtnRetry;
    @BindView(R.id.btnPlayPause)
    ImageButton mBtnPlayPause;
    @BindView(R.id.labelBottom)
    TextView mLabelBottom;
    @BindView(R.id.btnRight)
    ImageButton mBtnRight;
    @BindView(R.id.labelCustom)
    TextView mLabelCustom;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.evp_include_controls, container, false);


        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.btnLeft, R.id.btnPlayPause, R.id.btnRight})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLeft:

                break;
            case R.id.btnPlayPause:
                break;
            case R.id.btnRight:
                break;
        }
    }
}
