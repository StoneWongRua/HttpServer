package com.tong;

import java.io.FileNotFoundException;
import java.util.Map;

/**
 * @Author stonewong
 * @Description TODO
 * @Date 2019/6/10 17:31
 * @Param
 * @return
 **/
public class TongContext {
    private String rootPath;

    private String publicPath;

    private Map<String, String> locale;

    private TongContext(String rootPath){
        this.rootPath = rootPath;
    }

    public static TongContext init(String rootDir) throws FileNotFoundException {

        TongContext context = new TongContext(rootDir);

        return context;
    }
    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getPublicPath() {
        return publicPath;
    }

    public void setPublicPath(String publicPath) {
        this.publicPath = publicPath;
    }

    public Map<String, String> getLocale() {
        return locale;
    }

    public void setLocale(Map<String, String> locale) {
        this.locale = locale;
    }


}
