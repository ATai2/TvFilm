package com.tuojin.tvfilm.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Scroller;

/**
 * 文 件 名: CustomRecycleView
 * 创 建 人: Administrator
 * 创建日期: 2016/11/16 09:55
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class CustomRecycleViewVertical extends RecyclerView {

    private static final String TAG = "scroll";
    private Scroller mScroller;
    private int mLastx;
    private int mTargetPos;


    public CustomRecycleViewVertical(Context context) {
        super(context);
        init(context);
    }

    public CustomRecycleViewVertical(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public CustomRecycleViewVertical(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);

    }

    private void init(Context context) {
        mScroller = new Scroller(context);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //computeScrollOffset返回true表示滚动还在继续，持续时间应该就是startScroll设置的时间
        if (mScroller != null && mScroller.computeScrollOffset()) {
//            Log.i("scroll", "getCurrY = " + mScroller.getCurrY());
            int y = mLastx - mScroller.getCurrY();
//            Log.i(TAG,"y:   "+y);
            scrollBy( 0, y);
            mLastx = mScroller.getCurrY();
            postInvalidate();//让系统继续重绘，则会继续重复执行computeScroll
        }
    }

    /**
     * 将指定item平滑移动到整个view的中间位置
     *
     * @param position
     */
    public void smoothToCenter(int position) {

        int parentHeight = getHeight();//获取父视图的宽度

        //获取可视范围内的选项的头尾位置
        int firstVisiblePosition = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        int count = ((LinearLayoutManager)getLayoutManager()).getItemCount();//获取item总数
        mTargetPos = Math.max(0, Math.min(count - 1, position));//获取目标item的位置（参考listview中的smoothScrollToPosition方法）
        Log.d(TAG, "smoothToCenter: "+mTargetPos);
        int index = mTargetPos - firstVisiblePosition;
        Log.d(TAG, "index:   "+index);
        View targetChild = getChildAt(index);//获取目标item在当前可见视图item集合中的位置

        int childTopPx = targetChild.getTop();//子view相对于父view的左边距
        int childBottomPx = targetChild.getBottom();//子view相对于父view的右边距
        int childHeight = targetChild.getHeight();

        int centerTop = parentHeight/2-childHeight/2;//计算子view居中后相对于父view的左边距
        int centerBottom = parentHeight/2+childHeight/2;//计算子view居中后相对于父view的右边距

        if(childTopPx>centerTop){//子view左边距比居中view大（说明子view靠父view的右边，此时需要把子view向左平移
            //平移的起始位置就是子view的左边距，平移的距离就是两者之差
            mLastx = childTopPx;
            mScroller.startScroll(0,childTopPx,0,centerTop-childTopPx,600);//600为移动时长，可自行设定
            postInvalidate();
        }else if(childBottomPx<centerBottom){
            mLastx = childBottomPx;
            mScroller.startScroll(0,childBottomPx,0,centerBottom-childBottomPx,600);
            postInvalidate();
        }
    }
}
