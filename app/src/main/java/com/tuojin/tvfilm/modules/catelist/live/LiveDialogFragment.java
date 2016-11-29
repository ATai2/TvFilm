package com.tuojin.tvfilm.modules.catelist.live;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.bean.LiveContentBean;
import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文 件 名: LiveDialogFragment
 * 创 建 人: Administrator
 * 创建日期: 2016/10/19 15:26
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class LiveDialogFragment extends DialogFragment {

    DisplayMetrics mMetrics;
    @BindView(R.id.rv_container_filmlist)
    RecyclerView mRvContainerFilmlist;
    @BindView(R.id.btn_back)
    Button mBtnBack;
    private LinearLayoutManager mLayout;
    private String mKey;
    OnBtnStartClick mOnBtnStartClick;

    public void setOnBtnStartClick(OnBtnStartClick onBtnStartClick) {
        mOnBtnStartClick = onBtnStartClick;
    }

    interface OnBtnStartClick {
        void onClick(LiveContentBean bean);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
//        getArguments();
        mKey = getArguments().getString("key", "%E6%B5%8B%E8%AF%95");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("节目单：");
        View view = inflater.inflate(R.layout.fragment_live_dialog, container, false);
        ButterKnife.bind(this, view);
        mLayout = new LinearLayoutManager(getActivity());
        mLayout.setOrientation(LinearLayoutManager.VERTICAL);
        mRvContainerFilmlist.setLayoutManager(mLayout);
        String cmd = Constant.PADMAC +
                "|getLiveContentList|terminalCode=" +
                Constant.TERMINAL_CODE +
                "&lcname=" +
                mKey +
                "&startIndex=0&endIndex=20";
        TvFilmNetWorkWS mNetWorkWS =new TvFilmNetWorkWS();
        mNetWorkWS.sendMsg(cmd);
//                , new TvFilmNetWorkWS.Success() {
//                    @Override
//                    public void excute(String data) {
//                        final List<LiveContentBean> mDatas = new Gson().fromJson(data, LiveContentListBean.class).getData().getData();
//                        int i = 0;
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                CommonAdapter commonAdapter = new CommonAdapter<LiveContentBean>(getActivity(), R.layout.item_live_content_button, mDatas, 2) {
//
//                                    @Override
//                                    public void convert(ViewHolder holder, final LiveContentBean liveBean) {
//                                        holder.setText(R.id.tv_live_content, liveBean.getLcname());
//                                        holder.setText(R.id.tv_live_desc, liveBean.getLivecontent());
//                                        holder.setText(R.id.tv_live_start, liveBean.getOpentime());
//                                        holder.setText(R.id.tv_live_end, liveBean.getEndtime());
//                                        holder.setText(R.id.tv_live_duration, liveBean.getDuration() + "");
//                                        holder.setOnClickListener(R.id.tv_live_action, new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v) {
//                                                //视频初始化
//                                                mOnBtnStartClick.onClick(liveBean);
//                                                LiveDialogFragment.this.dismiss();
//                                            }
//                                        });
////                                        holder.setText(R.id.tv_live_action,liveBean.getLivecontent());
//                                    }
//                                };
//                                commonAdapter.setOnItemClickListener(new OnItemClickListener() {
//                                    @Override
//                                    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
//
//                                    }
//                                });
//                                mRvContainerFilmlist.setAdapter(commonAdapter);
//                                mRvContainerFilmlist.requestFocus();
//                            }
//                        });
////                        mPresenter.initList(mDatas);
//                    }
//                }, new TvFilmNetWorkWS.Failure() {
//                    @Override
//                    public void excute(String data) {
//                    }
//                });

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveDialogFragment.this.dismiss();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(700, 600);
    }
}
