package com.tuojin.tvfilm.modules.main.hotrecomm;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.RecommBean;
import com.tuojin.tvfilm.contract.HotRecommContract;
import com.tuojin.tvfilm.event.HotRecommEvent;
import com.tuojin.tvfilm.keybord.FocusGridLayoutManager;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.OnItemClickListener;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.modules.main.FilmDetailActivity;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.presenter.HotRecommPresenterImpl;
import com.tuojin.tvfilm.utils.Constant;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 * 文 件 名: CategoryContract
 * 创 建 人: Administrator
 * 创建日期: 2016/9/21 13:50
 * 文件描述：热门推荐位
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class RecommFragment extends BaseFragment<HotRecommContract.View, HotRecommPresenterImpl> implements HotRecommContract.View {

    @BindView(R.id.rv_recomm)
    public RecyclerView mRvRecomm;
    private RecommAdapter mAdapter;
    private List<FilmBean> mList;
    private CommonAdapter<FilmBean> mOtherAdapter;
    private FilmBean mValue;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recomm;
    }

    @Override
    protected void initView() {
        FocusGridLayoutManager focusGridLayoutManager = new FocusGridLayoutManager(mActivity, 2);
        focusGridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvRecomm.setFocusable(true);
        mRvRecomm.stopScroll();
        mRvRecomm.setHasFixedSize(true);
        mRvRecomm.setLayoutManager(focusGridLayoutManager);
        TvFilmNetWorkWS netWorkWS = new TvFilmNetWorkWS();

        String cmd = Constant.PADMAC +
                "|getFilmListOrderByHotest|orderByFeild=hotest&orderByType=desc&terminalCode=" +
                Constant.TERMINAL_CODE +
                "&startIndex=0&endIndex=10";
        netWorkWS.sendMsg(cmd, 101);

//        AssetFileDescriptor localAssetFileDescriptor = getResources().getAssets().openFd("startup/" + paramString);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(HotRecommEvent event) {
        String msg = event.msg;
        List<FilmBean> mDatas = new Gson().fromJson(msg, RecommBean.class).getData().getData();
        fillList(mDatas);
    }

    private void fillList(List<FilmBean> mDatas) {
        mList = mDatas;
        mOtherAdapter = new CommonAdapter<FilmBean>(mActivity, R.layout.item_other_recomm, mList, 2) {
            @Override
            public void convert(ViewHolder holder, FilmBean bean) {
                holder.setText(R.id.movie_title_other, bean.getMovie_name());
                holder.setText(R.id.movie_title_other_score, bean.getScore());
                holder.setImageResource(R.id.movie_image_other, bean.getPoster());
                holder.setScaleAnimation(R.id.movie_title_other);

            }
        };
        mOtherAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Intent intent = new Intent(mActivity, FilmDetailActivity.class);
                mValue = mList.get(position);
                intent.putExtra("film", mValue);
                startActivity(intent);
            }
        });
        mRvRecomm.setAdapter(mOtherAdapter);

        mRvRecomm.requestFocus();
    }

    @Override
    protected HotRecommPresenterImpl initPresenter() {
        return new HotRecommPresenterImpl();
    }
}
