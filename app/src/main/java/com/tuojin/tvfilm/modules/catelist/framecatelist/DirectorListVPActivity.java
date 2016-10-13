//package com.tuojin.tvfilm.modules.catelist.framecatelist;
//
//import android.content.Intent;
//import android.graphics.Matrix;
//import android.os.Handler;
//import android.os.Message;
//import android.support.v4.view.ViewPager;
//import android.support.v7.widget.OrientationHelper;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.StaggeredGridLayoutManager;
//import android.util.DisplayMetrics;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.Animation;
//import android.view.animation.TranslateAnimation;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//
//import com.tuojin.tvfilm.R;
//import com.tuojin.tvfilm.base.BaseActivity;
//import com.tuojin.tvfilm.bean.DirectorBean;
//import com.tuojin.tvfilm.contract.DirectorListContract;
//import com.tuojin.tvfilm.modules.catelist.framecatelist.activity.DirectorDetailActivity;
//import com.tuojin.tvfilm.modules.catelist.framecatelist.adapter.MyViewPagerAdapter;
//import com.tuojin.tvfilm.presenter.DirectorListPresenterImpl;
//import com.tuojin.tvfilm.utils.ImageLoaderUtils;
//import com.tuojin.tvfilm.widget.FocusGridLayoutManager;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 文 件 名: DirectorListVPActivity
// * 创 建 人: Administrator
// * 创建日期: 2016/9/30 14:05
// * 文件描述：
// * 邮   箱:
// * 博   客:
// * 修改时间：
// * 修改备注：
// */
//public class DirectorListVPActivity extends BaseActivity<DirectorListContract.View, DirectorListPresenterImpl>
//        implements DirectorListContract.View {
//
//    private RecyclerView mRecyclerView;
//    private ImageButton iv_back;
//    //    private CategoryInfo mCategoryInfo;
//    private StaggeredGridLayoutManager mLayoutManager;
//    private ViewPager mViewPager;
//    private boolean hasloadedData = false;
//    private static final int PAGE_SIZE = 8;
//
//    //翻页进度条
//    private static int sTotalPages = 1;
//    private List<RecyclerView> mLists;
//    private ImageView cursor;// 动画图片
//    private float offset = 0;// 动画图片偏移量
//    private int currIndex = 0;// 当前页卡编号
//    private float bmpW;// 动画图片宽度
//    float one;
//    float two;
//
//
//    private List<DirectorBean> mDatas;
//    private DirectorListVPActivity mActivity;
//
//    @Override
//    protected DirectorListPresenterImpl initPresenter() {
//        return new DirectorListPresenterImpl();
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//    @Override
//    protected void initView() {
//        mActivity = this;
//        mPresenter.attach(this);
//        mViewPager = (ViewPager) findViewById(R.id.viewpager);
//        cursor = (ImageView) findViewById(R.id.cursor);
//        iv_back = (ImageButton) findViewById(R.id.iv_back);
//        iv_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DirectorListVPActivity.this.finish();
//            }
//        });
//        mPresenter.getDirectorList();
//    }
//
//    @Override
//    public int getLayoutID() {
//        return R.layout.activity_director_vplist;
//    }
//
//    /**
//     * ViewPager页面选项卡进行切换时候的监听器处理,判断当前页是哪一页
//     *
//     * @author jiangqingqing
//     */
//    class MyOnPageChanger implements ViewPager.OnPageChangeListener {
//        @Override
//        public void onPageScrollStateChanged(int arg0) {
//        }
//
//        @Override
//        public void onPageScrolled(int arg0, float arg1, int arg2) {
//
//        }
//
//        @Override
//        public void onPageSelected(int arg0) {
//            currIndex = arg0;//当前页设置为1
//            Animation animation = null;
//            float from = (offset * 2 + bmpW) * currIndex;
//            float to = (offset * 2 + bmpW) * mViewPager.getCurrentItem();
//            animation = new TranslateAnimation(from, to, 0, 0);
//            currIndex = mViewPager.getCurrentItem();
//            animation.setFillAfter(true);// True:图片停在动画结束位置
//            animation.setDuration(300);
//            cursor.startAnimation(animation);
//        }
//
//    }
//
//
//    private DirectorListAdapter mDirectorListAdapter;
//    private MyViewPagerAdapter adapter;
//    Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 40:
//                    mLists = new ArrayList<>();
//                    final int PageCount = (int) Math.ceil(mDatas.size() / 8.0f);
//                    for (int i = 0; i < PageCount; i++) {
//                        mRecyclerView = new RecyclerView(mActivity);
//                        mLayoutManager = new FocusGridLayoutManager(4, OrientationHelper.VERTICAL);
//                        mRecyclerView.setLayoutManager(mLayoutManager);
//                        mDirectorListAdapter = new DirectorListAdapter(mActivity, mDatas, i);
//                        mRecyclerView.setAdapter(mDirectorListAdapter);
//                        mRecyclerView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
//                        mLists.add(mRecyclerView);
//                        mDirectorListAdapter.setOnItemClickLitener(new DirectorListAdapter.OnItemClickLitener() {
//                            @Override
//                            public void onItemClick(View view, DirectorBean data) {
//                                int mid = data.getId();
//                                Intent intent = new Intent(DirectorListVPActivity.this, DirectorDetailActivity.class);
//                                intent.putExtra("directorid", mid);
//                                startActivity(intent);
//                            }
//                        });
//                    }
//                    mViewPager.addOnPageChangeListener(new MyOnPageChanger());
//                    adapter = new MyViewPagerAdapter(mActivity, mLists);
//                    mViewPager.setAdapter(adapter);
//
//// 计算总页数
//                    if (mDatas.size() % PAGE_SIZE == 0) {
//                        sTotalPages = mDatas.size() / PAGE_SIZE;
//                    } else {
//                        sTotalPages = mDatas.size() / PAGE_SIZE + 1;
//                    }
//                    initImage(sTotalPages);
//                    //    currIndex = 0;
//            }
//        }
//
//
//    };
//
//    private void initImage(int size) {
//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        float screenW = dm.widthPixels;// 获取分辨率宽度
//        screenW -= ImageLoaderUtils.dip2px(this, 210);
//        bmpW = (screenW / size) * (7.0f / 8.0f);
//        offset = (screenW / size - bmpW) / 2.0f;// 计算偏移量
//        RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams((int) bmpW, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        layout.setMargins((int) offset, 0, 0, 0);
//        this.cursor.setLayoutParams(layout);
//        Matrix matrix = new Matrix();
//        matrix.postTranslate(offset, 0);
//        this.cursor.setImageMatrix(matrix);// 设置动画初始位置
//        one = offset * 2.0f + bmpW;// 页卡1 -> 页卡2 偏移量
//        two = one;// 页卡1 -> 页卡2 偏移量
//    }
//
//    @Override
//    public void initDirectors(List<DirectorBean> list) {
//        mDatas = list;
//        mHandler.sendEmptyMessage(40);
//    }
//
//    @Override
//    public void showLoading() {
//
//    }
//
//    @Override
//    public void hideLoading() {
//
//    }
//
//    @Override
//    public void showMessage(String msg) {
//
//    }
//}
