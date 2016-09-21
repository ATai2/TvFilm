package com.tuojin.tvfilm.modules.main.hotrecomm;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.contract.HotRecommContract;
import com.tuojin.tvfilm.presenter.HotRecommPresenterImpl;
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
public class RecommFragment extends BaseFragment<HotRecommContract.View,HotRecommPresenterImpl> implements HotRecommContract.View {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recomm;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected HotRecommPresenterImpl initPresenter() {
        return null;
    }
}
