package com.tong;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * @Author stonewong
 * @Description TODO
 * @Date 2019/6/1 14:44
 * @Param
 * @return
 **/
public class Const {
    /**
    * 默认编码
    * */
    public static final Charset CHARSET = Charset.forName("utf-8");

    /**
    * 编码器
    * */
    public static final CharsetEncoder ENCODER = CHARSET.newEncoder();

    /**
     * 默认主页文件
     */
    public static String DEFAULT_ROOTFILE = "index.html";
}
