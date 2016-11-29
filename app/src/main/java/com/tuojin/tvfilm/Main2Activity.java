package com.tuojin.tvfilm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tuojin.tvfilm.net.TvFilmNetWorkWS;
import com.tuojin.tvfilm.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button mBtn1;
    @BindView(R.id.btn2)
    Button mBtn2;
    @BindView(R.id.btn3)
    Button mBtn3;
    @BindView(R.id.btn4)
    Button mBtn4;
    @BindView(R.id.btn5)
    Button mBtn5;
    @BindView(R.id.btn6)
    Button mBtn6;
    @BindView(R.id.btn7)
    Button mBtn7;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.activity_main2)
    LinearLayout mActivityMain2;
    private TvFilmNetWorkWS mNetWorkWS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
//        mNetWorkWS = new TvFilmNetWorkWS(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                mNetWorkWS.sendMsg(Constant.PADMAC +
                        "|getActorList|startIndex=0&endIndex=50");
                break;
            case R.id.btn2:
                mNetWorkWS.sendMsg(Constant.PADMAC+"|getPlaceList|startIndex=0&endIndex=50");
                break;
            case R.id.btn3:
                mNetWorkWS.sendMsg(Constant.PADMAC +
                        "|getDoctorList|startIndex=0&endIndex=20");
                break;
            case R.id.btn4:
                mNetWorkWS.sendMsg(Constant.PADMAC +
                        "|getActorList|startIndex=0&endIndex=50");
                break;
            case R.id.btn5:
                mNetWorkWS.sendMsg(Constant.PADMAC +
                        "|getActorList|startIndex=0&endIndex=50");
                break;
            case R.id.btn6:
                break;
            case R.id.btn7:
                break;
        }
    }
}
