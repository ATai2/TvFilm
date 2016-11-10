package com.tuojin.tvfilm.net;

import com.tuojin.tvfilm.event.CmdEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * 文 件 名: TvFilmNetWorkWS
 * 创 建 人: Administrator
 * 创建日期: 2016/9/20 15:21
 * 文件描述：应用开启即启动socket，应用关闭，关闭。
 * 将网络请求从，Androidasynic换成okhttp。开启服务
 * 在网络处理时，注意msgType即可判断次方法为什么方法，之后可以进行操作，json转换，替换。发送消息等
 * 邮   箱:
 * 博   客:
 * 修改时间：
 * 修改备注：
 */
public class TvFilmNetWorkWS {
    private String TAG = "ws";

    public TvFilmNetWorkWS() {
    }

    public void sendMsg(String cmd, int i) {
        EventBus.getDefault().post(new CmdEvent(cmd, i));
    }

    public void sendMsg(String cmd) {
        sendMsg(cmd, 0);
    }
}

