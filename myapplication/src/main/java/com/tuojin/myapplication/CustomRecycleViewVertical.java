package com.tuojin.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
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
//        int parentWidth = getHeight();//获取父视图的宽度
//        int childCount = getChildCount();//获取当前视图可见子view的总数
//        //获取可视范围内的选项的头尾位置
//        int firstvisiableposition = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
//        int lastvisiableposition = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
//        int count = ((LinearLayoutManager) getLayoutManager()).getItemCount();//获取item总数
//        Log.i(TAG, "count:" + count);
//        mTargetPos = Math.max(0, Math.min(count - 1, position));//获取目标item的位置（参考listview中的smoothScrollToPosition方法）
//        Log.i(TAG, "firstposition:" + firstvisiableposition + "   lastposition:" + lastvisiableposition + "   position:" + position +
//                "   mTargetPos:" + mTargetPos);
//        View targetChild = getChildAt(mTargetPos - firstvisiableposition);//获取目标item在当前可见视图item集合中的位置
//        View firstChild = getChildAt(0);//当前可见视图集合中的最左view
//        View lastChild = getChildAt(childCount - 1);//当前可见视图集合中的最右view
//        Log.i(TAG, "first-->top:" + firstChild.getTop() + "   bot:" + firstChild.getBottom());
//        Log.i(TAG, "last-->top:" + lastChild.getTop() + "   bot:" + lastChild.getBottom());
//        int childTopPx = targetChild.getTop();//子view相对于父view的左边距
//        int childBotttomPx = targetChild.getBottom();//子view相对于父view的右边距
//        Log.i(TAG, "target-->top:" + targetChild.getTop() + "   bot:" + targetChild.getBottom());
//
//        int childWidth = targetChild.getHeight();
//        int centerTop = parentWidth / 2 - childWidth / 2;//计算子view居中后相对于父view的左边距
//        int centerBottom = parentWidth / 2 + childWidth / 2;//计算子view居中后相对于父view的右边距
//        Log.i(TAG, "rv top:" + parentWidth + "   item top:" + childWidth + "   centertop:" + centerTop + "   centerBottom:" + centerBottom);
//        if (childTopPx > centerTop) {//子view左边距比居中view大（说明子view靠父view的右边，此时需要把子view向左平移
//            //平移的起始位置就是子view的左边距，平移的距离就是两者之差
//            Log.i(TAG, "target-->down");
//            mLastx = childTopPx;
//             int dy =childTopPx- centerTop;
//            Log.i(TAG, "dy:down" + dy);
//            mScroller.startScroll(0, childTopPx, 0, -dy, 600);//600为移动时长，可自行设定
//            postInvalidate();
//        } else if (childBotttomPx < centerBottom) {
//            Log.i(TAG, "target-->up");
//
////            mLastx = childLeftPx;  centerRight-childRightPx
//            mLastx = childBotttomPx;
//            int dy = centerBottom - childBotttomPx;
//            Log.i(TAG, "dy:" + dy);
//            mScroller.startScroll(0, childBotttomPx, 0, dy, 600);
//            postInvalidate();
//        }

        int parentHeight = getHeight();//获取父视图的宽度

        //获取可视范围内的选项的头尾位置
        int firstVisiblePosition = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        //int lastVisiblePosition = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        int count = ((LinearLayoutManager)getLayoutManager()).getItemCount();//获取item总数

        mTargetPos = Math.max(0, Math.min(count - 1, position));//获取目标item的位置（参考listview中的smoothScrollToPosition方法）

        View targetChild = getChildAt(mTargetPos-firstVisiblePosition);//获取目标item在当前可见视图item集合中的位置

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
