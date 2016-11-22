package com.tuojin.tvfilm.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tuojin.tvfilm.event.ActorEvent;
import com.tuojin.tvfilm.event.ActorListEvent;
import com.tuojin.tvfilm.event.AreaEvent;
import com.tuojin.tvfilm.event.AreaFilmListEvent;
import com.tuojin.tvfilm.event.CmdEvent;
import com.tuojin.tvfilm.event.DetailEvent;
import com.tuojin.tvfilm.event.DetailListEvent;
import com.tuojin.tvfilm.event.DirectorEvent;
import com.tuojin.tvfilm.event.DirectorListEvent;
import com.tuojin.tvfilm.event.ErrorEvent;
import com.tuojin.tvfilm.event.FilmAdEvent;
import com.tuojin.tvfilm.event.FilmBigEvent;
import com.tuojin.tvfilm.event.FilmDoubanEvent;
import com.tuojin.tvfilm.event.FilmGotoPositionEvent;
import com.tuojin.tvfilm.event.FilmHotEvent;
import com.tuojin.tvfilm.event.FilmNewEvent;
import com.tuojin.tvfilm.event.FilmPauseEvent;
import com.tuojin.tvfilm.event.FilmPlayEvent;
import com.tuojin.tvfilm.event.FilmRePlayEvent;
import com.tuojin.tvfilm.event.FilmStatusEvent;
import com.tuojin.tvfilm.event.FilmStatusUpdateEvent;
import com.tuojin.tvfilm.event.FilmStopEvent;
import com.tuojin.tvfilm.event.FilmTypeEvent;
import com.tuojin.tvfilm.event.HotRecommEvent;
import com.tuojin.tvfilm.event.KeyWordEvent;
import com.tuojin.tvfilm.event.LiveContentEvent;
import com.tuojin.tvfilm.event.LiveListEvent;
import com.tuojin.tvfilm.event.PayEvent;
import com.tuojin.tvfilm.event.PayReviewEvent;
import com.tuojin.tvfilm.event.PreviewConfirmEvent;
import com.tuojin.tvfilm.event.QrCodeEvent;
import com.tuojin.tvfilm.event.SearchHotEvent;
import com.tuojin.tvfilm.event.SearchNoListEvent;
import com.tuojin.tvfilm.event.TerminalBindEvent;
import com.tuojin.tvfilm.event.TerminalListEvent;
import com.tuojin.tvfilm.event.YearEvent;
import com.tuojin.tvfilm.event.YearListEvent;
import com.tuojin.tvfilm.net.BaseApiResponse;
import com.tuojin.tvfilm.utils.Constant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.TimerTask;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

public class AutoBahnService extends Service  {
    private String TAG = "abs";
    String flag;
    boolean isOpen;
    private final WebSocketConnection mConnection = new WebSocketConnection();
    final String path = Constant.WSSITE;
    private String mCmd;

    public AutoBahnService() {
        mCmd = Constant.PADMAC +
                "|getFilmListOrderByHotest|orderByFeild=hotest&orderByType=desc&terminalCode=" +
                Constant.TERMINAL_CODE +
                "&startIndex=0&endIndex=10";
        connect();
//        Timer timer = new Timer();
//        timer.schedule(new ServiceTimer(), 1000, 2000);
    }

    class ServiceTimer extends TimerTask {

        @Override
        public void run() {
            Log.d(TAG, "run: ");
            while (!mConnection.isConnected()) {
                connect();
                mConnection.sendTextMessage(mCmd);
            }
        }
    }

