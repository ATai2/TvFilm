package com.tuojin.tvfilm.modules.main.filmdetail;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.FileUtils;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.litesuits.orm.LiteOrm;
import com.open.androidtvwidget.BuildConfig;
import com.tuojin.tvfilm.R;
import com.tuojin.tvfilm.base.BaseApplication;
import com.tuojin.tvfilm.base.BaseFragment;
import com.tuojin.tvfilm.bean.FilmBean;
import com.tuojin.tvfilm.bean.FilmDetailBean;
import com.tuojin.tvfilm.bean.LiteFilmBean;
import com.tuojin.tvfilm.bean.PayAliBean;
import com.tuojin.tvfilm.bean.RecommBean;
import com.tuojin.tvfilm.contract.FilmDetailContract;
import com.tuojin.tvfilm.event.DetailEvent;
import com.tuojin.tvfilm.event.DetailListEvent;
import com.tuojin.tvfilm.event.FilmPauseEvent;
import com.tuojin.tvfilm.event.FilmPlayEvent;
import com.tuojin.tvfilm.event.FilmPlayRefreshSearchEvent;
import com.tuojin.tvfilm.event.FilmStopEvent;
import com.tuojin.tvfilm.event.PayEvent;
import com.tuojin.tvfilm.event.PayFailEvent;
import com.tuojin.tvfilm.event.PayReviewEvent;
import com.tuojin.tvfilm.event.QrCodeEvent;
import com.tuojin.tvfilm.modules.catelist.fragments.CommonAdapter;
import com.tuojin.tvfilm.modules.catelist.fragments.OnItemClickListener;
import com.tuojin.tvfilm.modules.catelist.fragments.ViewHolder;
import com.tuojin.tvfilm.presenter.FilmDetailPresenterImpl;
import com.tuojin.tvfilm.utils.ImageLoaderUtils;
import com.tuojin.tvfilm.widget.PayRequestDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 需要控制片头15分钟，停止发送停止命令。
 * * A simple {@link Fragment} subclass.
 */
public class FilmDetailFragment extends BaseFragment<FilmDetailContract.View, FilmDetailPresenterImpl> implements
        FilmDetailContract.View {

    FilmBean mFilm;
    @BindView(R.id.iv_back)
    ImageButton mIvBack;
    @BindView(R.id.title_topbar)
    TextView mTitleTopbar;
    @BindView(R.id.iv_filmpic_detail)
    ImageView mIvFilmpicDetail;
    @BindView(R.id.iv_flag)
    ImageView mIvFlag;
    @BindView(R.id.tv_filmname_detail)
    TextView mTvFilmnameDetail;
    @BindView(R.id.tv_dbscore_detail)
    TextView mTvDbscoreDetail;
    @BindView(R.id.tv_dbscore_scor_detail)
    TextView mTvDbscoreScorDetail;
    @BindView(R.id.tv_filmtype_detail)
    TextView mTvFilmtypeDetail;
    @BindView(R.id.tv_director_detail)
    TextView mTvDirectorDetail;
    @BindView(R.id.tv_actors_detail)
    TextView mTvActorsDetail;
    @BindView(R.id.tv_desc_detail)
    TextView mTvDescDetail;
    @BindView(R.id.btn_play)
    Button mBtnPlay;
    @BindView(R.id.rl_detail)
    RelativeLayout mRlDetail;
    @BindView(R.id.tv_guess)
    TextView mTvGuess;
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
    @BindView(R.id.rv_film_detail)
    RecyclerView mRvFilmDetail;
    @BindView(R.id.iv_right)
    ImageView mIvRight;


    private FilmDetailBean.DataBean.FilmDetailDataBean mBean;
    List<FilmBean> mList;
    private Boolean isPlaying = false;
    Boolean isStoped = true;
    Boolean isPreview = false;
    boolean isPaied = false;
    private boolean mBig;
    private String mQrCode;
    private AlertDialog mAlertDialogQr;
    private AlertDialog mDialog;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private Timer mTimer;
    private String mFilePath;
    private LiteOrm mMLiteOrm;
    private PayRequestDialog mPayRequestDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_film_detail;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        mFilm = arguments.getParcelable("film");
        mBig = arguments.getBoolean("big", false);

        mTitleTopbar.setText("电影详情");
        LinearLayoutManager layout = new LinearLayoutManager(mActivity);
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvFilmDetail.setLayoutManager(layout);

        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                测试
//                playRecord();
                if (!isPlaying) {
                    showLoading();
                    mPresenter.play(mBean);
                } else {
                    mPresenter.stop(mBean);
                }
            }
        });

    }


