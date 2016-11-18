package com.tuojin.tvfilm.modules.main;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.SPUtils;
import com.google.gson.Gson;
import com.litesuits.orm.LiteOrm;
import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.base.BaseApplication;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.bean.LiteFilmCollectionBean;
import com.tuojin.tvfilm.bean.TerminalBean;
import com.tuojin.tvfilm.bean.TerminalListBean;
import com.tuojin.tvfilm.contract.HotRecommContract;
import com.tuojin.tvfilm.event.TerminalBindEvent;
import com.tuojin.tvfilm.event.TerminalListEvent;
import com.tuojin.tvfilm.modules.catelist.FilmListActivity;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.OnItemClickListener;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.modules.main.album.AlbumFragment;
import com.tuojin.tvfilm.modules.main.category.CategoryFragment;
import com.tuojin.tvfilm.modules.main.hotrecomm.RecommFragment;
import com.tuojin.tvfilm.modules.main.sortlist.SortListFragment;
import com.tuojin.tvfilm.modules.search.SearchActivity;
import com.tuojin.tvfilm.net.BaseApiResponse;
import com.tuojin.tvfilm.presenter.HotRecommPresenterImpl;
import com.tuojin.tvfilm.service.AutoBahnService;
import com.tuojin.tvfilm.utils.Constant;
import com.tuojin.tvfilm.utils.LogUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文 件 名: CategoryContract
 * 创 建 人: Administrator
 * 创建日期: 2016/9/21 13:50
 * 文件描述：主Activity，通过不同的Fragment
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class MainActivity extends BaseActivity<HotRecommContract.View, HotRecommPresenterImpl> implements
        View.OnFocusChangeListener {

    public static final String TAG = "MainActivity";

    @BindView(R.id.rab_hotrecomm)
    RadioButton mRbHotRecom;
    @BindView(R.id.rab_category)
    RadioButton mRbCatgory;
    @BindView(R.id.rab_sortlist)
    RadioButton mRabSortlist;
    @BindView(R.id.rab_album)
    RadioButton mRabAlbum;
    @BindView(R.id.rab_search)
    RadioButton mRabSearch;
    @BindView(R.id.group)
    RadioGroup mGroup;
    @BindView(R.id.mVpContainer)
    ViewPager mVpContainer;

    BaseFragment mHotRecommFrag, mCategoryFrag, mSortListFrag, mAlbumFrag;
    //    BaseFragment mHotRecommFrag, mCategoryFrag, mSortListFrag, mAlbumFrag, mSearchFrag;
    List<BaseFragment> mFragmentList;
    int mPressedCount = 0;
    int type;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_setting)
    TextView mTvSetting;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.tv_collect)
    TextView mTvCollect;
    @BindView(R.id.rl_container)
    LinearLayout mRlContainer;
    @BindView(R.id.relative)
    RelativeLayout mRelative;
    private int[] mIntArray;
    private AlertDialog mDialog;
    private RecyclerView mRecyclerView;
    private List<TerminalBean> mList;
    private LiteOrm mMLiteOrm;


    @Override
    protected HotRecommPresenterImpl initPresenter() {
        return new HotRecommPresenterImpl();
    }

    @Override
    protected void initData() {
        mDf = new SimpleDateFormat("MM-dd EEEE HH:mm:ss");
        Timer timer = new Timer();
        timer.schedule(new MyTimer(), 1000, 1000);
    }

    private SimpleDateFormat mDf;

    class MyTimer extends TimerTask {

        @Override
        public void run() {
            final String time = mDf.format(new Date());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTvTime.setText(time);
                }
            });

        }
    }

    @Override
    protected void initView() {
        SPUtils utils = new SPUtils(this, "terminal");
        Constant.TERMINAL_CODE= utils.getString("code");
        Constant.IP_TERMINAL=utils.getString("ip");

        mVpContainer.setOffscreenPageLimit(5);
        // mVpContainer.setCurrentItem(0);
        mRbHotRecom.requestFocus();
        mFragmentList = new ArrayList<>();
        mHotRecommFrag = new RecommFragment();
        mCategoryFrag = new CategoryFragment();
        mSortListFrag = new SortListFragment();
        mAlbumFrag = new AlbumFragment();
//        mSearchFrag = new SearchFragment();

        mFragmentList.add(mHotRecommFrag);
        mFragmentList.add(mCategoryFrag);
        mFragmentList.add(mSortListFrag);
        mFragmentList.add(mAlbumFrag);
//        mFragmentList.add(mSearchFrag);

        MainAdapter adapter = new MainAdapter(getSupportFragmentManager(), mFragmentList);
        mVpContainer.setAdapter(adapter);
        mVpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                type = position;
                LogUtils.d("TAG", position + "");
                switch (position) {
                    case 0:
                        setBackground(mRbHotRecom, mRbCatgory, mRabSortlist, mRabAlbum, mRabSearch);
                        break;
                    case 1:
                        setBackground(mRbCatgory, mRabSortlist, mRabAlbum, mRabSearch, mRbHotRecom);
                        break;
                    case 2:
                        setBackground(mRabSortlist, mRabAlbum, mRabSearch, mRbHotRecom, mRbCatgory);
                        break;
                    case 3:
                        setBackground(mRabAlbum, mRabSearch, mRbHotRecom, mRbCatgory, mRabSortlist);
                        break;
//                    case 4:
//                        setBackground(mRabSearch, mRbHotRecom, mRbCatgory, mRabSortlist, mRabAlbum);
//                        break;
                }
                mVpContainer.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置焦点改变监听
        mRbHotRecom.setOnFocusChangeListener(this);
        mRbCatgory.setOnFocusChangeListener(this);
        mRabSortlist.setOnFocusChangeListener(this);
        mRabAlbum.setOnFocusChangeListener(this);

        mTvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        mMLiteOrm = ((BaseApplication) mActivity.getApplication()).mLiteOrm;
        mTvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<LiteFilmCollectionBean> query = mMLiteOrm.query(LiteFilmCollectionBean.class);
                String s = new Gson().toJson(query);
                Intent intent = new Intent(MainActivity.this, FilmListActivity.class);
                intent.putExtra("data", s);
                intent.putExtra("type", "收藏");
                startActivity(intent);
            }
        });

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        if (mSearchFrag != null) {
//            ((SearchFragment)mSearchFrag).onKeyUp(keyCode, event);
//        }
        return super.onKeyUp(keyCode, event);
    }

    //tv的方向按键响应
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (mRbHotRecom.hasFocus() && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
//            if (mHotRecommFrag != null) {
//                ((RecommFragment)mHotRecommFrag).mRvRecomm.requestFocus();
//            }
//        }
        if (mVpContainer.hasFocus() && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            switch (type) {
                case 0:
                    mVpContainer.findFocus().setNextFocusDownId(R.id.rab_hotrecomm);
                    break;
                case 1:
                    mVpContainer.findFocus().setNextFocusDownId(R.id.rab_category);
                    break;
                case 2:
                    mVpContainer.findFocus().setNextFocusDownId(R.id.rab_sortlist);
                    break;
                case 3:
                    mVpContainer.findFocus().setNextFocusDownId(R.id.rab_album);
                    break;
//                case 4:
//                    mVpContainer.findFocus().setNextFocusUpId(R.id.rab_search);
//                    break;
            }
        }
//        if (mSearchFrag != null) {
//            ((SearchFragment)mSearchFrag).onKeyDown(keyCode, event);
//        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.rab_hotrecomm, R.id.rab_category, R.id.rab_sortlist, R.id.rab_album, R.id.rab_search, R.id.tv_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rab_hotrecomm:
                break;
            case R.id.rab_category:
                break;
            case R.id.rab_sortlist:
                break;
            case R.id.rab_album:
                break;
            case R.id.tv_setting:
                mPresenter.terminal();
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                LayoutInflater inflater = LayoutInflater.from(mActivity);
                View inflate = inflater.inflate(R.layout.dialog_binding, null);
                builder.setView(inflate);
                mRecyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerview);
                LinearLayoutManager ll = new LinearLayoutManager(MainActivity.this);
                ll.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(ll);
                mDialog = builder.create();
                mDialog.show();
                mDialog.getWindow().setLayout(400, 400);
                break;
            case R.id.rab_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }
    }

    /**
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TerminalBindEvent event) {
        BaseApiResponse baseApiResponse = new Gson().fromJson(event.msg, BaseApiResponse.class);
        mDialog.dismiss();
        try {
            JSONObject object = new JSONObject(baseApiResponse.getData().toString());
            String msg = object.getString("msg");
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
            mPresenter.onResume();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TerminalListEvent event) {
        TerminalListBean terminalListBean = new Gson().fromJson(event.msg, TerminalListBean.class);
        mList = terminalListBean.getData().getData();
        CommonAdapter<TerminalBean> adapter = new CommonAdapter<TerminalBean>(MainActivity.this, R.layout.item_radbtn, mList, 2) {
            @Override
            public void convert(ViewHolder holder, TerminalBean terminalBean) {
                holder.setText(R.id.radbtn_item, terminalBean.getTerminal_name());
//                if (terminalBean.getMac() == null || terminalBean.getMac().trim().equals("")) {
//                    holder.setTextColor(R.id.radbtn_item, R.color.white);
//                } else {
//                    holder.setTextColor(R.id.radbtn_item, R.color.red);
//                }
            }
        };
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                TerminalBean terminalBean = mList.get(position);
                if (terminalBean.getMac() == null || terminalBean.getMac().trim().equals(""))
                    mPresenter.bind(terminalBean);
                ip = terminalBean.getTerminal_ip();
                Constant.IP_TERMINAL = terminalBean.getTerminal_ip();
                Constant.TERMINAL_CODE=terminalBean.getTerminal_code();
                new SPUtils(MainActivity.this, "terminal").putString("ip", ip);
                new SPUtils(MainActivity.this, "terminal").putString("code", terminalBean.getTerminal_code());
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    String ip;

    //返回键按2次，
    @Override
    public void onBackPressed() {
        this.mPressedCount++;
        if (mPressedCount == 2) {
            super.onBackPressed();
            //  ((BaseApplication) getApplication()).mService.closeConnection();
            return;
        }
        Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPressedCount = 0;
            }
        }, 2000L);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(getApplicationContext(), AutoBahnService.class));
    }

    //  RadioButton控制viewpager
    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()) {
            case R.id.rab_hotrecomm:
                LogUtils.d("11", "hot");
                setBackground(mRbHotRecom, mRbCatgory, mRabSortlist, mRabAlbum, mRabSearch);
//                mRbHotRecom.setTextColor(Color.WHITE);
                mVpContainer.setCurrentItem(0);
                break;
            case R.id.rab_category:
                LogUtils.d("11", "rab_category");
//                mRbCatgory.setTextColor(Color.WHITE);
                mVpContainer.setCurrentItem(1);
                break;
            case R.id.rab_sortlist:
                LogUtils.d("11", "rab_sortlist");
//                mRabSortlist.setTextColor(Color.WHITE);
                mVpContainer.setCurrentItem(2);
                break;
            case R.id.rab_album:
                LogUtils.d("11", "rab_album");
//                mRabAlbum.setTextColor(Color.WHITE);
                mVpContainer.setCurrentItem(3);
                break;
//            case R.id.rab_search:
//                LogUtils.d("11", "rab_search");
//                mRabSearch.setTextColor(Color.WHITE);
//                mVpContainer.setCurrentItem(4);
//                break;
        }
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String msg) {

    }
}
