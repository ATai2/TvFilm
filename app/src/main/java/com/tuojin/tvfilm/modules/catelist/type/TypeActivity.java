package com.tuojin.tvfilm.modules.catelist.type;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.FilmTypeBean;
import com.tuojin.tvfilm.contract.FilmTypeContract;
import com.tuojin.tvfilm.keybord.FocusGridLayoutManager;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.OnItemClickListener;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.modules.main.FilmDetailActivity;
import com.tuojin.tvfilm.presenter.FilmTypePresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 文 件 名: TypeActivity
 * 创 建 人: Administrator
 * 创建日期: 2016/10/17 15:14
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class TypeActivity extends BaseActivity<FilmTypeContract.View, FilmTypePresenterImpl> implements FilmTypeContract.View {
    @BindView(R.id.iv_back)
    ImageButton mIvBack;
    @BindView(R.id.title_topbar)
    TextView mTitleTopbar;
    @BindView(R.id.index_type)
    TextView mIndexType;
    @BindView(R.id.main_fragment)
    RecyclerView mMainFragment;
    @BindView(R.id.tv_menutitle)
    TextView mTvMenutitle;
    @BindView(R.id.iv_up)
    ImageView mIvUp;
    @BindView(R.id.rv_menu)
    RecyclerView mRvMenu;
    @BindView(R.id.iv_down)
    ImageView mIvDown;
    @BindView(R.id.tab_container)
    LinearLayout mTabContainer;
    private FocusGridLayoutManager mGridLayoutManager;
    private LinearLayoutManager mLayout;
    private List<FilmTypeBean> mMenuList;
    TextView btn = null;
    private List<FilmBean> mData;
    private CommonAdapter<FilmBean> mOtherAdapter;
    private List<FilmBean> mFilmBeen;
    private FilmBean mValue;

    @Override
    protected FilmTypePresenterImpl initPresenter() {
        return new FilmTypePresenterImpl();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mPresenter.attach(this);
        mTvMenutitle.setText("电影类型");
        mLayout = new LinearLayoutManager(this);
        mLayout.setOrientation(LinearLayoutManager.VERTICAL);
        mRvMenu.setLayoutManager(mLayout);

        mGridLayoutManager = new FocusGridLayoutManager(mActivity, 5);
        mGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMainFragment.setHasFixedSize(true);
        mMainFragment.setLayoutManager(mGridLayoutManager);
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
    public void initFilmFragment(List<FilmBean> data) {
        mFilmBeen = data;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTitleTopbar.setText(btn.getText() + "共有" + mFilmBeen.size() + "部影片");
                mOtherAdapter = new CommonAdapter<FilmBean>(mActivity, R.layout.item_other, mFilmBeen, 1) {
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
                        mValue = mFilmBeen.get(position);
                        intent.putExtra("film", mValue);
                        startActivity(intent);
                    }
                });
                mMainFragment.setAdapter(mOtherAdapter);
            }
        });
    }

    @Override
    public void initMenu(List<FilmTypeBean> list) {
        mMenuList = list;
        YearAdapter yearAdapter = new YearAdapter();
        mRvMenu.setAdapter(yearAdapter);
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        this.finish();
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

    class YearAdapter extends RecyclerView.Adapter<TypeActivity.YearViewHolder> {
        @Override
        public TypeActivity.YearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(TypeActivity.this).inflate(R.layout.item_radbtn, parent, false);
            TypeActivity.YearViewHolder holder = new TypeActivity.YearViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(TypeActivity.YearViewHolder holder, int position) {
            final FilmTypeBean yearBean = mMenuList.get(position);
            holder.mRadbtnItem.setText(yearBean.getMovieType());
            if (position == 0) {
                holder.mRadbtnItem.requestFocus();
                btn = holder.mRadbtnItem;
                mPresenter.onClick(yearBean.getId());
            }
            holder.mRadbtnItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn = (TextView) v;
                    mPresenter.onClick(yearBean.getId());
                }
            });
        }

        @Override
        public int getItemCount() {
            return mMenuList.size();
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
