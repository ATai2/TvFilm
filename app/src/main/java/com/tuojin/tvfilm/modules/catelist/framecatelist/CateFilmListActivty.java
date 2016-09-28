package com.tuojin.tvfilm.modules.catelist.framecatelist;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.bean.DirectListBean;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.contract.CateListFilmContract;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.OnItemClickListener;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.presenter.CateListFilmPresenterImpl;
import com.tuojin.tvfilm.utils.Constant;
import com.tuojin.tvfilm.utils.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 文 件 名: CateFilmListActivty
 * 创 建 人: Administrator
 * 创建日期: 2016/9/27 13:06
 * 文件描述：  radiobutton 限定个数为6个，在网络请求中限定，加载更多时换页。
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class CateFilmListActivty extends BaseActivity<CateListFilmContract.View, CateListFilmPresenterImpl> implements
        CateListFilmContract.View, Constant {

    @BindView(R.id.index_type)
    TextView mIndexType;

    @BindView(R.id.rv_radbs_recy)
    RecyclerView mRvRadbsRecy;
    @BindView(R.id.tab_container)
    LinearLayout mTabContainer;
    @BindView(R.id.rv_filmlist)
    RecyclerView mRvFilmlist;
    @BindView(R.id.tab_indicator_search)
    RadioButton mTabIndicatorSearch;
    @BindView(R.id.tv_name)
    TextView mTvName;
    private int type;
    private int mPosition;
    private List<DirectListBean.DataBean.DirectorBean> mDirectorBeanList;
    private int mChildAdapterPosition;
    private List<FilmBean> mFilmBeanList;
    private CommonAdapter<DirectListBean.DataBean.DirectorBean> mAdapter;
    private CommonAdapter<FilmBean> mFilmBeanCommonAdapter;

    @Override
    protected CateListFilmPresenterImpl initPresenter() {
        return new CateListFilmPresenterImpl();
    }

    @Override
    protected void initData() {

    }

    /**
     * 初始化左侧菜单，同时强制第一个可获得焦点控件获得焦点，避免RV焦点乱跳
     */
    @Override
    protected void initView() {
        mPosition = getIntent().getIntExtra("position", 0);
        mPresenter.attach(this);
        mPresenter.initRadioGroup(mPosition);
        String type = "";
        switch (mPosition) {
            case 0:
                type = "电影类型";
                break;
            case 1:
                type = "电影年份";
                break;
            case 2:
                type = "直播列表";
                break;
            case 3:
                type = "地区列表";
                break;
            case 4:
                type = "导演列表";
                break;
            case 5:
                type = "演员列表";
                break;
        }
        mTvName.setText(type);

        mTabIndicatorSearch.requestFocus();
//        mRvRadbsRecy.requestFocus();

        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        mRvRadbsRecy.setLayoutManager(layout);
        mRvRadbsRecy.setHasFixedSize(true);

        StaggeredGridLayoutManager staggerlm = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mRvFilmlist.setLayoutManager(staggerlm);
        mRvFilmlist.setHasFixedSize(true);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_cate_filmlist;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.dettach();
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


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    initDirector();
                    break;
                case CFLA_INITLIST:
                    initFilmList();
            }
        }
    };

    private void initFilmList() {
        mFilmBeanCommonAdapter = new CommonAdapter<FilmBean>(this,R.layout.item_recomm,mFilmBeanList,0){

            @Override
            public void convert(ViewHolder holder, FilmBean bean) {
                holder.setText(R.id.tv_recomm,bean.getMovie_name());
                holder.setImageResource(R.id.iv_recomm,bean.getPoster());
            }
        };
        mRvFilmlist.setAdapter(mFilmBeanCommonAdapter);

    }

    private void initDirector() {
//        for (int i = 0; i < mDirectorBeanList.size(); i++) {
//            RadioButton radioButton = new RadioButton(this);
//            DirectListBean.DataBean.DirectorBean directorBean = mDirectorBeanList.get(i);
//            radioButton.setText(directorBean.getMovie_director());
//            radioButton.setTag(directorBean);
//            radioButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
//            mTabContainer.addView(radioButton);
//        }
//        int i = 0;

        mAdapter = new CommonAdapter<DirectListBean.DataBean.DirectorBean>(this,
                R.layout.item_radbtn, mDirectorBeanList, 0) {
            @Override
            public void convert(ViewHolder holder, DirectListBean.DataBean.DirectorBean directorBean) {
                holder.setText(R.id.radbtn_item, directorBean.getMovie_director());
            }
        };
        mRvRadbsRecy.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener<DirectListBean.DataBean.DirectorBean>() {

            @Override
            public void onItemClick(ViewGroup parent, View view, DirectListBean.DataBean.DirectorBean directBean, int position) {
                LogUtils.d("12","onItemClick");
                mPresenter.onItemClick(CateFilmListActivty.this, directBean);
            }
        });

    }

    @Override
    public void initViewRadioGroup(List<DirectListBean.DataBean.DirectorBean> list) {
        mDirectorBeanList = list;
        mHandler.sendEmptyMessage(0);
    }

    @Override
    public void initDirectorFragment(List<FilmBean> data1) {
        mFilmBeanList = data1;
        mHandler.sendEmptyMessage(CFLA_INITLIST);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_DPAD_LEFT) {


        }



        return super.onKeyDown(keyCode, event);

    }

    @OnClick(R.id.tab_indicator_search)
    public void onClick() {
        this.finish();
    }
}
