package com.tuojin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv2);

        List<String> mList=new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mList.add(""+i);
        }

        CommonAdapter adapter=new CommonAdapter<String>(this,R.layout.item_other,mList,0) {

            @Override
            public void convert(ViewHolder holder, String s) {
                holder.setText(R.id.movie_title_other, s);
                holder.setImageResource(R.id.movie_image_other, s);

            }
        };

        mRecyclerView.setAdapter(adapter);
    }



}
