package com.tuojin.tvfilm.modules.catelist.time;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.bean.AreaBean;
import com.tuojin.tvfilm.bean.YearBean;
import com.tuojin.tvfilm.contract.YearContract;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.presenter.YearPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 文 件 名: YearActivity
 * 创 建 人: Administrator
 * 创建日期: 2016/10/11 16:47
 * 文件描述： 注意动态添加按钮的时候，怎么处理焦点变化的情况
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class YearActivity extends BaseActivity<YearContract.View, YearPresenterImpl> implements YearContract.View {
    @BindView(R.id.index_type)
    TextView mIndexType;
    @BindView(R.id.main_fragment)
    FrameLayout mMainFragment;
    @BindView(R.id.iv_back)
    ImageButton mIvBack;
    @BindView(R.id.rv_menu)
    RecyclerView mRvMenu;
    @BindView(R.id.tab_container)
    LinearLayout mTabContainer;
    private List<YearBean> mMenuList;
    private CommonAdapter<YearBean> mMenuAdapter;
    private CommonAdapter<AreaBean> mAdapter;
    SparseArray<FilmListFragment> fragments = new SparseArray<>();
    private FragmentManager mFragmentManager;

    @Override
    protected YearPresenterImpl initPresenter() {
        return new YearPresenterImpl();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mPresenter.attach(this);
        mFragmentManager = getSupportFragmentManager();
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        mRvMenu.setLayoutManager(layout);
        leftMenu();
    }

    private void leftMenu() {
        mPresenter.menu();
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

    @OnClick(R.id.iv_back)
    public void onClick() {
        this.finish();
    }

    @Override
    public void initMenu(List<YearBean> list) {
        mMenuList = list;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                mMenuAdapter = new CommonAdapter<YearBean>(YearActivity.this, R.layout.item_radbtn, mMenuList, 0) {
//                    @Override
//                    public void convert(ViewHolder holder, YearBean s) {
//                        holder.setRadioButtonText(R.id.radbtn_item, s.getMovie_year());
//
//                    }
//                };
//                mMenuAdapter.setOnItemClickListener(new OnItemClickListener() {
//                    @Override
//                    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
//
//                        int id = view.getId();
//                        currentRadioId = id;
//                        mPresenter.list(mMenuList.get(position).getId(), id);
//                    }
//                });
                mRvMenu.setAdapter(new YearAdapter());
            }
        });
    }

    /**
     * 点击某项，显示列表。
     *
     * @param list
     * @param id
     */
    @Override
    public void initList(String list, int id) {
        Bundle bundle = new Bundle();
//        向fragment中传递参数，同时设置焦点向左的方向
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragment(transaction);
        if (fragments.get(id) == null) {
            FilmListFragment frag = new FilmListFragment();
            frag.setArguments(bundle);
//            bundle.
            bundle.putString("json", list);
            fragments.put(id, frag);
            transaction.add(R.id.main_fragment, frag, id + "");
        } else {
            transaction.show(fragments.get(id));
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        for (int i = 0; i < fragments.size(); i++) {
            int i1 = fragments.keyAt(i);
            transaction.hide(fragments.get(i1));
//            transaction.commit();
        }
    }

    int currentRadioId = 0;
    RadioButton btn=null;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mMainFragment.hasFocus() && keyCode == event.KEYCODE_DPAD_LEFT) {
            //判断哪个获得焦点
            //setHoverRight(ONE, currentRadioId);
            btn.requestFocus();
        }
        if (mIvBack.hasFocus() && keyCode == event.KEYCODE_DPAD_DOWN) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) mRvMenu.getLayoutManager();
            int firstVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
            mRvMenu.getChildAt(firstVisibleItemPosition).requestFocus();
//            mRvMenu.requestFocus();
        }

        return super.onKeyDown(keyCode, event);
    }

    public static final int ONE = 0;
    public static final int TWO = 1;

    //当焦点frgment移动至RadioGroup时 指明移动到哪一个RadioButton ID上
    private void setLeftButtonFocus(int id, int type) {
        if (type == ONE) {
            mMainFragment.findFocus().setNextFocusLeftId(id);
        } else if (type == TWO) {
            mMainFragment.findFocus().setNextFocusUpId(id);
        }
    }

    /**
     * 焦点设置
     *
     * @param type
     * @param id
     */
    private void setHoverRight(int type, int id) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                if (fragment.isVisible()) {
                    setLeftButtonFocus(id, type);
//                    if (fragment.getTag() == FRAGMENT_TAG_C) {
//                        setLeftButtonFocus(R.id.tab_indicator_new, type);
//                    } else if (fragment.getTag() == FRAGMENT_TAG_D) {
//                        setLeftButtonFocus(R.id.tab_indicator_hot, type);
//                    } else if (fragment.getTag() == FRAGMENT_TAG_E) {
//                        setLeftButtonFocus(R.id.tab_indicator_big, type);
//                    } else if (fragment.getTag() == FRAGMENT_TAG_F) {
//                        setLeftButtonFocus(R.id.tab_indicator_ad, type);
//                    } else if (fragment.getTag() == FRAGMENT_TAG_G) {
//                        setLeftButtonFocus(R.id.tab_indicator_douban, type);
//                    }
                }
            }
        }
    }

    class YearAdapter extends RecyclerView.Adapter<YearViewHolder> {
        @Override
        public YearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(YearActivity.this).inflate(R.layout.item_radbtn,parent,false);
            YearViewHolder holder=new YearViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(YearViewHolder holder, int position) {
            final YearBean yearBean = mMenuList.get(position);
            holder.mRadbtnItem.setText(yearBean.getMovie_year());
            holder.mRadbtnItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    currentRadioId = id;
                    btn= (RadioButton) v;
                    mPresenter.list(yearBean.getId(), id);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mMenuList.size();
        }

        class ViewHolder {
            @BindView(R.id.radbtn_item)
            RadioButton mRadbtnItem;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    public class YearViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.radbtn_item)
        RadioButton mRadbtnItem;

        YearViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}
