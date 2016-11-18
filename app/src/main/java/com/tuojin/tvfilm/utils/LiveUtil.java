package com.tuojin.tvfilm.utils;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.TimeZone;


public class LiveUtil {

    //	@Test
    public static void main(String[] args) throws IOException, ParseException {

		
		/*//取得10位时间戳
		long longTime = System.currentTimeMillis();
        int timeStamp = (int) (longTime / 1000);
        
        //十六进制时间戳
        String sixteenTimeStamp = Long.toHexString(timeStamp).toUpperCase();
		
        //原始URL的path
        String originalPath = "/channel01/2300.m3u8";//todo 从直播内容的虚拟直播通道获取
		
        //wskey
        String wskey = "k2hrwtgk0wybzdysm2sbl8";//todo 从数据字典表中获取
        
        //加密串1=网宿给的随机码wskey+url+时间戳
        String tmpEncrypt1 = wskey + originalPath + sixteenTimeStamp;
        System.out.println("tmpEncrypt1="+tmpEncrypt1);
        
        String encrypt1 = MD5Util.MD5(tmpEncrypt1);
        System.out.println("encrypt1="+encrypt1);
        
		//最终URL前缀
        String finalUrlPrefix = "http://live.bestmovie.com.cn:8088/";//todo 从数据字典表中获取
        
        //最终URL后缀
        String finalUrlSuffix = "";
        
        //url+md5加密串1+时间戳+url = 生成新的url
        String finalUrl = finalUrlPrefix + encrypt1 + "/" + sixteenTimeStamp + originalPath + finalUrlSuffix;

        System.out.println("finalUrl="+finalUrl);	*/


        String url = "http://live.bestmovie.com.cn:8088/channel03/1400.m3u8";
//        String url = "http://live.bestmovie.com.cn:8088/channel05/2300.m3u8";
        String key = "k2hrwtgk0wybzdysm2sbl8";
        getLiveFinalUrl(url, key);
    }


    /**
     *    原始URL地址拆分内容
     * @param wskey          网宿提供防盗链KEY
     * 原始URL地址拆分最终URL前缀
     * @return
     * @author Zhouqi
     */
    public static String getLiveFinalUrl(String url, String wskey) {

        String finalUrlPrefix = "http://live.bestmovie.com.cn:8088/";

        String originalPath = url.replace("http://live.bestmovie.com.cn:8088", "");
        //最终URL前缀
        //String finalUrlPrefix = "http://live.bestmovie.com.cn:8088/";//从直播内容的虚拟直播通道获取
        //原始URL的path
        //String originalPath = "/channel01/2300.m3u8";//从直播内容的虚拟直播通道获取
        //wskey
        //String wskey = "k2hrwtgk0wybzdysm2sbl8";//从数据字典表中获取

        if (StringUtils.isEmpty(originalPath) || StringUtils.isEmpty(wskey) || StringUtils.isEmpty(finalUrlPrefix)) {
            return null;
        }

        //取得10位时间戳
        long longTime = System.currentTimeMillis();
        long zeroTime = longTime / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        int timeStamp = (int) (zeroTime / 1000);

        //十六进制时间戳
        String sixteenTimeStamp = Long.toHexString(timeStamp).toUpperCase();
        System.out.println(sixteenTimeStamp);
        //加密串1=网宿给的随机码wskey+url+时间戳
        String tmpEncrypt1 = wskey + originalPath + sixteenTimeStamp;
        System.out.println("tmpEncrypt1=" + tmpEncrypt1);
        String encrypt1 = MD5Util.MD5(tmpEncrypt1);
        //最终URL后缀
        String finalUrlSuffix = "";
        //url+md5加密串1+时间戳+url = 生成新的url
        String finalUrl = finalUrlPrefix + encrypt1 + "/" + sixteenTimeStamp + originalPath + finalUrlSuffix;
        System.out.println("finalUrl=" + finalUrl);
        return finalUrl;
		
		/*
		 * 网宿验证逻辑：（由网宿加速节点完成） 
		用户使用加密的 URL 访问网宿的加速节点，网宿服务器会先把加密串1 提取出来，并得到原始的 URL 的<path>部分，用户访问时间，然后按照定义的业务逻辑进行验证： 
		a) 使用原始的 URL 中的 <path>部分，请求时间及 WSKEY 进行 MD5 加密得到一个加密串 2 
		b) 比较加密串 2 与加密串 1 是否一致，如果不一致则拒绝。 
		c) 取网宿加速节点服务器当前时间，并与从访问 URL 中所带的明文时间相减，判断是否超过设置的时限 t（时间域值 t 可以设置，以分钟为单位）； 
		d) 时间差小于设置时限的为合法请求，网宿加速节点才会给予正常的响应，否则拒绝该请求，返回 403。
		*/

    }


}
