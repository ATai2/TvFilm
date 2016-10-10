package com.tuojin.tvfilm.modules.catelist;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.open.androidtvwidget.bridge.RecyclerViewBridge;
import com.open.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.open.androidtvwidget.leanback.recycle.GridLayoutManagerTV;
import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.open.androidtvwidget.view.MainUpView;
import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.bean.DirectorBean;
import com.tuojin.tvfilm.contract.DirectorListContract;
import com.tuojin.tvfilm.modules.catelist.adpter.RecycleviewPresenter;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.presenter.DirectorListPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 文 件 名: DirectorListActivity
 * 创 建 人: Administrator
 * 创建日期: 2016/9/29 13:55
 * 文件描述：采用tv-widget框架。
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class DirectorListActivity extends BaseActivity<DirectorListContract.View, DirectorListPresenterImpl>
        implements DirectorListContract.View, RecyclerViewTV.OnItemListener {

    private RecyclerViewTV left_menu_rv; // 左侧菜单.
    private RecyclerViewTV mRecyclerView;
    private MainUpView mainUpView1;
    private RecyclerViewBridge mRecyclerViewBridge;
    private View oldView;
    private Context mContext;

    private List<DirectorBean> mList;
    private CommonAdapter<DirectorBean> mCommonAdapter;

    @Override
    protected DirectorListPresenterImpl initPresenter() {
        return new DirectorListPresenterImpl();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
//        FocusGridLayoutManager focusGridLayoutManager = new FocusGridLayoutManager(this, 5);
//        focusGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
////        GridLayoutManager lm=new GridLayoutManager(this,4);
////        lm.setOrientation(LinearLayoutManager.VERTICAL);
////
////        mMainFragment.setHasFixedSize(true);
////        mMainFragment.setLayoutManager(focusGridLayoutManager);
//        mPresenter.attach(this);
//        mList = new ArrayList<>();
//
//
//        gridView = (GridView) findViewById(R.id.gridView);
//        mainUpView1 = (MainUpView) findViewById(mainUpView1);
//        // 建议使用 NoDraw.
//        mainUpView1.setEffectBridge(new EffectNoDrawBridge());
//        EffectNoDrawBridge bridget = (EffectNoDrawBridge) mainUpView1.getEffectBridge();
//        bridget.setTranDurAnimTime(200);
//        // 设置移动边框的图片.
//        mainUpView1.setUpRectResource(R.drawable.white_light_10);
//        // 移动方框缩小的距离.
//        mainUpView1.setDrawUpRectPadding(new Rect(10, 10, 10, -55));
//        // 加载数据.
        mContext = DirectorListActivity.this;
        left_menu_rv = (RecyclerViewTV) findViewById(R.id.left_menu_rv);
        mRecyclerView = (RecyclerViewTV) findViewById(R.id.recyclerView);
        mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
        mainUpView1.setEffectBridge(new RecyclerViewBridge());
        // 注意这里，需要使用 RecyclerViewBridge 的移动边框 Bridge.
        mRecyclerViewBridge = (RecyclerViewBridge) mainUpView1.getEffectBridge();
        mRecyclerViewBridge.setUpRectResource(R.drawable.test_rectangle);
        // 初始化左侧菜单.
        initLeftMenu();

        GridLayoutManagerTV gridlayoutManager = new GridLayoutManagerTV(this, 4); // 解决快速长按焦点丢失问题.
        gridlayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridlayoutManager);
        mRecyclerView.setFocusable(false);

//        GeneralAdapter generalAdapter = new GeneralAdapter(new RecyclerViewPresenter(100));
        mPresenter.attach(this);
        mPresenter.getDirectorList();

        mRecyclerView.setOnItemListener(this);

        // item 单击事件处理.
        mRecyclerView.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
                Toast.makeText(mContext, "dianji", Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(D);
            }
        });
    }

    //左侧菜单初始化
    private void initLeftMenu() {
        List<String> listLeft=new ArrayList<>();
        for (int i = 'a'; i <='z' ; i++) {
            listLeft.add(Character.toString((char) i));
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        left_menu_rv.setLayoutManager(layoutManager);
        left_menu_rv.setFocusable(false);
        GeneralAdapter generalAdapter = new GeneralAdapter(new LeftMenuPresenter<String>(listLeft));
        left_menu_rv.setAdapter(generalAdapter);
        left_menu_rv.setOnItemListener(new RecyclerViewTV.OnItemListener() {
            @Override
            public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
                // 传入 itemView也可以, 自己保存的 oldView也可以.
                mRecyclerViewBridge.setUnFocusView(itemView);
            }

            @Override
            public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
                mRecyclerViewBridge.setFocusView(itemView, 1.0f);
                oldView = itemView;
            }

            /**
             * 这里是调整开头和结尾的移动边框.
             */
            @Override
            public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
                mRecyclerViewBridge.setFocusView(itemView, 1.0f);
                oldView = itemView;
            }
        });
        left_menu_rv.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
                // 测试.
                mRecyclerViewBridge.setFocusView(itemView, oldView, 1.0f);
                oldView = itemView;
                //
//                onViewItemClick(itemView, position);
            }
        });
    }

    @Override
    public int getLayoutID() {
        return R.layout.acitvity_director_list;
    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 40:
                    GeneralAdapter generalAdapter = new GeneralAdapter(new RecycleviewPresenter(DirectorListActivity.this, mList));
                    mRecyclerView.setAdapter(generalAdapter);
            }
        }
    };

    @Override
    public void initDirectors(List<DirectorBean> list) {
        mList = list;
        mHandler.sendEmptyMessage(40);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
        mRecyclerViewBridge.setUnFocusView(oldView);
    }

    @Override
    public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
        mRecyclerViewBridge.setFocusView(itemView, 1.2f);
        oldView = itemView;
    }

    @Override
    public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
        mRecyclerViewBridge.setFocusView(itemView, 1.2f);
        oldView = itemView;
    }
}
