package com.tuojin.tvfilm.modules.catelist.framecatelist;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.bean.DirectorBean;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.FilmTypeBean;
import com.tuojin.tvfilm.contract.CateListFilmContract;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.modules.catelist.framecatelist.adapter.RabDirectorAdapter;
import com.tuojin.tvfilm.modules.catelist.framecatelist.adapter.RabTypeAdapter;
import com.tuojin.tvfilm.presenter.CateListFilmPresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文 件 名: CateFilmListActivty
 * 创 建 人: Administrator
 * 创建日期: 2016/9/27 13:06
 * 文件描述：  radiobutton 限定个数为6个，在网络请求中限定，加载更多时换页。--done
 * 使用listview问题已解决
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
    ListView mRvRadbsRecy;
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
    private List<DirectorBean> mDirectorBeanList;
    private int mChildAdapterPosition;
    private List<FilmBean> mFilmBeanList;
    private CommonAdapter<DirectorBean> mAdapter;
    private CommonAdapter<FilmBean> mFilmBeanCommonAdapter;
    private DirectorRadAdapter mBtnAdapter;
    private RabDirectorAdapter mRabDirectorAdapter;
    private RadioButton mCurrentRadioBtn;
    private RabTypeAdapter mRabTypeAdapter;
    private List<FilmTypeBean> mTypeList;


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

//        LinearLayoutManager layout = new LinearLayoutManager(this);
//        layout.setOrientation(LinearLayoutManager.VERTICAL);
//        mRvRadbsRecy.setLayoutManager(layout);
//        mRvRadbsRecy.setHasFixedSize(true);


        mRvRadbsRecy.setItemsCanFocus(true);//避免多焦点
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
        mFilmBeanCommonAdapter = new CommonAdapter<FilmBean>(this, R.layout.item_recomm, mFilmBeanList, 0) {

            @Override
            public void convert(ViewHolder holder, FilmBean bean) {
                holder.setText(R.id.tv_recomm, bean.getMovie_name());
                holder.setImageResource(R.id.iv_recomm, bean.getPoster());
            }
        };
        mRvFilmlist.setAdapter(mFilmBeanCommonAdapter);

    }

    private void initDirector() {
        mRabDirectorAdapter = new RabDirectorAdapter(mDirectorBeanList, this, mPresenter);
        mRvRadbsRecy.setAdapter(mRabDirectorAdapter);
    }

    private void initType() {
        new RabTypeAdapter(mTypeList, this, mPresenter);
        mRvRadbsRecy.setAdapter(mRabTypeAdapter);
    }

    @Override
    public void initViewRadioGroup(List<DirectorBean> list) {
        mDirectorBeanList = list;
        mHandler.sendEmptyMessage(0);
    }

    @Override
    public void initFilmFragment(List<FilmBean> data1) {
        mFilmBeanList = data1;
        mHandler.sendEmptyMessage(CFLA_INITLIST);
    }

    @Override
    public void refreshUIs() {

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            Toast.makeText(this, "lkdslfj", Toast.LENGTH_SHORT).show();
            int i = 0;


            mCurrentRadioBtn.requestFocus();


        }
//判断确定键按下，同时是RadioButton中获得焦点。
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            Toast.makeText(this, "center", Toast.LENGTH_SHORT).show();

            int i = 0;
        }

        return super.onKeyDown(keyCode, event);

    }

    @OnClick(R.id.tab_indicator_search)
    public void onClick() {
        this.finish();
    }
}
