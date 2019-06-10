package com.tong.kit;

/**
 * @Author stonewong
 * @Description TODO
 * @Date 2019/6/10 17:31
 * @Param
 * @return
 **/
public final class PathKit {

    public static String fixPath(String path) {
        if (path == null) {
            return "/";
        }
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        if (path.length() > 1 && path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }

    public static String cleanPath(String path){
        if (path == null) {
            return null;
        }
        return path.replaceAll("[/]+", "/");
    }

}

