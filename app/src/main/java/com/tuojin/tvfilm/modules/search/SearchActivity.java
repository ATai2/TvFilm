package com.tuojin.tvfilm.modules.search;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseActivity;
import com.tuojin.tvfilm.contract.SearchContract;
import com.tuojin.tvfilm.keybord.FocusGridLayoutManager;
import com.tuojin.tvfilm.keybord.KeyboardIME;
import com.tuojin.tvfilm.keybord.SimpleKeyBoardView;
import com.tuojin.tvfilm.presenter.SearchPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tuojin.tvfilm.R.id.first_search_text;
import static com.tuojin.tvfilm.R.id.search_text;
import static com.tuojin.tvfilm.R.id.two_search_text;

/**
 * 文 件 名: SearchActivity
 * 创 建 人: Administrator
 * 创建日期: 2016/9/23 13:08
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class SearchActivity extends BaseActivity<SearchContract.View, SearchPresenterImpl> implements SearchContract.View {
    @BindView(search_text)
    TextView mSearchText;
    @BindView(first_search_text)
    TextView mFirstSearchText;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(two_search_text)
    TextView mTwoSearchText;
    @BindView(R.id.two_recyclerview)
    RecyclerView mTwoRecyclerview;
    @BindView(R.id.edit_search)
    EditText mEditSearch;
    @BindView(R.id.keyboard_view)
    SimpleKeyBoardView mKeyboardView;
    @BindView(R.id.left_menu)
    LinearLayout mLeftMenu;


    Activity mActivity;
    Context mContext;
    private FocusGridLayoutManager mLayoutManager;

    @Override
    protected SearchPresenterImpl initPresenter() {
        return new SearchPresenterImpl();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mActivity=this;
        mContext=this;
//        搜索部分初始化
        mLayoutManager = new FocusGridLayoutManager(mActivity, 5);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(mLayoutManager);
        hideKeyboard(mEditSearch);
        new KeyboardIME(mActivity,mContext,mEditSearch,mRecyclerview,mTwoRecyclerview);

    }
    public void hideKeyboard(EditText et) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }
    @Override
    public int getLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

//    //获取当前的输入的edittext
//    private void setE(Editable editable) {
//        mRecyclerView.setVisibility(View.VISIBLE);
//        two_search_text.setVisibility(View.GONE);
//        two_recyclerview.setVisibility(View.GONE);
//        if (editable.toString().equals("")) {
//            presenter.onResume(code, ONE);
//        } else {
//            presenter.onInitalsData(editable.toString());
//        }
//        search_text.setText("\"");
//        search_text.append(editable);
//        search_text.append("\"");
//        search_text.append("  的搜索结果有");
//
//        if (editable != null) {
//            search_text.setVisibility(View.VISIBLE);
//            first_search_text.setVisibility(View.GONE);
//        }
//        if (editable.length() == 0) {
//            search_text.setVisibility(View.GONE);
//            first_search_text.setVisibility(View.VISIBLE);
//        }
//    }

}
