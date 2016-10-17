package com.tuojin.tvfilm.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * 文 件 名: AutoAdjustRecyclerView
 * 创 建 人: Administrator
 * 创建日期: 2016/10/17 12:46
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class AutoAdjustRecyclerView extends RecyclerView {
    final String TAG = "autoadjustRecyclerView";
    Scroller mScroller = null;
    int mLastx = 0;
    float mPxPerMillsec = 0;

    public float getPxPerMillsec() {
        return mPxPerMillsec;
    }

    public void setPxPerMillsec(float pxPerMillsec) {
        mPxPerMillsec = pxPerMillsec;
    }

    AutoAdjustItemClickListener mListener = null;

    public AutoAdjustRecyclerView(Context context) {
        super(context);
        initData(context);
    }

    public AutoAdjustRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData(context);

    }

    public AutoAdjustRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initData(context);

    }

    private void initData(Context context) {
        mScroller = new Scroller(context, new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return input;
            }
        });
    }

    public void setScroller(Scroller scroller) {
        if (mScroller != null) {
            mScroller = scroller;
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller != null) {
            if (mScroller.computeScrollOffset()) {
                Log.d("AutoAdjustRecyclerView", "getCurrX+" + mScroller.getCurrX());
                scrollBy(mLastx - mScroller.getCurrX(), 0);
                mLastx = mScroller.getCurrX();
                postInvalidate();  //继续让系统重绘
            }
        }
    }


    public AutoAdjustItemClickListener getItemClickListener() {
        return mListener;
    }

    public void setItemClickListener(AutoAdjustItemClickListener listener) {
        this.mListener = listener;
    }

    public void checkAutoAdjust(int position) {
        int childcount = getChildCount();
        int firstVisibleItemPosition = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        int lastVisibleItemPosition = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        if (position == (firstVisibleItemPosition + 1) || position == firstVisibleItemPosition) {
            //当前位置需要向右平移
            leftScrollBy(position, firstVisibleItemPosition);
        } else if (position == (lastVisibleItemPosition - 1) || position == lastVisibleItemPosition) {
            //当前位置需要向做平移
            rightScrollBy(position, lastVisibleItemPosition);
        }
    }

    private void leftScrollBy(int position, int firstvisiableposition) {
        View leftChild = getChildAt(0);
        if (leftChild != null) {
            int leftx = leftChild.getLeft();
            Log.d(TAG, "leftChild left:" + leftx);
            int startleft = leftx;
            int endleft = position == firstvisiableposition ? leftChild.getWidth() : 0;
            Log.d(TAG, "startleft:" + startleft + " endleft" + endleft);
            autoAdjustScroll(startleft, endleft);
        }
    }


    private void rightScrollBy(int position, int lastvisiableposition) {
        int childcount = getChildCount();
        View rightChild = getChildAt(childcount - 1);
        if (rightChild != null) {
            int rightx = rightChild.getRight();
            int dx = rightx - getWidth();
            Log.d(TAG, "rightChild right:" + rightx + " dx:" + dx);
            int startright = dx;
            int endright = position == lastvisiableposition ? -1 * rightChild.getWidth() : 0;
            Log.d(TAG, "startright:" + startright + " endright:" + endright);
            autoAdjustScroll(startright, endright);
        }
    }

    /**
     * @param start 滑动起始位置
     * @param end   滑动结束位置
     */
    private void autoAdjustScroll(int start, int end) {
        int duration = 0;
        if (mPxPerMillsec != 0) {
            duration = (int) ((Math.abs(end - start) / mPxPerMillsec));
        }
        Log.d(TAG, "duration:" + duration);
        mLastx = start;
        mScroller.startScroll(start, 0, end - start, 0, duration);
        postInvalidate();
    }
    public abstract class AbstractAutoAdjustViewHolder extends ViewHolder implements OnClickListener{

        private final static String TAG = "AutoAdjustViewHolder";

        public AbstractAutoAdjustViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            initView(view);
        }

        protected abstract void initView(View view);

        /**
         * 点击监听
         */
        @Override
        public void onClick(View v) {
            //单击选项的时候判断是否需要移动
            checkAutoAdjust(getPosition());
            if(mListener != null){
                mListener.onItemClick(v,getPosition());
            }
        }
    }

    public interface AutoAdjustItemClickListener {
        public void onItemClick(View view, int postion);
    }
}
