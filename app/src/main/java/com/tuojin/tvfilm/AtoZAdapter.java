//package com.tuojin.tvfilm;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
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
// * 创建日期: 2016/11/16 16:58
// * 文件描述：
// * 邮   箱:
// * 博   客:
// * 修改时间：
// * 修改备注：
// */
//
//class AtoZAdapter extends RecyclerView.Adapter<AtoZAdapter.ViewHolder> {
//
//    private final List<String> mMenuList;
//    Context mContext;
//
//    public AtoZAdapter(Context context,List<String> list) {
//        mContext = context;
//        mMenuList=list;
//    }
//
//    public AtoZAdapter(Context context) {
//        mContext = context;
//        mMenuList = new ArrayList<>();
//        for (int i = 'A'; i <= 'Z'; i++) {
//            mMenuList.add(String.valueOf((char) i));
//        }
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(View view, int position);
//        void onItemLongClick(View view,int position);
//    }
//
//    public interface OnItemSelectListener{
//        void onItemSelect(View view,int position);
//    }
//
//    private OnItemClickListener mListener;
//    private OnItemSelectListener mSelectListener;
//
//    public void setOnItemSelectListener(OnItemSelectListener listener){
//        mSelectListener = listener;
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener){
//        mListener = listener;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.item_radbtn, parent, false);
//        ViewHolder holder = new ViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, final int position) {
//
//        holder.mRadbtnItem.setText(mMenuList.get(position));
//        if (position == 0) {
//            holder.mRadbtnItem.requestFocus();
//            btn = holder.mRadbtnItem;
//            mPresenter.list("A");
//        }
//        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                mRvMenu.smoothToCenter(position);
//            }
//        });
//        holder.mRadbtnItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                btn = (TextView) v;
////                    name=btn.getText().toString();
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