package com.tuojin.tvfilm.modules.search;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.SearchTag;
import com.tuojin.tvfilm.contract.SearchContract;
import com.tuojin.tvfilm.keybord.FocusGridLayoutManager;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.presenter.SearchPresenterImpl;
import com.tuojin.tvfilm.widget.FixGridLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文 件 名: SearchFragment
 * 创 建 人: Administrator
 * 创建日期: 2016/10/9 14:56
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class SearchFragment extends BaseFragment<SearchContract.View, SearchPresenterImpl> implements SearchContract.View {

    @BindView(R.id.search_text)
    TextView mSearchText;
    @BindView(R.id.first_search_text)
    TextView mFirstSearchText;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.two_search_text)
    TextView mTwoSearchText;
    @BindView(R.id.two_recyclerview)
    RecyclerView mTwoRecyclerview;
    @BindView(R.id.edit_search)
    EditText mEditSearch;
    @BindView(R.id.ll_wrap)
    FixGridLayout mLlWrap;
    private SharedPreferences mSearchHistory;
    private String mKey = "";
    private List<FilmBean> mList = new ArrayList<>();
    private FocusGridLayoutManager mGridLayoutManager;
    private CommonAdapter<FilmBean> mAdapter;
    private List<SearchTag> mTags;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        mEditSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEnterOnTextChanged(s, start, before, count);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mGridLayoutManager = new FocusGridLayoutManager(mActivity, 5);
        mGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(mGridLayoutManager);
        mAdapter = new CommonAdapter<FilmBean>(mActivity, R.layout.item_other, mList, 0) {
            @Override
            public void convert(ViewHolder holder, FilmBean bean) {
                holder.setText(R.id.movie_title_other, bean.getMovie_name());
                holder.setImageResource(R.id.movie_image_other, bean.getPoster());
                holder.setScaleAnimation(R.id.movie_title_other);
            }
        };
        mRecyclerview.setAdapter(mAdapter);
        mEditSearch.requestFocus();


        TextView textView=new TextView(mActivity);
        textView.setTextColor(getResources().getColor(R.color.blue));
        textView.setTextSize(24);
        textView.setPadding(10,10,5,2);
        mLlWrap.addView(textView);
        mTags = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
          SearchTag tag=new SearchTag();
            tag.setName(""+i);
            mTags.add(tag);
        }


        for (int i = 0; i < mTags.size(); i++) {
            LinearLayout layout = new LinearLayout(getActivity());
//            关闭硬件加速
            layout.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
            setLayoutParams(layout);
            final Button btn = new Button(getActivity());
            setBtnParams(mTags, i, btn);
            final TextView tv = new TextView(mActivity);
            setTitleParamsw(mTags, i, layout, btn, tv);
            mLlWrap.addView(layout);
            layout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){
                        btn.setTextColor(getResources().getColor(R.color.white));
                        btn.setNextFocusUpId(R.id.search_text);
                    }
                    else{
                        btn.setTextColor(getResources().getColor(R.color.white));
                    }
                }
            });
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    dealLayOutOnclicked(tv);
                }
            });
            layout.setNextFocusUpId(R.id.edit_search);
        }

    }
    /**
     * @des  设置title参数
     * @param searchTagInfos
     * @param i
     * @param layout
     * @param btn
     * @param tv
     */
    private void setTitleParamsw(List<SearchTag> searchTagInfos, int i, LinearLayout layout, Button btn, TextView tv) {
        tv.setVisibility(View.GONE);
        tv.setTextColor(Color.WHITE);
        tv.setText(searchTagInfos.get(i).getName());
        layout.addView(btn);
        layout.addView(tv);
        layout.setPadding(5, 2, 5, 2);
    }
    /**
     * @des 设置btn参数
     * @param searchTagInfos
     * @param i
     * @param btn
     */
    private void setBtnParams(List<SearchTag> searchTagInfos, int i, Button btn) {
        btn.setBackgroundColor(0x000);
        btn.setTextColor(getResources().getColor(R.color.white));
        btn.setPadding(5, 2, 5, 2);
        btn.setTextSize(20);
        btn.setFocusable(true);
        btn.setText(searchTagInfos.get(i).getName());
    }
    /**
     * @des 设置layout参数
     * @param layout
     */
    private void setLayoutParams(LinearLayout layout) {
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 50));
        layout.setBackgroundResource(R.drawable.border_search_selector);
        layout.setFocusable(true);
        layout.setGravity(Gravity.CENTER);
    }
    private void mEnterOnTextChanged(CharSequence s, int start, int before, int count) {
        mKey = s.toString();
        if (!TextUtils.isEmpty(s)) {
            mPresenter.search(mKey);
        }
    }

    @Override
    protected SearchPresenterImpl initPresenter() {
        return new SearchPresenterImpl();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                if (mList == null) {
                    Toast.makeText(mActivity, "查无结果！", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    };

    @Override
    public void refreshUI(List<FilmBean> beanList) {
        mList = beanList;
        mHandler.sendEmptyMessage(0);
    }
}