//    /**
//     * 详情页初始化
//     *
//     * @param event
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(PaySuccessEvent event) {
//        if ()
//
//    }

    /**
     * 详情页初始化
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DetailEvent event) {
        FilmDetailBean.DataBean.FilmDetailDataBean data = new Gson().fromJson(event.bean, FilmDetailBean.class).getData().getData();
        mBean = data;
        if (mBean.getCharge_flag().equals("1")) {
            mBig=true;
        }
        ImageLoaderUtils.showRecommIcom(mActivity, "/MID" + mBean.getPoster(), mIvFilmpicDetail);
        mTvFilmnameDetail.setText(mBean.getMovie_name());
        mTvFilmtypeDetail.setText("电影类型：" + mBean.getType() + "  地区：" + mBean.getMovieCountry() + "    年份：" + mBean.getPublishdate());
        mTvActorsDetail.setText("演员：" + mBean.getCast());
        mTvDirectorDetail.setText("导演：" + mBean.getDirector());
        mTvDbscoreScorDetail.setText(mBean.getScore());
        mTvDescDetail.setText(mBean.getBrief());
        mBtnPlay.setFocusable(true);
        //  mBig = mBean.getCharge_price() < 0.01 ? false : true  大片的flag找找看
//        if (mBean.getCharge_price() < 0.01)
//            mBig = false;

        if (mBig) {
            mIvFlag.setVisibility(View.VISIBLE);
        } else {
            mIvFlag.setVisibility(View.INVISIBLE);
        }
        mBtnPlay.requestFocus();
        mBtnPlay.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
    }


    /**
     * 喜欢列表
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DetailListEvent event) {
        final List<FilmBean> data = new Gson().fromJson(event.msg, RecommBean.class).getData().getData();
        mList = data;
        CommonAdapter<FilmBean> adapter = new CommonAdapter<FilmBean>(mActivity, R.layout.item_other_linear, data, 0) {
            @Override
            public void convert(ViewHolder holder, FilmBean bean) {

                holder.setText(R.id.movie_title_other, bean.getMovie_name());
                holder.setImageResource(R.id.movie_image_other, bean.getPoster());
//                holder.setScaleAnimation(R.id.movie_title_other);
                holder.setOnTextFocusChangeListner(R.id.rl_container, R.id.movie_title_other, R.id.movie_image_other, new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            v.setVisibility(View.VISIBLE);
                        } else {
                            v.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        };
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                FilmBean bean = data.get(position);
                mBig = bean.getCharge_price() < 0.01 ? false : true;
                if (mBig) {
                    mIvFlag.setVisibility(View.VISIBLE);
                } else {
                    mIvFlag.setVisibility(View.INVISIBLE);
                }
                mFilm = bean;
                mPresenter.onResume(bean.getMid(), bean.getUuid());
                if (isPlaying) {
                    AlertDialog dialog = new AlertDialog.Builder(mActivity)
                            .setTitle("影片将被停止，确定？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    mPresenter.stop(mBean);
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }

            }
        });
        mRvFilmDetail.setAdapter(adapter);
    }

    /**
     * 支付成功
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PayEvent event) {
        Log.d("12", "onMessageEvent: " + event.msg);
//        Toast.makeText(getActivity(), event.msg, Toast.LENGTH_SHORT).show();
        isPaied = true;
//        mPresenter.play(mBean);
        mBtnPlay.setText("停止");
        isPlaying = true;
        mPresenter.rePlay();
        if (mAlertDialogQr != null) {
            mAlertDialogQr.dismiss();
        }
        if (mDialog != null) {
            mDialog.hide();
            mDialog.dismiss();
        }
        Toast.makeText(mActivity, "大片支付成功，请继续观看", Toast.LENGTH_SHORT).show();
    }

    /**
     * 支付失败
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PayFailEvent event) {
        Toast.makeText(getActivity(), event.msg, Toast.LENGTH_LONG).show();
        mPresenter.stop(mBean);
        mBtnPlay.setText("播放");
    }

    /**
     * 暂停影片
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FilmPauseEvent event) {
        Toast.makeText(getActivity(), event.msg, Toast.LENGTH_LONG).show();
        isPlaying = false;
    }

    /**
     * 停止影片
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FilmStopEvent event) {
//        Toast.makeText(getActivity(), event.msg, Toast.LENGTH_LONG).show();
        mBtnPlay.setText("播放");
        isPlaying = false;
        isStoped = true;
    }

    /**
     * 开始播放
     * 开始播放命令成功，提示进入二维码扫描页，进入计时，15min后弹出支付页
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FilmPlayEvent event) {
        if (BuildConfig.DEBUG) Log.d(TAG, event.msg);
        playRecord();
        hideLoading();

        if (mBig) {
            aliPayDialog();
            mTimer = new Timer();
            mTimer.schedule(new MyTimerTask(), 15 * 60 * 1000);//15分钟

        }
        isStoped = false;
        isPreview = true;
        isPlaying = true;
        mBtnPlay.setText("停止");
//        timer.schedule(mTimerTask,15*60*1000);
    }

    /**
     * 加入数据库，播放历史
     */
    private void playRecord() {
        Log.d("play", "playRecord: ");
        mMLiteOrm = ((BaseApplication) mActivity.getApplication()).mLiteOrm;
//        mMLiteOrm.de
        LiteFilmBean bean = new LiteFilmBean();
        String s = new Gson().toJson(mFilm);
        bean.setFilmBean(s);
        long save = mMLiteOrm.save(bean);
        if (save > 0) {
            EventBus.getDefault().post(new FilmPlayRefreshSearchEvent(mFilm));
        }
    }

    /**
     * 处理activity的onKeyUp事件
     *
     * @param keyCode
     * @param event
     */
    public void onKeyUp(int keyCode, KeyEvent event) {
        if (((LinearLayoutManager) mRvFilmDetail.getLayoutManager()).findFirstVisibleItemPosition() == 0
                ) {
            mIvLeft.setVisibility(View.INVISIBLE);
        } else {
            mIvLeft.setVisibility(View.VISIBLE);

        }
        if (((LinearLayoutManager) mRvFilmDetail.getLayoutManager()).findLastVisibleItemPosition() == mList.size() - 1
                ) {
            mIvRight.setVisibility(View.INVISIBLE);
        } else {
            mIvRight.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 计时器
     */
    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            //倒计时结束，开始执行弹出操作--支付宝。 如果已支付，继续观看，如果没有支付暂停，弹二维码框。
            if (!isPaied) {
                Log.d("ws", "run: timertask");
                mPresenter.pause();
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mAlertDialogQr == null || (mAlertDialogQr != null && !mAlertDialogQr.isShowing())) {
                            mDialog.hide();
                            showLoading();
                            qrPayFilm();
                            mTimer.cancel();
//                            if () {
//                                mPresenter.playStatus();
//                            }
                        }
                    }
                });
                isPreview = false;
            }
        }
    }

    /**
     * 先判断是否为大片？
     * 弹出支付提醒-----
     * 二维码
     * 看看再说
     * 取消
     * 支付宝二维码弹框
     */
    private void aliPayDialog() {
//        已经在播放了
        if (mBig) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            LayoutInflater lf = LayoutInflater.from(mActivity);
            View inflate = lf.inflate(R.layout.dialog_pay, null);
            builder.setView(inflate);
            mDialog = builder.create();
            mDialog.show();
            Button btnPay = (Button) inflate.findViewById(R.id.btn_pay);
            Button btnPayLater = (Button) inflate.findViewById(R.id.btn_later);
            Button btnPayCancel = (Button) inflate.findViewById(R.id.btn_cancle);
//            mPayRequestDialog = new PayRequestDialog();
//            mPayRequestDialog.show(mActivity.getFragmentManager(), "payrequest");
//            mPayRequestDialog.setClickListener1(new PayRequestDialog.OnbtnClickListener1() {
//                @Override
//                public void click() {
//                    qrPayFilm();
//                    mPayRequestDialog.dismiss();
//                }
//            });
//            mPayRequestDialog.setClickListener2(new PayRequestDialog.OnbtnClickListener2() {
//                @Override
//                public void click() {
//                    mPayRequestDialog.dismiss();
//                }
//            });
//            mPayRequestDialog.setClickListener3(new PayRequestDialog.OnbtnClickListener3() {
//                @Override
//                public void click() {
//                    if (!isStoped) {
//                        mPresenter.stop(mBean);
//                    }
//                    isPlaying = false;
//                    mPayRequestDialog.dismiss();
//                }
//            });
//                    先播放，再支付
            btnPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    qrPayFilm();
                }
            });
            btnPayLater.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mPresenter.play(mBean);

                    mDialog.cancel();
