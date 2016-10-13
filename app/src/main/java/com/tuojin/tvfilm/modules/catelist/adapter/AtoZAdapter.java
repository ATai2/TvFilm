//package com.tuojin.tvfilm.modules.catelist.adapter;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.tuojin.tvfilm.R;
//import com.tuojin.tvfilm.modules.catelist.area.AreaActivity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * 文 件 名: AtoZAdapter
// * 创 建 人: Administrator
// * 创建日期: 2016/10/13 10:52
// * 文件描述：
// * 邮   箱:
// * 博   客:
// * 修改时间：
// * 修改备注：
// */
//
//class AtoZAdapter extends RecyclerView.Adapter<AtoZAdapter.ViewHolder> {
//    List<String> mMenuList;
//
//    public AtoZAdapter() {
//        mMenuList = new ArrayList<>();
//        for (int i = 'A'; i <= 'Z'; i++) {
//            mMenuList.add(String.valueOf((char) i));
//        }
//    }
//
//
//    @Override
//    public AtoZAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(AreaActivity.this).inflate(R.layout.item_radbtn, parent, false);
//       AtoZAdapter.ViewHolder holder = new AtoZAdapter.ViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(AtoZAdapter.ViewHolder holder, int position) {
//
//        holder.mRadbtnItem.setText(mMenuList.get(position));
//        if (position == 0) {
//            holder.mRadbtnItem.requestFocus();
//            btn = holder.mRadbtnItem;
//            mPresenter.list("A");
//        }
//        holder.mRadbtnItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                btn = (TextView) v;
//                mPresenter.list(btn.getText().toString());
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return mMenuList.size();
//    }
//
//    class ViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.radbtn_item)
//        TextView mRadbtnItem;
//
//        ViewHolder(View view) {
//            super(view);
//            ButterKnife.bind(this, view);
//        }
//    }
//}