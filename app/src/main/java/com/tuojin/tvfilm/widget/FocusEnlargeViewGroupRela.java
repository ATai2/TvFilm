package com.tuojin.tvfilm.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.tuojin.tvfilm.R;

/**
 * 文 件 名: FocusEnlargeViewGroup
 * 创 建 人: Administrator
 * 创建日期: 2016/11/10 13:50
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class FocusEnlargeViewGroupRela extends LinearLayout {

    private Animation mScaleToLarge;
    private int mAnimationDuration=100;

    public FocusEnlargeViewGroupRela(Context context) {
        super(context);
        initAnimation();
    }

    public FocusEnlargeViewGroupRela(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FocusEnlargeViewGroupRela(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void initAnimation() {
        mScaleToLarge = AnimationUtils.loadAnimation(this.getContext(), R.anim.scale_out);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (gainFocus) {
            scaleTolarge();
        }

    }

    private void scaleTolarge() {
        if (mAnimationDuration > 0) {
            clearAnimation();
            mScaleToLarge.setStartTime(AnimationUtils.currentAnimationTimeMillis() + 2);
            setAnimation(mScaleToLarge);
        }
    }
}