//                    mDialog=null;
                }
            });

            btnPayCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isStoped) {
                        mPresenter.stop(mBean);
                    }
                    isPlaying = false;
                    mDialog.dismiss();
                }
            });
//            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                @Override
//                public void onCancel(DialogInterface dialog) {
//                    if (!isStoped) {
//                        mPresenter.stop(mBean);
//                    }
//                    isPlaying = false;
//                }
//            });
//        }else {
//            if (!isPlaying){
//                Toast.makeText(mActivity, "播放影片", Toast.LENGTH_SHORT).show();
//                mPresenter.play(mBean);
//                mBtnPlay.setText("停止");
//            }else {
//                Toast.makeText(mActivity, "停止播放影片", Toast.LENGTH_SHORT).show();
//                mPresenter.stop(mBean);
//                mBtnPlay.setText("播放");
//            }
//            isPlaying = !isPlaying;
        }
    }

    /**
     * 预览后支付页,先loading，然后弹出页面。
     */
    private void qrPayFilm() {

        mPresenter.getQrCode(mBean);

    }


    /**
     * 二维码
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(QrCodeEvent event) {
        String qrCode = new Gson().fromJson(event.bean, PayAliBean.class).getData().getQrCode();
        hideLoading();
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View inflate = inflater.inflate(R.layout.dialog_qrcode, null);
        builder.setView(inflate);
        mAlertDialogQr = builder.create();
        mAlertDialogQr.show();
        mImageView = (ImageView) inflate.findViewById(R.id.iv_qrcode);
        mProgressBar = (ProgressBar) inflate.findViewById(R.id.pb_pay);
        mProgressBar.setVisibility(View.INVISIBLE);

        Button btnPayCancel = (Button) inflate.findViewById(R.id.btn_cancle);
        btnPayCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialogQr.dismiss();
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                    mBtnPlay.setText("播放");
                }
                if (!isStoped) {
                    mPresenter.stop(mBean);
                }
                isPlaying = false;
            }
        });
        mAlertDialogQr.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (!isStoped) {
                    mPresenter.stop(mBean);
                    mBtnPlay.setText("播放");
                }
                isPlaying = false;
            }
        });

        if (qrCode != null) {
            mQrCode = qrCode;
            mFilePath = getFileRoot(mActivity) + File.separator
                    + "qr_" + System.currentTimeMillis() + ".jpg";
            final String path = mFilePath;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean success = createQRImage(mQrCode, 400, 400, null, path);
                    if (success) {
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mImageView.setImageBitmap(BitmapFactory.decodeFile(path));
                                mProgressBar.setVisibility(View.INVISIBLE);
                                hideLoading();
//                            开启线程，每隔几秒检查是否支付成功。
                            }
                        });
                    }
                }
            }).start();
        } else {
            Toast.makeText(mActivity, "二维码无法获得，请稍后重试", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected FilmDetailPresenterImpl initPresenter() {
        return new FilmDetailPresenterImpl();
    }

    ;


    //文件存储根目录
    private String getFileRoot(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File external = context.getExternalFilesDir(null);
            if (external != null) {
                return external.getAbsolutePath();
            }
        }

        return context.getFilesDir().getAbsolutePath();
    }

    /**
     * 显示二维码
     *
     * @param qrCode
     */
    /**
     * 大片预览结束
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PayReviewEvent event) {
        Toast.makeText(getActivity(), event.msg, Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("大片预览结束");
        builder.setMessage("");
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View inflate = inflater.inflate(R.layout.dialog_qrcode, null);
        builder.setView(inflate);
        mAlertDialogQr = builder.create();
        mAlertDialogQr.show();
        mImageView = (ImageView) inflate.findViewById(R.id.iv_qrcode);
        mProgressBar = (ProgressBar) inflate.findViewById(R.id.pb_pay);
        mProgressBar.setVisibility(View.INVISIBLE);
        mPresenter.getQrCode(mBean);
        Button btnPayCancel = (Button) inflate.findViewById(R.id.btn_cancle);
        btnPayCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialogQr.dismiss();
            }
        });
    }

    @Override
    public void qrCode(String qrCode) {
        hideLoading();
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View inflate = inflater.inflate(R.layout.dialog_qrcode, null);
        builder.setView(inflate);
        mAlertDialogQr = builder.create();
        mAlertDialogQr.show();
        mImageView = (ImageView) inflate.findViewById(R.id.iv_qrcode);
        mProgressBar = (ProgressBar) inflate.findViewById(R.id.pb_pay);
        mProgressBar.setVisibility(View.INVISIBLE);

        Button btnPayCancel = (Button) inflate.findViewById(R.id.btn_cancle);
        btnPayCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialogQr.dismiss();
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                    mBtnPlay.setText("播放");
                }
                if (!isStoped) {
                    mPresenter.stop(mBean);
                }
                isPlaying = false;
            }
        });
        mAlertDialogQr.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (!isStoped) {
                    mPresenter.stop(mBean);
                    mBtnPlay.setText("播放");
                }
                isPlaying = false;
            }
        });

        if (qrCode != null) {
            mQrCode = qrCode;

            mFilePath = getFileRoot(mActivity) + File.separator
                    + "qr_" + System.currentTimeMillis() + ".jpg";
            final String path = mFilePath;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean success = createQRImage(mQrCode, 400, 400, null, path);
                    if (success) {
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mImageView.setImageBitmap(BitmapFactory.decodeFile(path));
                                mProgressBar.setVisibility(View.INVISIBLE);
                                hideLoading();
//                            开启线程，每隔几秒检查是否支付成功。
                            }
                        });
                    }
                }
            }).start();
        } else {
            Toast.makeText(mActivity, "二维码无法获得，请稍后重试", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 生成二维码Bitmap
     *
     * @param content   内容
     * @param widthPix  图片宽度
     * @param heightPix 图片高度
     * @param logoBm    二维码中心的Logo图标（可以为null）
     * @param filePath  用于存储二维码图片的文件路径
     * @return 生成二维码及保存文件是否成功
     */
    public static boolean createQRImage(String content, int widthPix, int heightPix, Bitmap logoBm, String filePath) {
        try {
            if (content == null || "".equals(content)) {
                return false;
            }
            //配置参数
            Map hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //容错级别
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            //设置空白边距的宽度
//            hints.put(EncodeHintType.MARGIN, 2); //default is 4
            // 图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, widthPix, heightPix, hints);
            int[] pixels = new int[widthPix * heightPix];
            // 下面这里按照二维码的算法，逐个生成二维码的图片，
            // 两个for循环是图片横列扫描的结果
            for (int y = 0; y < heightPix; y++) {
                for (int x = 0; x < widthPix; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * widthPix + x] = 0xff000000;
                    } else {
                        pixels[y * widthPix + x] = 0xffffffff;
                    }
                }
            }
            // 生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(widthPix, heightPix, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, widthPix, 0, 0, widthPix, heightPix);
            //必须使用compress方法将bitmap保存到文件中再进行读取。直接返回的bitmap是没有任何压缩的，内存消耗巨大！
            return bitmap != null && bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(filePath));
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume(mFilm.getMid(), mFilm.getUuid());
        mPresenter.initList();
    }


    boolean begin;

    @OnClick(R.id.iv_back)
    public void onClick() {
        mActivity.finish();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        FileUtils.deleteFile(mFilePath);
        if (!isStoped) {
            mPresenter.stop(mBean);
        }

    }


}
