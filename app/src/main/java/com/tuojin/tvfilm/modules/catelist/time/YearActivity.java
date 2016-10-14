package com.tuojin.tvfilm.modules.catelist.time;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.YearBean;
import com.tuojin.tvfilm.contract.YearContract;
import com.tuojin.tvfilm.keybord.FocusGridLayoutManager;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.OnItemClickListener;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.modules.main.FilmDetailActivity;
import com.tuojin.tvfilm.presenter.YearPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.id;

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
    RecyclerView mMainFragment;
    @BindView(R.id.iv_back)
    ImageButton mIvBack;
    @BindView(R.id.rv_menu)
    RecyclerView mRvMenu;
    @BindView(R.id.tab_container)
    LinearLayout mTabContainer;
    @BindView(R.id.title)
    TextView mTitle;
    private List<YearBean> mMenuList;
    private CommonAdapter<YearBean> mMenuAdapter;
    private CommonAdapter<FilmBean> mOtherAdapter;

    TextView btn = null;
    private LinearLayoutManager mLayout;

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
        mLayout = new LinearLayoutManager(this);
        mLayout.setOrientation(LinearLayoutManager.VERTICAL);
        mRvMenu.setLayoutManager(mLayout);

        FocusGridLayoutManager focusGridLayoutManager = new FocusGridLayoutManager(YearActivity.this, 5);
        focusGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMainFragment.setHasFixedSize(true);
        mMainFragment.setLayoutManager(focusGridLayoutManager);
        leftMenu();
    }

    private void leftMenu() {
        mPresenter.menu();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        this.finish();
    }

    /**
     * 网络访问，得到年份列表
     *
     * @param list
     */
    @Override
    public void initMenu(List<YearBean> list) {
        mMenuList = list;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                YearAdapter adapter = new YearAdapter();
                mRvMenu.setAdapter(adapter);
            }
        });
    }

    /**
     * 点击某项，显示列表。
     *
     * @param list
     */
    @Override
    public void initList(final List<FilmBean> list) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (btn != null) {
                    mTitle.setText("年份：" + btn.getText() + "\t共有" + list.size() + "部");
                    mOtherAdapter = new CommonAdapter<FilmBean>(mActivity, R.layout.item_other, list, 1) {
                        @Override
                        public void convert(ViewHolder holder, FilmBean bean) {
                            holder.setText(R.id.movie_title_other, bean.getMovie_name());
                            holder.setImageResource(R.id.movie_image_other, bean.getPoster());
                            holder.setScaleAnimation(R.id.movie_title_other);
                        }
                    };
                    mOtherAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                            Intent intent = new Intent(mActivity, FilmDetailActivity.class);
                            FilmBean bean = list.get(position);
                            intent.putExtra("film", bean);
                            startActivity(intent);
                        }
                    });
                    mMainFragment.setAdapter(mOtherAdapter);
                }
            }
        });
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
     */
    private void setHoverRight(int type) {
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
            View view = LayoutInflater.from(YearActivity.this).inflate(R.layout.item_radbtn, parent, false);
            YearViewHolder holder = new YearViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(YearViewHolder holder, int position) {
            final YearBean yearBean = mMenuList.get(position);
            holder.mRadbtnItem.setText(yearBean.getMovie_year());
            if (position == 0) {
                holder.mRadbtnItem.requestFocus();
                btn = holder.mRadbtnItem;
                mPresenter.list(47);
            }
            holder.mRadbtnItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn = (TextView) v;
                    mPresenter.list(yearBean.getId());
                    int firstVisibleItemPosition = mLayout.findFirstVisibleItemPosition();
                    int i = 0;
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
        TextView mRadbtnItem;

        YearViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