    /**
     * 服务连接，避免在应用退出后无法再次重连，设置，openConnection后，发送命令。
     */
    private void connect() {
        EventBus aDefault = EventBus.getDefault();
        if (!aDefault.isRegistered(this)) {
            aDefault.register(this);
        }

        try {
            mConnection.connect(path, new WebSocketHandler() {
                @Override
                public void onOpen() {
                    super.onOpen();
                    Log.d(TAG, "Status: Connected to " + path);
                    mInt = 101;
                    String login = Constant.PADMAC + "|login|username=admin&pwd=123456";
                    mConnection.sendTextMessage(login);
                    mConnection.sendTextMessage(mCmd);
                }

                @Override
                public void onTextMessage(String payload) {
                    Log.d(TAG, "Got echo: " + payload);
                    String s = payload;
                    Gson gson = new Gson();
                    BaseApiResponse response = gson.fromJson(s, BaseApiResponse.class);
                    int status = response.getStatus();

                    if (response.getMsgType().equals("bindTerminal")) {
                        //绑定影厅
                        EventBus.getDefault().post(new TerminalBindEvent(s));
                    }
                    //后台有问题？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？

                    if (status == 3) {
                        switch (response.getMsgType()) {
                            case Constant.PAY_PREVIEWOVER:
                                //获得大片预览停止确认
                                EventBus.getDefault().post(new PayReviewEvent("大片预览结束"));
                                break;
                            case Constant.STARTPLAY:
                                //影片开始播放
                                EventBus.getDefault().post(new FilmPlayEvent("影片开始播放"));
                                break;
                            case Constant.PAYSUCCESS:
                                EventBus.getDefault().post(new PayEvent("支付成功，请继续观看"));
                                break;
                            case Constant.STOPPLAY:
                                //影片停止播放
                                EventBus.getDefault().post(new FilmStopEvent("影片停止播放"));
                                break;
                            case Constant.BIGPAUSE:
                                //影片预览暂停确认
                                EventBus.getDefault().post(new PreviewConfirmEvent(s));
                                break;
//                            case Constant.BIGPAUSECONTINUE:
//                                //影片停止播放
//                                EventBus.getDefault().post(new FilmStopEvent("影片停止播放"));
//                                break;
//                            case Constant.BIGSTOP:
//                                //影片停止播放
//                                EventBus.getDefault().post(new FilmStopEvent("影片停止播放"));
//                                break;
                            case Constant.BIGPREVEWSTOPCONFIRM:
                                //影片停止播放
                                EventBus.getDefault().post(new FilmStopEvent("影片停止播放"));
                                break;

                            case Constant.PAUSEPLAY:
                                EventBus.getDefault().post(new FilmPauseEvent("影片暂停播放"));
                                break;
                            case Constant.CONTINUEPLAY:
                                //影片继续播放
                                EventBus.getDefault().post(new FilmRePlayEvent("影片继续播放"));
                                break;
                            case Constant.PLAYSTATUS:
                                //影片状态
                                if (mInt == 90) {
                                    EventBus.getDefault().post(new FilmStatusUpdateEvent(s));
                                } else {
                                    EventBus.getDefault().post(new FilmStatusEvent(s));
                                }

                                break;
                            case Constant.GOTOPOSITION:
                                //影片跳转播放
                                EventBus.getDefault().post(new FilmGotoPositionEvent(s));
                                break;
                            case Constant.GETTERMINAL:
                                //获取影厅列表
                                EventBus.getDefault().post(new TerminalListEvent(s));
                                break;
                            case Constant.GETFILMLISTORDERBYHOTEST:
                                //热门推荐
                                if (mInt == 101) {
                                    EventBus.getDefault().post(new HotRecommEvent(s));
                                }//详情中列表
                                if (mInt == 102) {
                                    EventBus.getDefault().post(new DetailListEvent(s));
                                }   // 分类最新
                                if (mInt == 103) {
                                    EventBus.getDefault().post(new FilmHotEvent(s));
                                }
                                if (mInt == 104) {
                                    EventBus.getDefault().post(new SearchHotEvent(s));
                                }
                                if (mInt == 201) {
//                                    广告
                                    EventBus.getDefault().post(new FilmAdEvent(s));
                                }
                                break;
                            case Constant.GET_FILMLIST_ORDER_BYNEWEST:
                                //最热
                                EventBus.getDefault().post(new FilmNewEvent(s));
                                break;
                            case Constant.GET_FILMLIST_ORDER_BYPAYMOVIE:
                                //大片
                                EventBus.getDefault().post(new FilmBigEvent(s));
                                break;
                            case Constant.GET_FILMLIST_ORDER_BYDOUBAN:
                                //豆瓣
                                EventBus.getDefault().post(new FilmDoubanEvent(s));
                                break;
                            case Constant.GET_FILMLIST_ORDER_BYDIRECTOR:
                                //导演
                                EventBus.getDefault().post(new DirectorEvent(s));
                                break;
                            case Constant.GET_FILMLIST_ORDER_BYACTOR:
                                //演员
                                EventBus.getDefault().post(new ActorEvent(s));
                                break;
                            case Constant.PLACELIST:
                                //地区列表
                                EventBus.getDefault().post(new AreaEvent(s));
                                break;
                            case Constant.LIVELIST:
                                //直播列表
                                EventBus.getDefault().post(new LiveListEvent(s));
                                break;
                            case Constant.LIVECONTENTLIST:
                                //直播列表
                                EventBus.getDefault().post(new LiveContentEvent(s));
                                break;
                            case Constant.TYPELIST:
                                //影片跳转播放
                                EventBus.getDefault().post(new FilmTypeEvent(s));
                                break;
                            case Constant.YEARLIST:
                                //地区列表
                                EventBus.getDefault().post(new YearEvent(s));
                                break;
                            case Constant.FIRSTKEY:
                                //地区列表
                                EventBus.getDefault().post(new KeyWordEvent(s));
                                break;
//                            case Constant.SCORELIST:
//                                //地区列表
//                                EventBus.getDefault().post(new AreaEvent(s));
//                                break;
                            case Constant.FILMLIST:
                                //影片类型
                                if (mInt == 1) {
                                    EventBus.getDefault().post(new FilmTypeEvent(s));
                                }
//                地区
                                if (mInt == 2) {
                                    EventBus.getDefault().post(new AreaFilmListEvent(s));
                                }
//                导演
                                if (mInt == 3) {
                                    EventBus.getDefault().post(new DirectorListEvent(s));
                                }
                                //                演员
                                if (mInt == 4) {
                                    EventBus.getDefault().post(new ActorListEvent(s));
                                }
                                //                年份
                                if (mInt == 5) {
                                    EventBus.getDefault().post(new YearListEvent(s));
                                }  //               关键字
                                if (mInt == 6) {
                                    EventBus.getDefault().post(new KeyWordEvent(s));
                                }
                                break;
                            case Constant.ALIPAY:
                                //二维码
                                EventBus.getDefault().post(new QrCodeEvent(s));
                                break;
                            case Constant.DETAIL:
                                //影片明晰
                                EventBus.getDefault().post(new DetailEvent(s));
                                break;
                        }
                    } else {
                        switch (response.getMsgType()) {
                            case Constant.FIRSTKEY:
                                EventBus.getDefault().post(new SearchNoListEvent(s));
                                break;
                            default:
                                EventBus.getDefault().post(new ErrorEvent(s));
                                break;
                        }
                    }
                    //EventBus.getDefault().post(event);
                }

                @Override
                public void onClose(int code, String reason) {
                    Log.d(TAG, "ABS close: Connection lost.");
                }
            });
        } catch (WebSocketException e) {
            e.printStackTrace();
        }
    }

    public int mInt = 0;

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(CmdEvent event) {
        String cmd = event.msg;
        mInt = event.mInt;
        Log.d(TAG, "onMessage" + cmd);
        if (mConnection != null) {
            mConnection.sendTextMessage(cmd);
        } else {
            connect();
            mConnection.sendTextMessage(cmd);
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "服务启动", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "绑定服务", Toast.LENGTH_SHORT).show();

        return new MyBinder();
    }

    public void closeConnection() {
        mConnection.disconnect();
        Log.d("abs", "disconnect");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mConnection.isConnected()) {
            mConnection.disconnect();
        }
        Log.d("abs", "destroy    disconnect");
    }

    public class MyBinder extends Binder {
        public AutoBahnService getInstance() {
            return AutoBahnService.this;
        }
    }
}
