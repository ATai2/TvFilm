package com.tuojin.tvfilm.modules.catelist.area;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.bean.AreaBean;
import com.tuojin.tvfilm.contract.AreaContract;
import com.tuojin.tvfilm.keybord.FocusGridLayoutManager;
import com.tuojin.tvfilm.modules.catelist.FilmListActivity;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.OnItemClickListener;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.presenter.AreaPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    List<AreaBean> mList = new ArrayList<>();
    @BindView(R.id.iv_back)
    ImageButton mIvBack;
    @BindView(R.id.title_topbar)
    TextView mTitle;
    @BindView(R.id.index_type)
    TextView mIndexType;
    @BindView(R.id.main_fragment)
    RecyclerView mMainFragment;
    @BindView(R.id.tv_menutitle)
    TextView mTvMenutitle;
    @BindView(R.id.rv_menu)
    RecyclerView mRvMenu;
    @BindView(R.id.tab_container)
    LinearLayout mTabContainer;
    TextView btn = null;


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
        mPresenter.attach(this);
        mTvMenutitle.setText("地区选择");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvMenu.setLayoutManager(layoutManager);

        FocusGridLayoutManager focusGridLayoutManager = new FocusGridLayoutManager(mActivity, 5);
        focusGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMainFragment.setLayoutManager(focusGridLayoutManager);
        mRvMenu.setAdapter(new AtoZAdapter());
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_year;
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String msg) {

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        View focusedChild = mMainFragment.getFocusedChild();
        int childLayoutPosition = mMainFragment.getChildLayoutPosition(focusedChild);
        if (mMainFragment.hasFocus() && keyCode == KeyEvent.KEYCODE_DPAD_LEFT && (childLayoutPosition % 5 == 0 || childLayoutPosition % 5 == 5)) {
            //判断哪个获得焦点
            if (btn != null)
                btn.requestFocus();
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        this.finish();
    }

    @Override
    public void initList(List<AreaBean> list) {
        mList=list;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CommonAdapter<AreaBean> mAdapter = new CommonAdapter<AreaBean>(AreaActivity.this, R.layout.item_other_fitsize, mList, 0) {
                    @Override
                    public void convert(ViewHolder holder, AreaBean areaBean) {
                        holder.setText(R.id.movie_title_other, areaBean.getMovie_country());
                        holder.setImageResourceNoMID(R.id.movie_image_other, areaBean.getImg());
                        holder.setScaleAnimation(R.id.movie_title_other);
                    }
                };
                mAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                        mPresenter.listByArea(mList.get(position).getId(),mList.get(position).getMovie_country());
                    }
                });
                mMainFragment.setAdapter(mAdapter);
            }
        });
    }
    @Override
    public void initListByArea( String data,String s) {
        Intent intent=new Intent(this, FilmListActivity.class);
        intent.putExtra("data",data);
        intent.putExtra("type",s);
        startActivity(intent);
    }

    class AtoZAdapter extends RecyclerView.Adapter<AtoZAdapter.ViewHolder> {
        List<String> mMenuList;

        public AtoZAdapter() {
            mMenuList = new ArrayList<>();
            for (int i = 'A'; i <= 'Z'; i++) {
                mMenuList.add(String.valueOf((char) i));
            }
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(AreaActivity.this).inflate(R.layout.item_radbtn, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.mRadbtnItem.setText(mMenuList.get(position));
            if (position == 0) {
                holder.mRadbtnItem.requestFocus();
                btn = holder.mRadbtnItem;
                mPresenter.list("A");
            }
            holder.mRadbtnItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn = (TextView) v;
                    mPresenter.list(btn.getText().toString());
                }
            });
        }

        @Override
        public int getItemCount() {
            return mMenuList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.radbtn_item)
            TextView mRadbtnItem;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

//    public class AViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.radbtn_item)
//        TextView mRadbtnItem;
//
//        AViewHolder(View view) {
//            super(view);
//            ButterKnife.bind(this, view);
//        }
//    }
}

