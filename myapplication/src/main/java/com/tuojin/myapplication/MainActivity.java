package com.tuojin.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends BaseActivity {

    private ImageView mView;
    private SimpleDraweeView mById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = (ImageView) findViewById(R.id.ivm);
        mById = (SimpleDraweeView) findViewById(R.id.sdv);
//        String url="/pad/director/3.png";
        String url="/MID/BESTV160422133926003485/POSTER/BESTV160422133926003485.jpg";
        ImageLoaderUtils.showRecommIcom(this,url,mView);
//        ImageLoaderUtils.showPictureWithApplicationWithNoMID(this,url,mView);
        mById.setImageURI(Uri.parse(InterfaceURL.BASEIP+url));

//        RecyclerView rv= (RecyclerView) findViewById(R.id.rv);
//        rv.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL));
//        List<Integer> list=new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            list.add(i,i);
//        }
//
//        CommonAdapter<Integer> adapter=new CommonAdapter<Integer>(this,R.layout.item_cd,list,1) {
//            @Override
//            public void convert(ViewHolder holder, Integer integer) {
//                holder.setText(R.id.tvname,""+integer);
//            }
//        };
//        adapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
//                Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
//            }
//        });
//        rv.setAdapter(adapter);
    }
}
