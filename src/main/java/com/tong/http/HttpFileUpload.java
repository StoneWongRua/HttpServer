package com.tong.http;

import java.io.File;

/**
 * @Author stonewong
 * @Description TODO
 * @Date 2019/6/10 16:44
 * @Param
 * @return
 **/
public class HttpFileUpload {
    private String fileName;

    private File tempFile;

    public HttpFileUpload(String fileName, File tempFile) {
        this.fileName = fileName;
        this.tempFile = tempFile;
    }

    public String getFileName() {
        return fileName;
    }

    public File getTempFile() {
        return tempFile;
    }
}
