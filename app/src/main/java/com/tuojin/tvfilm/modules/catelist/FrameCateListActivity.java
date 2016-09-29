package com.tuojin.tvfilm.modules.catelist;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.open.androidtvwidget.bridge.RecyclerViewBridge;
import com.open.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.open.androidtvwidget.leanback.recycle.GridLayoutManagerTV;
import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.open.androidtvwidget.view.MainUpView;
import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.contract.HotRecommContract;
import com.tuojin.tvfilm.modules.catelist.framecatelist.LeftMenuPresenter;
import com.tuojin.tvfilm.presenter.HotRecommPresenterImpl;

import butterknife.BindView;

/**
 * 文 件 名: FrameCateListActivity
 * 创 建 人: Administrator
 * 创建日期: 2016/9/27 11:24
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class FrameCateListActivity extends BaseActivity<HotRecommContract.View, HotRecommPresenterImpl> {

    @BindView(R.id.left_menu_rv)
    RecyclerViewTV mLeftMenuRv;
    @BindView(R.id.recyclerView)
    RecyclerViewTV mRecyclerView;
    @BindView(R.id.mainUpView1)
    MainUpView mMainUpView1;
    private RecyclerViewBridge mRecyclerViewBridge;
    private View oldView;

    @Override
    protected HotRecommPresenterImpl initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        mMainUpView1.setEffectBridge(new RecyclerViewBridge());
        mRecyclerViewBridge = (RecyclerViewBridge) mMainUpView1.getEffectBridge();
        mRecyclerViewBridge.setUpRectResource(R.drawable.test_rectangle);
        //初始化左侧菜单
        iniLeftMenu();
    }

    private void iniLeftMenu() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLeftMenuRv.setLayoutManager(linearLayoutManager);
        mLeftMenuRv.setFocusable(false);
        GeneralAdapter generalAdapter=new GeneralAdapter(new LeftMenuPresenter());
        mLeftMenuRv.setAdapter(generalAdapter);
        mLeftMenuRv.setOnItemListener(new RecyclerViewTV.OnItemListener() {
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
        mLeftMenuRv.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
                // 测试.
                mRecyclerViewBridge.setFocusView(itemView, oldView, 1.0f);
                oldView = itemView;
                //
                onViewItemClick(itemView, position);
            }
        });
    }

    private void onViewItemClick(View itemView, int position) {
        switch (position) {
            case 0:
                testRecyclerViewGridLayout(GridLayoutManager.VERTICAL);
                break;
            case 1:
                testRecyclerViewGridLayout(GridLayoutManager.VERTICAL);
                break;
            case 2:
                testRecyclerViewGridLayout(GridLayoutManager.VERTICAL);
                break;
            case 3:
                testRecyclerViewGridLayout(GridLayoutManager.VERTICAL);
                break;
            case 4:
                testRecyclerViewGridLayout(GridLayoutManager.VERTICAL);
                break;

        }
    }

    private void testRecyclerViewGridLayout(int orientation) {
        GridLayoutManagerTV gridlayoutManager = new GridLayoutManagerTV(this, 4); // 解决快速长按焦点丢失问题.
        gridlayoutManager.setOrientation(orientation);
        mRecyclerView.setLayoutManager(gridlayoutManager);
        mRecyclerView.setFocusable(false);
//        GeneralAdapter generalAdapter = new GeneralAdapter(new RecyclerViewPresenter(100));
//        mRecyclerView.setAdapter(generalAdapter);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_frame_catelist;
    }

}
