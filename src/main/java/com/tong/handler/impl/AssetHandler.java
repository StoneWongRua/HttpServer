package com.tong.handler.impl;

import com.tong.Const;
import com.tong.handler.RequestHandler;
import com.tong.http.HttpRequest;
import com.tong.http.HttpResponse;
import com.tong.http.HttpStatus;
import com.tong.kit.PathKit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Logger;

/**
 * @Author stonewong
 * @Description TODO
 * @Date 2019/6/1 17:08
 * @Param
 * @return
 **/
public class AssetHandler implements RequestHandler {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(AssetHandler.class));

    private File rootFile;

    private String rootPath;

    public AssetHandler(File rootFile) {
        this.rootFile = rootFile;
        this.rootPath = rootFile.getPath();
    }

    public AssetHandler(String rootPath) {
        this(new File(rootPath));
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        String uri = request.getUri();
        try {
            uri = URLDecoder.decode(uri, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            uri = uri.replace("%20", " ");
        }

        LOGGER.info("Request URI > " + uri);

        uri = PathKit.fixPath(uri);

        if(uri.equals("/")){
            uri = "/" + Const.DEFAULT_ROOTFILE;
        }

        File file = new File(rootFile, uri);
        if (file.exists() && !file.isDirectory()) {
            try {
                if (rootPath == null) {
                    rootPath = rootFile.getAbsolutePath();
                    if (rootPath.endsWith("/") || rootPath.endsWith(".")) {
                        rootPath = rootPath.substring(0, rootPath.length() - 1);
                    }
                }

                String requestPath = file.getCanonicalPath();
                if (requestPath.endsWith("/")) {
                    requestPath = requestPath.substring(0, requestPath.length() - 1);
                }
                if (!requestPath.startsWith(rootPath)) {
                    return new HttpResponse().reason(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.toString());
                }

                HttpResponse res = new HttpResponse(HttpStatus.OK, new FileInputStream(file));
                res.setLength(file.length());
                return res;

            } catch (IOException e) {
                return new HttpResponse().reason(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString());
            }
        }
        return null;
    }

}
