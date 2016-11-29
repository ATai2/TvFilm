package com.tuojin.tvfilm.modules.main.album;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.bean.AlbumInfo;
import com.tuojin.tvfilm.contract.AlbumContract;
import com.tuojin.tvfilm.keybord.FocusGridLayoutManager;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.OnItemClickListener;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.modules.catelist.live.MP4Activity;
import com.tuojin.tvfilm.presenter.AlbumPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文 件 名: AlbumFragment
 * 创 建 人: Administrator
 * 创建日期: 2016/9/21 16:01
 * 文件描述：
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class AlbumFragment extends BaseFragment<AlbumContract.View, AlbumPresenterImpl> {
    @BindView(R.id.rv_album)
    RecyclerView mRvAlbum;
    private CommonAdapter<AlbumInfo> mOtherAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_album;
    }

    @Override
    protected void initView() {
        FocusGridLayoutManager focusGridLayoutManager = new FocusGridLayoutManager(mActivity, 3);
        focusGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        LinearLayoutManager ll=new LinearLayoutManager(mActivity);
//        ll.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvAlbum.setFocusable(true);
        mRvAlbum.setHasFixedSize(true);
        mRvAlbum.setLayoutManager(focusGridLayoutManager);
        final List<String> listName=new ArrayList<>();
//        listName.add("http://192.168.1.243/What's%20up%20Hong%20Kong.mp4");
        listName.add("http://192.168.1.243/hideandseek.mp4");
        listName.add("http://192.168.1.243/The.Big.Bang.Theory.S09E03.中英字幕.WEB-HR.AAC.1024X576.x264.mp4");
        listName.add("http://192.168.1.243/The.Big.Bang.Theory.S09E04.WEB-HR.AAC.1024X576.x264.mp4");

        Drawable drawable = mActivity.getResources().getDrawable(R.drawable.aa);
        List<Drawable> pics=new ArrayList<>();
        pics.add(drawable);
        pics.add(mActivity.getResources().getDrawable(R.drawable.bb));
        pics.add(drawable);

        List<AlbumInfo> list=new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            AlbumInfo albumInfo = new AlbumInfo(listName.get(i), pics.get(i));
            list.add(albumInfo);
        }

        mOtherAdapter = new CommonAdapter<AlbumInfo>(mActivity, R.layout.item_other_album, list, 2) {
            @Override
            public void convert(ViewHolder holder, AlbumInfo bean) {
                holder.setImageDrawable(R.id.movie_image_other,bean.getPic());
            }
        };
        mOtherAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Intent intent = new Intent(mActivity, MP4Activity.class);
                intent.putExtra("url", listName.get(position));
                startActivity(intent);
            }
        });
        mRvAlbum.setAdapter(mOtherAdapter);
        mRvAlbum.requestFocus();
    }

    @Override
    protected AlbumPresenterImpl initPresenter() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
