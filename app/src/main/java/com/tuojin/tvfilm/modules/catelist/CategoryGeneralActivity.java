package com.tuojin.tvfilm.modules.catelist;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.bridge.RecyclerViewBridge;
import com.open.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.open.androidtvwidget.view.MainUpView;
import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.contract.CateListContract;
import com.tuojin.tvfilm.presenter.CateListPresenterImpl;
import com.tuojin.tvfilm.utils.LogUtils;

import java.util.List;

import butterknife.BindView;

/**
 * 文 件 名: CategoryGeneralActivity
 * 创 建 人: Administrator
 * 创建日期: 2016/9/26 12:50
 * 文件描述：  使用通用类，逻辑较为复杂：
 * 1.   首先是左侧的菜单列进行初始化。
 * 2.   右侧根据左侧的菜单数据更新。
 *
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class CategoryGeneralActivity<T> extends BaseActivity<CateListContract.View<T>, CateListPresenterImpl<T>>
        implements CateListContract.View {


    @BindView(R.id.tab_indicator_search)
    RadioButton mTabIndicatorSearch;
    @BindView(R.id.tab_indicator_screening)
    RadioButton mTabIndicatorScreening;
    @BindView(R.id.rv_radbs)
    RecyclerViewTV mRvRadbs;
    @BindView(R.id.tab_container_category)
    LinearLayout mTabContainerCategory;
    @BindView(R.id.index_type)
    TextView mIndexType;
    @BindView(R.id.recyclerView)
    RecyclerViewTV mRecyclerView;
    @BindView(R.id.mainUpView1)
    MainUpView mainUpView1;
    private int mPosition;
    private List<T> mList;
    private RecyclerViewBridge mRecyclerViewBridge;
    private RecyclerViewBridge mEffectBridge;
    private View oldView;

    @Override
    protected CateListPresenterImpl initPresenter() {
        return new CateListPresenterImpl();
    }

    @Override
    protected void initData() {
        mPosition = getIntent().getIntExtra("position", 0);//判断此次为什么类型的列表
    }

    @Override
    protected void initView() {
        // 建议使用 NoDraw.
        mainUpView1.setEffectBridge(new EffectNoDrawBridge());
//        EffectNoDrawBridge bridget = (EffectNoDrawBridge) mainUpView1.getEffectBridge();
//        bridget.setTranDurAnimTime(200);
        // 设置移动边框的图片.
//        mainUpView1.setUpRectResource(R.drawable.white_light_10);
        // 移动方框缩小的距离.
//        mainUpView1.setDrawUpRectPadding(new Rect(10, 10, 10, -55));

        mEffectBridge = (RecyclerViewBridge) mainUpView1.getEffectBridge();
        mRecyclerViewBridge.setUpRectResource(R.drawable.test_rectangle);
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

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                //将得到的列表初始化
                for (int i = 0; i < mList.size(); i++) {
                    GeneralAdapter generalAdapter = new GeneralAdapter(new LeftMenuPresenter(mList));
                    mRvRadbs.setAdapter(generalAdapter);
                    mRvRadbs.setOnItemListener(new RecyclerViewTV.OnItemListener() {
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

                        @Override
                        public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
                            mRecyclerViewBridge.setFocusView(itemView, 1.0f);
                            oldView = itemView;
                        }
                    });
                    mRvRadbs.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
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
            }


            //右侧点击进入电影详情页。   ===放在外面
            mRecyclerView.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
                @Override
                public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
                    LogUtils.d("12"," mRecyclerView.setOnItemClickListener(");
                    //网络请求获得此电影的详情，
                    // 进入详情页
                }
            });
        }
    };

    /**
     * 左侧radiobutton点击，右侧recycleview数据更新。
     *
     * @param itemView
     * @param position
     */
    private void onViewItemClick(View itemView, int position) {

    }

    /**
     * 更新左侧菜单
     * @param directorBeanList
     */
    @Override
    public void initViewRadioButton(List directorBeanList) {
        mList = directorBeanList;
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String msg) {

    }
}
