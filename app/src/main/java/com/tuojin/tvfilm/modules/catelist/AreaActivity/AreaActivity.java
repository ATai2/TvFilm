package com.tuojin.tvfilm.modules.catelist.AreaActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.bean.AreaBean;
import com.tuojin.tvfilm.contract.AreaContract;
import com.tuojin.tvfilm.keybord.FocusGridLayoutManager;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.OnItemClickListener;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.presenter.AreaPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文 件 名: AreaActivity
 * 创 建 人: Administrator
 * 创建日期: 2016/10/9 13:49
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class AreaActivity extends BaseActivity<AreaContract.View, AreaPresenterImpl> implements AreaContract.View {
    @BindView(R.id.index_type)
    TextView mIndexType;
    @BindView(R.id.main_fragment)
    RecyclerView mMainFragment;
    @BindView(R.id.tab_indicator_search)
    RadioButton mTabIndicatorSearch;
    @BindView(R.id.rv_menu)
    RecyclerView mRvMenu;
    private List<String> mMenuList;
    private CommonAdapter<String> mMenuAdapter;
    private List<AreaBean> mList=new ArrayList<>();

    @Override
    protected AreaPresenterImpl initPresenter() {
        return new AreaPresenterImpl();
    }

    @Override
    protected void initData() {

    }

    /**
     * 初始化2个RV的布局。
     */
    @Override
    protected void initView() {
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        mRvMenu.setLayoutManager(layout);

        FocusGridLayoutManager focusGridLayoutManager = new FocusGridLayoutManager(mActivity, 5);
        focusGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMainFragment.setLayoutManager(focusGridLayoutManager);

        mMenuList = new ArrayList<>();
        for (int i = 'A'; i <='Z' ; i++) {
           mMenuList.add(String.valueOf((char)i));
        }

        mMenuAdapter = new CommonAdapter<String>(this, R.layout.item_radbtn, mMenuList, 0) {
            @Override
            public void convert(ViewHolder holder, String s) {
                holder.setRadioButtonText(R.id.radbtn_item,s);
            }
        };
        mRvMenu.setAdapter(mMenuAdapter);

        CommonAdapter<AreaBean> mAdapter=new CommonAdapter<AreaBean>(this,R.layout.item_other,mList,0) {
            @Override
            public void convert(ViewHolder holder, AreaBean areaBean) {
                holder.setText(R.id.movie_title_other,areaBean.getMovie_country() );
                holder.setImageResourceNoMID(R.id.movie_image_other, areaBean.getImg());
                holder.setScaleAnimation(R.id.movie_title_other);
            }
        };
//        按首字母检索？？？？？？
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                RadioButton view1 = (RadioButton) view;
            }
        });
        mMainFragment.setAdapter(mAdapter);

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_area;
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
}
