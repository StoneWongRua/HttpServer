package com.tong.test;

import com.tong.Tong;
import com.tong.handler.impl.AssetHandler;

import java.io.IOException;

/**
 * @Author stonewong
 * @Description TODO
 * @Date 2019/6/10 17:34
 * @Param
 * @return
 **/
public class TongTest {
    public static void main(String[] args) {
        try {
            Tong tong = new Tong(9999);
            tong.addHandler(new AssetHandler("C:\\Users\\15845\\说到做到hst\\web"));
            tong.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
