package com.tuojin.tvfilm.modules.catelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.LiteFilmCollectionBean;
import com.tuojin.tvfilm.bean.RecommBean;
import com.tuojin.tvfilm.keybord.FocusGridLayoutManager;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.OnItemClickListener;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.modules.main.FilmDetailActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilmListActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageButton mIvBack;
    @BindView(R.id.title_topbar)
    TextView mTitle;
    @BindView(R.id.rv_container_filmlist)
    RecyclerView mRvContainerFilmlist;
    private List<FilmBean> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_list);
        ButterKnife.bind(this);

        String data = getIntent().getStringExtra("data");
        final String type = getIntent().getStringExtra("type");

//        data=data.replace('\\',' ').trim();
        if (type.equals("收藏")) {
            mData=new ArrayList<>();
            ArrayList<LiteFilmCollectionBean> list=new Gson().fromJson(data, new TypeToken<ArrayList<LiteFilmCollectionBean>>() {
            }.getType());
            for (int i = 0; i < list.size(); i++) {
                FilmBean bean = new Gson().fromJson(list.get(i).getFilmBean(), FilmBean.class);
                mData.add(bean);
            }
            Collections.reverse(mData);
        }else {
            mData = new Gson().fromJson(data, RecommBean.class).getData().getData();
        }


        if (mData != null) {
            mTitle.setText(type+"共有"+mData.size()+"部电影");
        }

        FocusGridLayoutManager focusGridLayoutManager = new FocusGridLayoutManager(FilmListActivity.this, 5);
        focusGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvContainerFilmlist.setHasFixedSize(true);
        mRvContainerFilmlist.setLayoutManager(focusGridLayoutManager);

        final CommonAdapter<FilmBean> adapter = new CommonAdapter<FilmBean>(this, R.layout.item_other, mData, 0) {
            @Override
            public void convert(ViewHolder holder, FilmBean bean) {
                holder.setText(R.id.movie_title_other, bean.getMovie_name());
                holder.setImageResource(R.id.movie_image_other, bean.getPoster());
                holder.setScaleAnimation(R.id.movie_title_other);
            }
        };
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Intent intent = new Intent(FilmListActivity.this, FilmDetailActivity.class);
                FilmBean bean = mData.get(position);
                intent.putExtra("film", bean);
                startActivity(intent);
                if (type.equals("收藏")) {
                    FilmListActivity.this.finish();
                }
            }
        });
        mRvContainerFilmlist.setAdapter(adapter);
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        this.finish();
    }
}
